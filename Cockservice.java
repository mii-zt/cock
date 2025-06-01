import java.util.*;

public class Cockservice {
    public static List<Recipe> recommend(String[] userIngredients, String[] dislikedIngredients) {
        List<Recipe> recipes = Recipeloader.loadRecipes("recipes.txt"); // レシピを読み込む
        Set<String> recentMealsSet = new HashSet<>(Arrays.asList(dislikedIngredients));
        Set<String> userIngredientsSet = new HashSet<>(Arrays.asList(userIngredients));

        // 料理可能なレシピをチェック
        return Recipecheck.checkCookableRecipes(recipes, new ArrayList<>(userIngredientsSet), recentMealsSet);
    }
}
