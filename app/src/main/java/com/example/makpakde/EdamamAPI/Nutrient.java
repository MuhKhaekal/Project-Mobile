package com.example.makpakde.EdamamAPI;

import com.google.gson.annotations.SerializedName;

public class Nutrient {
    @SerializedName("label")
    private String label;

    @SerializedName("quantity")
    private float quantity;

    @SerializedName("unit")
    private String unit;
}
