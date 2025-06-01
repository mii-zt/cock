import java.util.*;

public class Recipe {
    private String name; // 料理の名前
    private List<String> ingredients; // 食材の一覧

    public Recipe(String name) {
        this.name = name;
        this.ingredients = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void addIngredient(String ingredient) {
        ingredients.add(ingredient.trim());
    }
}