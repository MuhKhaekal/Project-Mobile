package com.example.makpakde.EdamamAPI;

import com.example.makpakde.EdamamAPI.Nutrient;
import com.google.gson.annotations.SerializedName;

public class TotalNutrients {
    private Nutrient energy;

    @SerializedName("FAT")
    private Nutrient fat;

    @SerializedName("FASAT")
    private Nutrient saturatedFat;

    @SerializedName("FATRN")
    private Nutrient transFat;

    @SerializedName("FAMS")
    private Nutrient monounsaturatedFat;

    @SerializedName("FAPU")
    private Nutrient polyunsaturatedFat;

    @SerializedName("CHOCDF")
    private Nutrient carbs;

    @SerializedName("CHOCDF.net")
    private Nutrient netCarbs;

    @SerializedName("FIBTG")
    private Nutrient fiber;

    @SerializedName("SUGAR")
    private Nutrient sugars;

    @SerializedName("PROCNT")
    private Nutrient protein;

    @SerializedName("CHOLE")
    private Nutrient cholesterol;

    @SerializedName("NA")
    private Nutrient sodium;

    @SerializedName("CA")
    private Nutrient calcium;

    @SerializedName("MG")
    private Nutrient magnesium;

    @SerializedName("K")
    private Nutrient potassium;

    @SerializedName("FE")
    private Nutrient iron;

    @SerializedName("ZN")
    private Nutrient zinc;

    @SerializedName("P")
    private Nutrient phosphorus;

    @SerializedName("VITA_RAE")
    private Nutrient vitaminA;

    @SerializedName("VITC")
    private Nutrient vitaminC;

    @SerializedName("THIA")
    private Nutrient thiamin;

    @SerializedName("RIBF")
    private Nutrient riboflavin;

    @SerializedName("NIA")
    private Nutrient niacin;

    @SerializedName("VITB6A")
    private Nutrient vitaminB6;

    @SerializedName("FOLDFE")
    private Nutrient folate;

    @SerializedName("VITB12")
    private Nutrient vitaminB12;

    @SerializedName("VITD")
    private Nutrient vitaminD;

    @SerializedName("TOCPHA")
    private Nutrient vitaminE;

    @SerializedName("VITK1")
    private Nutrient vitaminK;

    @SerializedName("WATER")
    private Nutrient water;
}
