package Recipe_Realm.Project.repository;

import Recipe_Realm.Project.enums.Role;
import Recipe_Realm.Project.model.Recipe;
import Recipe_Realm.Project.model.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;


import java.time.Instant;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class RecipeRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RecipeRepository recipeRepository;


    @BeforeEach
    void setUp() {
        //Action
        User user = new User();
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
    }

    @Test
    void getReferenceById() {
        User user = new User();
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
        Recipe existingRecipe = recipeRepository.getReferenceById(recipe.getId());
        Assertions.assertEquals(existingRecipe.getTitle(), recipe.getTitle());
    }

    @Test
    void findById() {
        User user = new User();
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
        Optional<Recipe> existingRecipe = recipeRepository.findById(recipe.getId());
        Assert.state(existingRecipe.isPresent(), "Recipe is not present, It is Null");
    }

    @Test
    void findByTitleOrDescriptionOrCategory() {
        List<Recipe> existingRecipes = recipeRepository.findByTitleOrDescriptionOrCategory("kebab", "", "");
        Assert.state(existingRecipes.toArray().length != 0, "List is Null!");
    }
}