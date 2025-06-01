import java.util.*;

public class Cockservice {
    public static List<Recipe> recommend(String[] userIngredients, String[] dislikedIngredients, String[] allergy) {
        // アレルギーがない時
        if (allergy == null || allergy.length == 0) {
            allergy = new String[] { "" }; // アレルギーがない場合は空文字列をセット
        }

        List<Recipe> recipes = Recipeloader.loadRecipes("recipes.txt"); // レシピを読み込む
        Set<String> recentMealsSet = new HashSet<>(Arrays.asList(dislikedIngredients));
        Set<String> userIngredientsSet = new HashSet<>(Arrays.asList(userIngredients));
        Set<String> allergySet = new HashSet<>(Arrays.asList(allergy)); // アレルギー食材のセット

        // 料理可能なレシピをチェック
        return Recipecheck.checkCookableRecipes(recipes, new ArrayList<>(userIngredientsSet), recentMealsSet,
                allergySet);
    }
}
