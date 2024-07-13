package Recipe_Realm.Project.dto;

import Recipe_Realm.Project.model.Comment;
import Recipe_Realm.Project.model.User;
import lombok.Data;

import java.time.Instant;
import java.util.Set;

@Data
public class RecipeResponse {

    private Long id;
    private String title;
    private String description;
    private String ingredients;
    private String category;
    private Instant createdAt;
    private Long likes;
    private Long dislikes;
    private User user;
    private Set<Comment> comments;
    private Set<User> usersFavorite;
}
