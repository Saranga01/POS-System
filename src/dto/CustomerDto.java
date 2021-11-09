package dto;

public class CustomerDto {
    private String CustomerId;
    private String CustomerTitle;
    private String CustomerName;
    private String CustomerAddress;
    private String CustomerCity;
    private String Province;
    private String PostalCode;

    public CustomerDto(String customerId, String customerTitle, String customerName, String customerAddress, String customerCity, String province, String postalCode) {
        CustomerId = customerId;
        CustomerTitle = customerTitle;
        CustomerName = customerName;
        CustomerAddress = customerAddress;
        CustomerCity = customerCity;
        Province = province;
        PostalCode = postalCode;
    }

    public CustomerDto() {

    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getCustomerTitle() {
        return CustomerTitle;
    }

    public void setCustomerTitle(String customerTitle) {
        CustomerTitle = customerTitle;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        CustomerAddress = customerAddress;
    }

    public String getCustomerCity() {
        return CustomerCity;
    }

    public void setCustomerCity(String customerCity) {
        CustomerCity = customerCity;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }
}
