package Recipe_Realm.Project.converter;

import Recipe_Realm.Project.dto.RecipeRequest;
import Recipe_Realm.Project.model.Recipe;
import Recipe_Realm.Project.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class RecipeConverter {

    private UserRepository userRepository;

    public RecipeConverter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Recipe toRecipe(RecipeRequest recipeRequest) {
        Recipe recipe = new Recipe();
        recipe.setCategory(recipeRequest.getCategory());
        recipe.setDescription(recipeRequest.getDescription());
        recipe.setCreatedAt(Instant.now());
        recipe.setIngredients(recipeRequest.getIngredients());
        recipe.setTitle(recipeRequest.getTitle());
        recipe.setUser(userRepository.findById(recipeRequest.getUserId()).get());
        return recipe;
    }
}
