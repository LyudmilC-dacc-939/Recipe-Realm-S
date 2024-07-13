package Recipe_Realm.Project.converter;

import Recipe_Realm.Project.dto.CommentRequest;
import Recipe_Realm.Project.model.Comment;
import Recipe_Realm.Project.repository.RecipeRepository;
import Recipe_Realm.Project.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class CommentConverter {

    private UserRepository userRepository;
    private RecipeRepository recipeRepository;

    public CommentConverter(UserRepository userRepository, RecipeRepository recipeRepository) {
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
    }

    public Comment toComment(CommentRequest commentRequest) {
        Comment comment = new Comment();
        comment.setCreatedAt(Instant.now());
        comment.setText(commentRequest.getText());
        comment.setUser(userRepository.getReferenceById(commentRequest.getUserId()));
        comment.setRecipe(recipeRepository.getReferenceById(commentRequest.getRecipeId()));
        return comment;
    }
}
