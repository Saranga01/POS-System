package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrderDao;
import entity.OrderDetail;
import entity.Orders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDaoImpl implements OrderDao {


    @Override
    public String getOrderId() throws SQLException {
        ResultSet rst = CrudUtil.executeQuery("SELECT OrderId From `Orders` ORDER BY OrderId DESC LIMIT 1");
        return rst.next() ? String.format("O/%03d", (Integer.parseInt(rst.getString("OrderId").replace("O/", "")) + 1)) : "O/001";
    }

    @Override
    public ResultSet getItemPrice(String itemCode) throws SQLException {
        return CrudUtil.executeQuery("SELECT * FROM Item WHERE ItemCode=?",itemCode);
    }


    @Override
    public boolean orderDetailsSave(OrderDetail orderDetails) throws SQLException {
        return CrudUtil.executeUpdate("INSERT INTO OrderDetail VALUES (?,?,?,?)",orderDetails.getOrderId(),orderDetails.getItemCode(),orderDetails.getOrderQTY(),orderDetails.getItemCost());

    }

    @Override
    public boolean add(Orders orders) throws SQLException {
        return CrudUtil.executeUpdate("INSERT INTO Orders VALUES (?,?,?,?,?)",orders.getOrderId(),orders.getCustId(),orders.getOrderDate(),orders.getOrderTime(),orders.getTotalCost());

    }

    @Override
    public boolean delete(String s) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Orders orders) throws SQLException {
        return false;
    }

    @Override
    public Orders search(String s) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Orders> getAll() throws SQLException {
        return null;
    }
}
