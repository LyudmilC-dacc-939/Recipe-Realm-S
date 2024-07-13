package Recipe_Realm.Project.repository;


import Recipe_Realm.Project.enums.Role;
import Recipe_Realm.Project.model.Comment;
import Recipe_Realm.Project.model.Recipe;
import Recipe_Realm.Project.model.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

import java.time.Instant;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RecipeRepository recipeRepository;

    private Comment comment;
    private User user;

    @BeforeEach
    void setUp() {
        //Action
        user = new User();
        user.setEmail("Chincho@abv.bg");
        user.setUsername("chinchoo00");
        user.setPassword("chinchoMishoka");
        user.setRole(Role.USER);
        userRepository.save(user);

        Recipe recipe = new Recipe();
        recipe.setCategory("Balkan");
        recipe.setIngredients("test ingredients");
        recipe.setDescription("test description");
        recipe.setTitle("Kebab with rice-filled Peppers");
        recipe.setCreatedAt(Instant.now());
        recipe.setUser(user);
        recipeRepository.save(recipe);

        comment = Comment.builder()
                .text("This is a test comment")
                .createdAt(Instant.now())
                .likes(4L)
                .dislikes(8L)
                .recipe(recipe)
                .user(user)
                .build();

    }

    @Test
    @DisplayName("Test 1: Save Comment Test")
    @Order(1)
    //@Rollback(value = false)
    //@Transactional
    public void saveCommentTest() {
        commentRepository.save(comment);

        Comment existingComment = commentRepository.getReferenceById(user.getId());
        //Verify
        Assertions.assertNotNull(existingComment.getId());
    }

    @Test
    @DisplayName("Test 2: Find By Id")
    @Order(2)
    public void findById_returnsNull() {

        //Verify
        Assert.state(commentRepository.findById(2L).isEmpty(), "Yes it is");
    }
}