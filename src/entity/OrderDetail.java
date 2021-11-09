package entity;

public class OrderDetail {
    private String OrderId;
    private String ItemCode;
    private int OrderQTY;
    private double ItemCost;

    public OrderDetail() {
    }

    public OrderDetail(String orderId, String itemCode, int orderQTY, double itemCost) {
        setOrderId(orderId);
        setItemCode(itemCode);
        setOrderQTY(orderQTY);
        setItemCost(itemCost);
    }


    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public int getOrderQTY() {
        return OrderQTY;
    }

    public void setOrderQTY(int orderQTY) {
        OrderQTY = orderQTY;
    }

    public double getItemCost() {
        return ItemCost;
    }

    public void setItemCost(double itemCost) {
        ItemCost = itemCost;
    }

    @Override
    public String toString() {
        return "Orderdetail{" +
                "OrderId='" + OrderId + '\'' +
                ", ItemCode='" + ItemCode + '\'' +
                ", OrderQTY=" + OrderQTY +
                ", ItemCost=" + ItemCost +
                '}';
    }
}
