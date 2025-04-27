import controllers.OwnerController;
import dao.OwnerDao;
import dao.PetDao;
import models.ColorPet;
import models.Owner;
import models.Pet;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import util.HibernateSessionFactoryUtil;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
class Tests {

    @Container
    private static final PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:15-alpine")
                    .withDatabaseName("db_test")
                    .withUsername("user_test")
                    .withPassword("pass_test");

    @BeforeAll
    static void beforeAll() {
        System.setProperty("hibernate.connection.url", postgres.getJdbcUrl());
        System.setProperty("hibernate.connection.username", postgres.getUsername());
        System.setProperty("hibernate.connection.password", postgres.getPassword());
    }

    @AfterAll
    static void afterAll() {
        HibernateSessionFactoryUtil.shutdown();
    }

    @Test
    void saveAndFindOwner_ShouldWork() {
        OwnerDao dao = new OwnerDao();

        Owner owner = new Owner();
        owner.setName("Мефодий");
        owner.setBirthDate(LocalDate.of(2005, 9, 23));

        Owner savedOwner = (Owner) dao.save(owner);
        assertNotNull(savedOwner.getId());

        Owner foundOwner = (Owner) dao.getById(savedOwner.getId());
        assertEquals("Мефодий", foundOwner.getName());
    }

    @Test
    void savePetWithOwner_ShouldSetRelations() {
        OwnerDao ownerDao = new OwnerDao();
        Owner owner = new Owner();
        owner.setName("Абдималик");
        owner.setBirthDate(LocalDate.now());
        Owner savedOwner = (Owner) ownerDao.save(owner);

        PetDao petDao = new PetDao();
        Pet pet = new Pet();
        pet.setName("Рыжик");
        pet.setColorPet(ColorPet.yellow);
        pet.setOwner(savedOwner);
        Pet savedPet = (Pet) petDao.save(pet);

        assertNotNull(savedPet.getId());
        assertEquals(savedOwner.getId(), savedPet.getOwner().getId());
    }

    @Test
    void createAndGetOwner_ShouldWork() {
        OwnerController controller = new OwnerController();

        Owner owner = controller.createowner("Стасянчик", LocalDate.of(2005, 2, 19), List.of());
        Owner found = controller.getOwnerById(owner.getId());

        assertEquals("Стасянчик", found.getName());
    }
}