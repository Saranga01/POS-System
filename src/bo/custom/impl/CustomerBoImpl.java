package bo.custom.impl;

import bo.custom.CustomerBo;
import dao.custom.CustomerDao;
import dao.custom.impl.CustomerDaoImpl;
import dto.CustomerDto;
import entity.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBoImpl  implements CustomerBo {
    CustomerDao customerDao=new CustomerDaoImpl();

    @Override
    public String generateCustomerId() throws SQLException {
        int tempId=Integer.parseInt(customerDao.getLastCustomerId().split("/")[1]);
        if(tempId<10){
            return  "C/00"+tempId;
        }else if(tempId<100){
            return "C/0"+tempId;
        }else {
            return "C"+tempId;
        }
    }

    @Override
    public boolean saveCustomer(CustomerDto customerDto) throws SQLException {
        return customerDao.add(new Customer(customerDto.getCustomerId(),customerDto.getCustomerTitle(),customerDto.getCustomerName(),customerDto.getCustomerAddress(),customerDto.getCustomerCity(),customerDto.getProvince(),customerDto.getPostalCode()));

    }

    @Override
    public boolean updateCustomer(CustomerDto customerDto) throws SQLException {
        return customerDao.update(new Customer(customerDto.getCustomerId(),customerDto.getCustomerTitle(),customerDto.getCustomerName(),customerDto.getCustomerAddress(),customerDto.getCustomerCity(),customerDto.getProvince(),customerDto.getPostalCode()));

    }

    @Override
    public CustomerDto searchCustomer(String Id) throws SQLException {
        Customer customer=customerDao.search(Id);
        CustomerDto customerDto= new CustomerDto(
                customer.getCustomerId(),
                customer.getCustomerTitle(),
                customer.getCustomerName(),
                customer.getCustomerAddress(),
                customer.getCity(),
                customer.getProvince(),
                customer.getPostalCode()
                );
        return customerDto;
    }

    @Override
    public boolean deleteCustomer(String Id) throws SQLException {
        return customerDao.delete(Id);
    }

    @Override
    public ObservableList<CustomerDto> viewAllCustomers() throws SQLException {
        ArrayList<Customer> getAllCustomers = customerDao.getAll();
        ObservableList<CustomerDto> setAllCustomers=FXCollections.observableArrayList();

        for (Customer allCustomers:getAllCustomers
             ) {
                setAllCustomers.add(
                        new CustomerDto(allCustomers.getCustomerId(),allCustomers.getCustomerTitle(),allCustomers.getCustomerName(),allCustomers.getCustomerAddress(),allCustomers.getCity(),allCustomers.getProvince(),allCustomers.getPostalCode())
                );
        }

        return setAllCustomers;

    }

    @Override
    public void setCustomerTableValue(TableColumn... tableValue) {

        String [] customerProperty=new String[7];
        customerProperty[0]="CustomerId";
        customerProperty[1]="CustomerTitle";
        customerProperty[2]="CustomerName";
        customerProperty[3]="CustomerAddress";
        customerProperty[4]="CustomerCity";
        customerProperty[5]="Province";
        customerProperty[6]="PostalCode";

        for(int i=0;i<tableValue.length;i++){
            tableValue[i].setCellValueFactory(new PropertyValueFactory<>(customerProperty[i]));
            tableValue[i].setStyle("-fx-alignment: CENTER;");
        }


    }

    @Override
    public ObservableList<String> getCustomerIds() throws SQLException {
       return customerDao.getCustomerId();

    }


}
