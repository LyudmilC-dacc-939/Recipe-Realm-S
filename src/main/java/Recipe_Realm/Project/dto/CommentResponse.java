package Recipe_Realm.Project.dto;

import Recipe_Realm.Project.model.Recipe;
import Recipe_Realm.Project.model.User;
import lombok.Data;

import java.time.Instant;

@Data
public class CommentResponse {

    private Long id;
    private String text;
    private Instant createdAt;
    private Long likes;
    private Long dislikes;
    private User user;
    private Recipe recipe;
}
