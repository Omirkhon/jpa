import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import model.Category;
import model.Product;
import model.Value;

import java.util.List;

public class CategoryGet {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();

        TypedQuery<Category> query = manager.createQuery("from Category", Category.class);

        List<Category> categories = query.getResultList();

        for (Category category : categories) {
            System.out.println(category.getId() + ". " + category.getName());
            for (Product product : category.getProduct()) {
                System.out.println(product.getName() + " " + product.getPrice());
                for (Value value : product.getValues()) {
                    System.out.println("- " + value.getOption().getName()
                            + " " + value.getName());
                }
            }
        }
    }
}