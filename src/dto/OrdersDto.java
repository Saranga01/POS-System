package dto;

public class OrdersDto {
    private String OrderId;
    private String CustId;
    private String OrderDate;
    private String OrderTime;
    private double TotalCost;

    public OrdersDto() {
    }

    public OrdersDto(String orderId, String custId, String orderDate, String orderTime, double totalCost) {
        OrderId = orderId;
        CustId = custId;
        OrderDate = orderDate;
        OrderTime = orderTime;
        TotalCost = totalCost;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getCustId() {
        return CustId;
    }

    public void setCustId(String custId) {
        CustId = custId;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public String getOrderTime() {
        return OrderTime;
    }

    public void setOrderTime(String orderTime) {
        OrderTime = orderTime;
    }

    public double getTotalCost() {
        return TotalCost;
    }

    public void setTotalCost(double totalCost) {
        TotalCost = totalCost;
    }
}
