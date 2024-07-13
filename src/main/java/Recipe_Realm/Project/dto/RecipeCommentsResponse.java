package Recipe_Realm.Project.dto;

import Recipe_Realm.Project.model.Comment;
import lombok.Data;

import java.util.Set;

@Data
public class RecipeCommentsResponse {
    private Long id;
    private String title;
    private Long likes;
    private Long dislikes;
    private Set<Comment> comments;
}