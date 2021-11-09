package dto;

public class OrderDetailDto {
    private String OrderId;
    private String ItemCode;
    private int OrderQTY;
    private double ItemCost;

    public OrderDetailDto() {
    }

    public OrderDetailDto(String orderId, String itemCode, int orderQTY, double itemCost) {
        OrderId = orderId;
        ItemCode = itemCode;
        OrderQTY = orderQTY;
        ItemCost = itemCost;
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
}
