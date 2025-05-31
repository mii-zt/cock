import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        List<Recipe> recipes = Recipeloader.loadRecipes("recipes.txt"); // レシピを読み込む

        Scanner scanner = new Scanner(new InputStreamReader(System.in, "Shift_JIS"));

        Set<String> recentMeals = new HashSet<>();
        System.out.println("最近食べた料理を入力してください（最大６品目、カンマ区切り、終了は'end'）");
        while (recentMeals.size() < 6) {
            String input = scanner.nextLine().trim();
            if (input.equals("end"))
                break;
            String[] meals = input.split(",");
            for (String meal : meals) {
                recentMeals.add(meal.trim()); // 最近食べた料理セットに追加
            }
        }

        Set<String> userIngredients = new HashSet<>();
        System.out.println("持っている食材を入力してください（カンマ区切り、終了は'end'）");
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equals("end"))
                break;
            String[] ingredients = input.split(",");
            for (String ingredient : ingredients) {
                userIngredients.add(ingredient.trim()); // ユーザーの食材セットに追加
            }

        }

        System.out.println("\n料理名と必要な食材を表示します。");
        List<Recipe> cookable = Recipecheck.checkCookableRecipes(recipes, new ArrayList<>(userIngredients),
                recentMeals);
        if (cookable.isEmpty()) {
            System.out.println("条件に合う料理はありません。");
        } else {
            for (Recipe r : cookable) {
                System.out.print(r.getName() + ": ");
                for (String ing : r.getIngredients()) {
                    System.out.print(ing + " ");
                }
                System.out.println();
            }
        }
        scanner.close();

    }

}
