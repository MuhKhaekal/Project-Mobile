package com.example.makpakde.EdamamAPI;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class RecipeResponse {

    @SerializedName("hits")
    private List<Hit> hits;

    public List<Hit> getHits() {
        return hits;
    }

    public void setHits(List<Hit> hits) {
        this.hits = hits;
    }

    public static class Hit {

        @SerializedName("recipe")
        private Recipe recipe;

        public Recipe getRecipe() {
            return recipe;
        }

        public void setRecipe(Recipe recipe) {
            this.recipe = recipe;
        }
    }
}
