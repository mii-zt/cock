import java.util.*;

public class Recipecheck {
    public static List<Recipe> checkCookableRecipes(List<Recipe> allRecipes, List<String> userIngredients) {
        List<Recipe> cookable = new ArrayList<>(); // つくれるレシピのリスト
        for (Recipe recipe : allRecipes) {
            boolean match = false;
            // レシピの食材がユーザーの持っている食材に含まれているかチェック
            for (String ingredient : recipe.getIngredients()) {
                if (userIngredients.contains(ingredient)) {
                    match = true;
                    break;
                }
            }
            // ユーザーが持っている食材の中にレシピの食材が含まれていない場合、必要な食材をリストに追加
            if (match) {
                Recipe necessary = new Recipe(recipe.getName());
                for (String ingredient : recipe.getIngredients()) {
                    if (!userIngredients.contains(ingredient)) {
                        necessary.addIngredient(ingredient);
                    }
                }
                cookable.add(necessary); // つくれるレシピのリストに追加
            }
        }
        return cookable;
    }
}
