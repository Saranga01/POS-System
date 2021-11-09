package dto;

public class CartTmDto {
    private String itemCode;
    private int reqQty;
    private double price;

    public CartTmDto() {
    }

    public CartTmDto(String itemCode, int reqQty, double price) {
        this.setItemCode(itemCode);
        this.setReqQty(reqQty);
        this.setPrice(price);
    }


    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getReqQty() {
        return reqQty;
    }

    public void setReqQty(int reqQty) {
        this.reqQty = reqQty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
