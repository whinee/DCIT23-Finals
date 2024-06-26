package xyz.whinyaan;

// Arriola
public class Item {
    public double price;
    public double unitNum;
    public String unit;

    public Item(double price, double unitNum, String unit) {
        this.price = price;
        this.unitNum = unitNum;
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public double getUnitNum() {
        return unitNum;
    }

    public String getUnit() {
        return unit;
    }
}