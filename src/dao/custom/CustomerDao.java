package dao.custom;

import dao.CrudDao;
import entity.Customer;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface CustomerDao extends CrudDao<Customer,String> {
    ObservableList<String> getCustomerId() throws SQLException;
    public String getLastCustomerId() throws SQLException;
}
