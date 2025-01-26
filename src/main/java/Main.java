import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import model.Category;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();

        System.out.println(entityManager.isOpen());

        TypedQuery<Category> query = entityManager.createQuery("select c from Category c", Category.class);
        List<Category> categories = query.getResultList();
        for (Category category : categories) {
            System.out.println(category);
        }
    }
}
