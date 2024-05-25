package com.example.makpakde.EdamamAPI;

import com.google.gson.annotations.SerializedName;


public class SingleRecipeResponse {
    @SerializedName("recipe")
    private Recipe recipe;

    public Recipe getSingleRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
