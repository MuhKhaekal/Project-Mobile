package com.example.makpakde.Model;

import com.google.gson.annotations.SerializedName;

public class Nutrient {
    @SerializedName("label")
    private String label;

    @SerializedName("quantity")
    private float quantity;

    @SerializedName("unit")
    private String unit;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
