package com.example.makpakde.EdamamAPI;

import com.example.makpakde.EdamamAPI.Nutrient;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

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

    public Nutrient getEnergy() {
        return energy;
    }

    public void setEnergy(Nutrient energy) {
        this.energy = energy;
    }

    public Nutrient getFat() {
        return fat;
    }

    public void setFat(Nutrient fat) {
        this.fat = fat;
    }

    public Nutrient getSaturatedFat() {
        return saturatedFat;
    }

    public void setSaturatedFat(Nutrient saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public Nutrient getTransFat() {
        return transFat;
    }

    public void setTransFat(Nutrient transFat) {
        this.transFat = transFat;
    }

    public Nutrient getMonounsaturatedFat() {
        return monounsaturatedFat;
    }

    public void setMonounsaturatedFat(Nutrient monounsaturatedFat) {
        this.monounsaturatedFat = monounsaturatedFat;
    }

    public Nutrient getPolyunsaturatedFat() {
        return polyunsaturatedFat;
    }

    public void setPolyunsaturatedFat(Nutrient polyunsaturatedFat) {
        this.polyunsaturatedFat = polyunsaturatedFat;
    }

    public Nutrient getCarbs() {
        return carbs;
    }

    public void setCarbs(Nutrient carbs) {
        this.carbs = carbs;
    }

    public Nutrient getNetCarbs() {
        return netCarbs;
    }

    public void setNetCarbs(Nutrient netCarbs) {
        this.netCarbs = netCarbs;
    }

    public Nutrient getFiber() {
        return fiber;
    }

    public void setFiber(Nutrient fiber) {
        this.fiber = fiber;
    }

    public Nutrient getSugars() {
        return sugars;
    }

    public void setSugars(Nutrient sugars) {
        this.sugars = sugars;
    }

    public Nutrient getProtein() {
        return protein;
    }

    public void setProtein(Nutrient protein) {
        this.protein = protein;
    }

    public Nutrient getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(Nutrient cholesterol) {
        this.cholesterol = cholesterol;
    }

    public Nutrient getSodium() {
        return sodium;
    }

    public void setSodium(Nutrient sodium) {
        this.sodium = sodium;
    }

    public Nutrient getCalcium() {
        return calcium;
    }

    public void setCalcium(Nutrient calcium) {
        this.calcium = calcium;
    }

    public Nutrient getMagnesium() {
        return magnesium;
    }

    public void setMagnesium(Nutrient magnesium) {
        this.magnesium = magnesium;
    }

    public Nutrient getPotassium() {
        return potassium;
    }

    public void setPotassium(Nutrient potassium) {
        this.potassium = potassium;
    }

    public Nutrient getIron() {
        return iron;
    }

    public void setIron(Nutrient iron) {
        this.iron = iron;
    }

    public Nutrient getZinc() {
        return zinc;
    }

    public void setZinc(Nutrient zinc) {
        this.zinc = zinc;
    }

    public Nutrient getPhosphorus() {
        return phosphorus;
    }

    public void setPhosphorus(Nutrient phosphorus) {
        this.phosphorus = phosphorus;
    }

    public Nutrient getVitaminA() {
        return vitaminA;
    }

    public void setVitaminA(Nutrient vitaminA) {
        this.vitaminA = vitaminA;
    }

    public Nutrient getVitaminC() {
        return vitaminC;
    }

    public void setVitaminC(Nutrient vitaminC) {
        this.vitaminC = vitaminC;
    }

    public Nutrient getThiamin() {
        return thiamin;
    }

    public void setThiamin(Nutrient thiamin) {
        this.thiamin = thiamin;
    }

    public Nutrient getRiboflavin() {
        return riboflavin;
    }

    public void setRiboflavin(Nutrient riboflavin) {
        this.riboflavin = riboflavin;
    }

    public Nutrient getNiacin() {
        return niacin;
    }

    public void setNiacin(Nutrient niacin) {
        this.niacin = niacin;
    }

    public Nutrient getVitaminB6() {
        return vitaminB6;
    }

    public void setVitaminB6(Nutrient vitaminB6) {
        this.vitaminB6 = vitaminB6;
    }

    public Nutrient getFolate() {
        return folate;
    }

    public void setFolate(Nutrient folate) {
        this.folate = folate;
    }

    public Nutrient getVitaminB12() {
        return vitaminB12;
    }

    public void setVitaminB12(Nutrient vitaminB12) {
        this.vitaminB12 = vitaminB12;
    }

    public Nutrient getVitaminD() {
        return vitaminD;
    }

    public void setVitaminD(Nutrient vitaminD) {
        this.vitaminD = vitaminD;
    }

    public Nutrient getVitaminE() {
        return vitaminE;
    }

    public void setVitaminE(Nutrient vitaminE) {
        this.vitaminE = vitaminE;
    }

    public Nutrient getVitaminK() {
        return vitaminK;
    }

    public void setVitaminK(Nutrient vitaminK) {
        this.vitaminK = vitaminK;
    }

    public Nutrient getWater() {
        return water;
    }

    public void setWater(Nutrient water) {
        this.water = water;
    }
    public List<Nutrient> getAllNutrients() {
        List<Nutrient> allNutrients = new ArrayList<>();
        allNutrients.add(energy);
        allNutrients.add(fat);
        allNutrients.add(saturatedFat);
        allNutrients.add(transFat);
        allNutrients.add(monounsaturatedFat);
        allNutrients.add(polyunsaturatedFat);
        allNutrients.add(carbs);
        allNutrients.add(netCarbs);
        allNutrients.add(fiber);
        allNutrients.add(sugars);
        allNutrients.add(protein);
        allNutrients.add(cholesterol);
        allNutrients.add(sodium);
        allNutrients.add(calcium);
        allNutrients.add(magnesium);
        allNutrients.add(potassium);
        allNutrients.add(iron);
        allNutrients.add(zinc);
        allNutrients.add(phosphorus);
        allNutrients.add(vitaminA);
        allNutrients.add(vitaminC);
        allNutrients.add(thiamin);
        allNutrients.add(riboflavin);
        allNutrients.add(niacin);
        allNutrients.add(vitaminB6);
        allNutrients.add(folate);
        allNutrients.add(vitaminB12);
        allNutrients.add(vitaminD);
        allNutrients.add(vitaminE);
        allNutrients.add(vitaminK);
        allNutrients.add(water);
        return allNutrients;
    }

}
