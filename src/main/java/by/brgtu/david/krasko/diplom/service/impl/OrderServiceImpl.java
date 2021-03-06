package by.brgtu.david.krasko.diplom.service.impl;

import by.brgtu.david.krasko.diplom.dao.GoodsDao;
import by.brgtu.david.krasko.diplom.dao.OrderDao;
import by.brgtu.david.krasko.diplom.exception.ValidationException;
import by.brgtu.david.krasko.diplom.model.Order;
import by.brgtu.david.krasko.diplom.model.Status;
import by.brgtu.david.krasko.diplom.model.dto.CustomerDto;
import by.brgtu.david.krasko.diplom.service.OrderService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private GoodsDao goodsDao;
    private final MessageSourceService messageSourceService;
    private final Counter counter;

    @Autowired
    public OrderServiceImpl(final OrderDao orderDao, final GoodsDao goodsDao,
                            final MessageSourceService messageSourceService, final MeterRegistry meterRegistry) {
        this.orderDao = orderDao;
        this.goodsDao = goodsDao;
        this.messageSourceService = messageSourceService;
        this.counter = Counter.builder("submitted.orders")
                .tag("type", "ale")
                .description("The amount of submitted orders")
                .register(meterRegistry);
    }

    @Override
    public List<Order> getListOrder(final Long userId) {
        return orderDao.getListOrder(userId);
    }

    @Override
    @Transactional
    public void submitOrder(final Long userId, final CustomerDto customer) {
        counter.increment();
        if (goodsDao.checkOrAllGoodsAvailable(userId)) {
            Long orderId = orderDao.submitListOrder(userId);
            orderDao.updateOrderCustomer(orderId, customer);
            orderDao.addGoodsToOrder(userId, orderId);
            goodsDao.removeGoodsByUserId(userId);
        } else {
            throw new ValidationException(
                    messageSourceService.getLocaleMessage("order.errorBasketHasGoodsAvailable"));
        }
    }

    @Override
    public void updateOrderStatus(final Long orderId, final Long statusId) {
        if (!orderDao.updateOrderStatus(orderId, statusId)) {
            throw new ValidationException(
                    messageSourceService.getLocaleMessage("order.errorExistingOfOrder"));
        }
    }

    @Override
    public void removeOrder(final Long orderId) {
        if (!orderDao.removeOrder(orderId)) {
            throw new ValidationException(
                    messageSourceService.getLocaleMessage("order.errorExistingOfOrder"));
        }
    }

    @Override
    public Order getOrderById(final Long orderId) {
        try {
            return orderDao.getOrderById(orderId);
        } catch (EmptyResultDataAccessException ex) {
            throw new ValidationException(
                    messageSourceService.getLocaleMessage("order.errorExistingOfOrder"));
        }
    }

    @Override
    public List<Status> getListStatus() {
        return orderDao.getListStatus();
    }
}
