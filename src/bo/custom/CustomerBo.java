package bo.custom;

import bo.SuperBo;
import dto.CustomerDto;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

import java.sql.SQLException;

public interface CustomerBo extends SuperBo {
    public String generateCustomerId() throws SQLException;
    public boolean saveCustomer(CustomerDto customerDto) throws SQLException;
    public boolean updateCustomer(CustomerDto customerDto) throws SQLException;
    public CustomerDto searchCustomer(String Id) throws SQLException;
    public boolean deleteCustomer(String Id) throws SQLException;
    public ObservableList<CustomerDto> viewAllCustomers() throws SQLException;
    public void setCustomerTableValue(TableColumn... tableValue);
    public ObservableList<String> getCustomerIds() throws SQLException;

}
