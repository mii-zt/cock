import java.io.*;
import java.util.*;

public class Recipeloader {
    public static List<Recipe> loadRecipes(String filename) {
        List<Recipe> recipes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 2)
                    continue;
                Recipe recipe = new Recipe(parts[0]); // 料理の名前を設定
                // 食材を追加
                for (int i = 1; i < parts.length; i++) {
                    recipe.addIngredient(parts[i]);
                }
                recipes.add(recipe);

            }

        } catch (IOException e) {
            System.err.println("ファイル読み込みエラー: " + e.getMessage());
        }
        return recipes;
    }
}
