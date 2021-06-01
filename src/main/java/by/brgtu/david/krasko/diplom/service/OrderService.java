package by.brgtu.david.krasko.diplom.service;


import by.brgtu.david.krasko.diplom.model.Order;
import by.brgtu.david.krasko.diplom.model.Status;
import by.brgtu.david.krasko.diplom.model.dto.CustomerDto;

import java.util.List;

/**
 * The interface Order service.
 */
public interface OrderService {

    /**
     * Gets list order.
     *
     * @param userId the user id
     * @return the list order
     */
    List<Order> getListOrder(Long userId);

    /**
     * Submit order.
     *
     * @param userId the user id
     * @param customer customer
     */
    void submitOrder(Long userId, CustomerDto customer);

    /**
     * Update order status.
     *
     * @param orderId  the order id
     * @param statusId the status id
     */
    void updateOrderStatus(Long orderId, Long statusId);

    /**
     * Remove order.
     *
     * @param orderId the order id
     */
    void removeOrder(Long orderId);

    /**
     * Gets order by id.
     *
     * @param orderId the order id
     * @return the order by id
     */
    Order getOrderById(Long orderId);

    /**
     * Gets list status.
     *
     * @return the list status
     */
    List<Status> getListStatus();
}
