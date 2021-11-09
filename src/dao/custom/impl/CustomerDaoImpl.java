package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDao;
import entity.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public boolean add(Customer customer) throws SQLException {
        return CrudUtil.executeUpdate("INSERT INTO Customer VALUES(?,?,?,?,?,?,?)", customer.getCustomerId(), customer.getCustomerTitle(), customer.getCustomerName(), customer.getCustomerAddress(), customer.getCity(), customer.getProvince(), customer.getPostalCode());
    }

    @Override
    public boolean delete(String s) throws SQLException {
        return  CrudUtil.executeUpdate("DELETE FROM Customer WHERE CustId=?",s);
    }

    @Override
    public boolean update(Customer customer) throws SQLException {
        return CrudUtil.executeUpdate("UPDATE Customer SET CustTitle=?,CustName=?,CustAddress=?,City=?,Province=?,PostalCode=? WHERE CustId=?",customer.getCustomerTitle(), customer.getCustomerName(), customer.getCustomerAddress(), customer.getCity(), customer.getProvince(), customer.getPostalCode(),customer.getCustomerId());

    }

    @Override
    public Customer search(String s) throws SQLException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM Customer WHERE CustId=?", s);

        resultSet.next();
        return new Customer(
                resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getString(6),
                resultSet.getString(7)
        );

    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException {

        ArrayList<Customer> customers=new ArrayList<>();
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM Customer");
        while (resultSet.next()){
            customers.add(
                    new Customer(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7)
                    )
            );
        }
        return customers;
    }

    @Override
    public ObservableList<String> getCustomerId() throws SQLException {
        ObservableList<String> customerIds = FXCollections.observableArrayList();
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM Customer");
        while (resultSet.next()) {
            customerIds.add(
                    resultSet.getString(1));
        }

        return customerIds;
    }

    @Override
    public String getLastCustomerId() throws SQLException {
        ResultSet rst = CrudUtil.executeQuery("SELECT CustId From `Customer` ORDER BY CustId DESC LIMIT 1");
        return rst.next() ? String.format("C/%03d", (Integer.parseInt(rst.getString("CustId").replace("C/", "")) + 1)) : "C/001";
    }
}
