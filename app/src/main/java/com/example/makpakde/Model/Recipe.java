package com.example.makpakde.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Recipe {

    @SerializedName("uri")
    private String uri;

    @SerializedName("label")
    private String label;

    @SerializedName("image")
    private String image;

    @SerializedName("source")
    private String source;

    @SerializedName("url")
    private String url;

    @SerializedName("shareAs")
    private String shareAs;

    @SerializedName("yield")
    private float yield;

    @SerializedName("dietLabels")
    private List<String> dietLabels;

    @SerializedName("healthLabels")
    private List<String> healthLabels;

    @SerializedName("cautions")
    private List<String> cautions;

    @SerializedName("ingredientLines")
    private List<String> ingredientLines;

    @SerializedName("ingredients")
    private List<Ingredient> ingredients;

    @SerializedName("calories")
    private float calories;

    @SerializedName("totalCO2Emissions")
    private float totalCO2Emissions;

    @SerializedName("co2EmissionsClass")
    private String co2EmissionsClass;

    @SerializedName("totalWeight")
    private float totalWeight;

    @SerializedName("totalTime")
    private float totalTime;

    @SerializedName("cuisineType")
    private List<String> cuisineType;

    @SerializedName("mealType")
    private List<String> mealType;

    @SerializedName("dishType")
    private List<String> dishType;

    @SerializedName("totalNutrients")
    private TotalNutrients totalNutrients;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShareAs() {
        return shareAs;
    }

    public void setShareAs(String shareAs) {
        this.shareAs = shareAs;
    }

    public float getYield() {
        return yield;
    }

    public void setYield(float yield) {
        this.yield = yield;
    }

    public List<String> getDietLabels() {
        return dietLabels;
    }

    public void setDietLabels(List<String> dietLabels) {
        this.dietLabels = dietLabels;
    }

    public List<String> getHealthLabels() {
        return healthLabels;
    }

    public void setHealthLabels(List<String> healthLabels) {
        this.healthLabels = healthLabels;
    }

    public List<String> getCautions() {
        return cautions;
    }

    public void setCautions(List<String> cautions) {
        this.cautions = cautions;
    }

    public List<String> getIngredientLines() {
        return ingredientLines;
    }

    public void setIngredientLines(List<String> ingredientLines) {
        this.ingredientLines = ingredientLines;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public float getTotalCO2Emissions() {
        return totalCO2Emissions;
    }

    public void setTotalCO2Emissions(float totalCO2Emissions) {
        this.totalCO2Emissions = totalCO2Emissions;
    }

    public String getCo2EmissionsClass() {
        return co2EmissionsClass;
    }

    public void setCo2EmissionsClass(String co2EmissionsClass) {
        this.co2EmissionsClass = co2EmissionsClass;
    }

    public float getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(float totalWeight) {
        this.totalWeight = totalWeight;
    }

    public float getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(float totalTime) {
        this.totalTime = totalTime;
    }

    public List<String> getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(List<String> cuisineType) {
        this.cuisineType = cuisineType;
    }

    public List<String> getMealType() {
        return mealType;
    }

    public void setMealType(List<String> mealType) {
        this.mealType = mealType;
    }

    public List<String> getDishType() {
        return dishType;
    }

    public void setDishType(List<String> dishType) {
        this.dishType = dishType;
    }

    public TotalNutrients getTotalNutrients() {
        return totalNutrients;
    }

    public void setTotalNutrients(TotalNutrients totalNutrients) {
        this.totalNutrients = totalNutrients;
    }
}
