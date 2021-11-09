package entity;

public class Orders {
    private String OrderId;
    private String CustId;
    private String OrderDate;
    private String OrderTime;
    private double TotalCost;

    public Orders() {
    }

    public Orders(String orderId, String custId, String orderDate, String orderTime, double totalCost) {
        setOrderId(orderId);
        setCustId(custId);
        setOrderDate(orderDate);
        setOrderTime(orderTime);
        setTotalCost(totalCost);
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

    @Override
    public String toString() {
        return "Orders{" +
                "OrderId='" + OrderId + '\'' +
                ", CustId='" + CustId + '\'' +
                ", OrderDate='" + OrderDate + '\'' +
                ", OrderTime='" + OrderTime + '\'' +
                ", TotalCost=" + TotalCost +
                '}';
    }
}
