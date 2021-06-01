package by.brgtu.david.krasko.diplom.dao.jdbc.mapper;

import by.brgtu.david.krasko.diplom.model.Goods;
import by.brgtu.david.krasko.diplom.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class OrderRowMapper implements RowMapper<Order> {

    private static final String ORDER_ID = "order_id";
    private static final String FIRST_NAME = "first_name";
    private static final String SECOND_NAME = "second_name";

    private StatusRowMapper statusRowMapper;
    private GoodsRowMapper goodsRowMapper;

    @Autowired
    public OrderRowMapper(final StatusRowMapper statusRowMapper, final GoodsRowMapper goodsRowMapper) {
        this.statusRowMapper = statusRowMapper;
        this.goodsRowMapper = goodsRowMapper;
    }

    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
        long orderId = resultSet.getLong(ORDER_ID);
        Order order = new Order(orderId, statusRowMapper.mapRow(resultSet, i),
                resultSet.getString(FIRST_NAME),
                resultSet.getString(SECOND_NAME));
        List<Goods> goodsList = new ArrayList<>();
        Integer totalPrice = 0;
        Goods goods;
        do {
            goods = goodsRowMapper.mapRow(resultSet, i);
            if (Objects.nonNull(goods.getName())) {
                goodsList.add(goods);
                totalPrice += goods.getPrice();
            }
        } while (resultSet.next() && orderId == resultSet.getLong(ORDER_ID));
        if (!resultSet.isAfterLast()) {
            resultSet.previous();
        }
        order.setGoodsList(goodsList);
        order.setTotalPrice(totalPrice);
        return order;
    }
}
