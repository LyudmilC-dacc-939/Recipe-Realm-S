package Recipe_Realm.Project.converter;

import Recipe_Realm.Project.dto.UserRequest;
import Recipe_Realm.Project.enums.Role;
import Recipe_Realm.Project.model.User;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class UserConverter {

    public User toUser(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEMail());
        user.setPassword(userRequest.getPassword());
        user.setProfilePicture(userRequest.getProfilePicture());
        user.setCreatedAt(Instant.now());
        user.setRole(Role.USER);
        return user;
    }
}
