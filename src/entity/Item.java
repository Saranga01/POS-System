package entity;

public class Item {
    private String ItemCode;
    private String Description;
    private String PackSize;
    private double UnitPrice;
    private int QtyOnHand;
    private double DiscRate;

    public Item() {
    }

    public Item(String itemCode, String description, String packSize, double unitPrice, int qtyOnHand, double discRate) {
        setItemCode(itemCode);
        setDescription(description);
        setPackSize(packSize);
        setUnitPrice(unitPrice);
        setQtyOnHand(qtyOnHand);
        setDiscRate(discRate);
    }


    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPackSize() {
        return PackSize;
    }

    public void setPackSize(String packSize) {
        PackSize = packSize;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    public int getQtyOnHand() {
        return QtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        QtyOnHand = qtyOnHand;
    }

    public double getDiscRate() {
        return DiscRate;
    }

    public void setDiscRate(double discRate) {
        DiscRate = discRate;
    }

    @Override
    public String toString() {
        return "Item{" +
                "ItemCode='" + ItemCode + '\'' +
                ", Description='" + Description + '\'' +
                ", PackSize='" + PackSize + '\'' +
                ", UnitPrice=" + UnitPrice +
                ", QtyOnHand=" + QtyOnHand +
                ", DiscRate=" + DiscRate +
                '}';
    }
}
