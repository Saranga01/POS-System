package dao.custom;

import dao.CrudDao;
import entity.OrderDetail;
import entity.Orders;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface OrderDao  extends CrudDao<Orders,String > {
    public String getOrderId() throws SQLException;
    public ResultSet getItemPrice(String itemCode) throws SQLException;
    public boolean orderDetailsSave(OrderDetail orderDetails) throws SQLException;

}
