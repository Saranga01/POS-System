package dto;

public class ItemDto {
    private String itemCode;
    private String description;
    private String packSize;
    private Double unitPrice;
    private int qtyOnHand;
    private Double discountRate;

    public ItemDto(String string, String resultSetString, String setString, String s, String string1, String resultSetString1){

    }

    public ItemDto(String itemCode, String description, String packSize, Double unitPrice, int qtyOnHand, Double discountRate){
        this.itemCode=itemCode;
        this.description=description;
        this.packSize=packSize;
        this.unitPrice=unitPrice;
        this.qtyOnHand=qtyOnHand;
        this.discountRate=discountRate;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPackSize() {
        return packSize;
    }

    public void setPackSize(String packSize) {
        this.packSize = packSize;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public Double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
    }
}
