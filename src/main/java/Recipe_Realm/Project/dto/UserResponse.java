package Recipe_Realm.Project.dto;

import Recipe_Realm.Project.enums.Role;
import Recipe_Realm.Project.model.Comment;
import Recipe_Realm.Project.model.Recipe;
import lombok.Data;

import java.time.Instant;
import java.util.Set;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String password;
    private String eMail;
    private String profilePicture;
    private Instant createdAt;
    private Role role;
    private Set<Recipe> recipes;
    private Set<Comment> comments;
    private Set<Recipe> favorites;
}
