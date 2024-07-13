package Recipe_Realm.Project.repository;

import Recipe_Realm.Project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    User getReferenceById(Long id);

    Optional<User> findByUsername(String username);

    User getByEmail(String email);

    User getByUsername(String username);
    //todo add test for getByUsername
}
