package Recipe_Realm.Project.service;


import Recipe_Realm.Project.dto.RecipeCommentsResponse;
import Recipe_Realm.Project.dto.RecipeRequest;
import Recipe_Realm.Project.dto.RecipeResponse;
import Recipe_Realm.Project.dto.RecipeUpdateRequest;
import Recipe_Realm.Project.model.Recipe;
import Recipe_Realm.Project.model.User;

import java.util.List;
import java.util.Set;

public interface RecipeService {

    RecipeResponse createRecipe(RecipeRequest recipeRequest);

    RecipeResponse getRecipe(Long id);

    RecipeResponse updateRecipe(RecipeUpdateRequest recipeUpdateRequest, Long id);

    void deleteRecipe(Long id);

    RecipeCommentsResponse likeRecipe(Long recipeId, Long userId);

    RecipeCommentsResponse dislikeRecipe(Long recipeId, Long userId);

    RecipeCommentsResponse getAllCommentsFromRecipe(Long id);

    Set<User> getUsers(Long id);

    List<Recipe> findRecipeBy(String title, String description, String category);
}
