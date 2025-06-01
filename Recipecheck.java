import java.util.*;

public class Recipecheck {
    public static List<Recipe> checkCookableRecipes(List<Recipe> allRecipes, List<String> userIngredients,
            Set<String> recentMeals, Set<String> allergy) {
        List<Recipe> cookable = new ArrayList<>(); // つくれるレシピのリスト
        for (Recipe recipe : allRecipes) {
            boolean match = false;
            // レシピの食材がユーザーの持っている食材に含まれているかチェック
            if (recentMeals.contains(recipe.getName())) {
                continue; // 最近食べた料理はスキップ
            }
            for (String ingredient : recipe.getIngredients()) {
                if (allergy.contains(ingredient)) {
                    match = false; // アレルギー食材が含まれている場合はスキップ
                    break;
                }
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
        // 必要な食材の数でソート
        cookable.sort(Comparator.comparingInt(r -> r.getIngredients().size()));
        return cookable;
    }
}
