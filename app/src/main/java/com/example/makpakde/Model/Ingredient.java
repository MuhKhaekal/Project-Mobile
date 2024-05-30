package com.example.makpakde.Model;

import com.google.gson.annotations.SerializedName;

public class Ingredient {

    @SerializedName("text")
    private String text;

    @SerializedName("quantity")
    private float quantity;

    @SerializedName("measure")
    private String measure;

    @SerializedName("food")
    private String food;

    @SerializedName("weight")
    private float weight;

    @SerializedName("foodCategory")
    private String foodCategory;

    @SerializedName("foodId")
    private String foodId;

    @SerializedName("image")
    private String image;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
