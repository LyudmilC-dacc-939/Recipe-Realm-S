package Recipe_Realm.Project.service;

import Recipe_Realm.Project.dto.UserRequest;
import Recipe_Realm.Project.dto.UserResponse;
import Recipe_Realm.Project.model.Recipe;
import Recipe_Realm.Project.model.User;

import java.util.Set;


public interface UserService {

    UserResponse createUser(UserRequest userRequest);

    UserResponse getUserById(Long id);

    User updateUser(UserRequest userRequest, Long id);

    void deleteUser(Long id);

    Set<Recipe> getAllRecipesForUser (Long id);

    Set<Recipe> getAllFavoriteRecipesForUser(Long id);

    UserResponse addToFavorites(Long userId, Long recipeId);

    UserResponse removeFromFavorites(Long userId, Long recipeId);
    
}
