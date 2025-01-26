import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import model.Category;
import model.Option;
import model.Product;
import model.Value;

import java.util.List;
import java.util.Scanner;

public class ProductCreate {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();
        Scanner scanner = new Scanner(System.in);

        TypedQuery<Category> query = entityManager.createQuery("from Category", Category.class);

        List<Category> categories = query.getResultList();

        for (Category category : categories) {
            System.out.println(category.getId() + ". " + category.getName());
        }

        System.out.print("Выберите категорию: ");
        int categoryId = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите название товара: ");
        String name = scanner.nextLine();

        System.out.println("Введите стоимость товара: ");
        int price = Integer.parseInt(scanner.nextLine());

        Category category = entityManager.find(Category.class, categoryId);
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);

        for (Option option: category.getOptions()) {

        }

        List<Value> values = category.getOptions().stream()
                .map(option -> {
                    System.out.print(option.getName() + ": ");
                    String valueName = scanner.nextLine();
                    Value value = new Value();
                    value.setProduct(product);
                    value.setOption(option);
                    value.setName(valueName);
                    product.addValues(value);

                    return value;
                }).toList();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(product);
            values.forEach(entityManager::persist);
            entityManager.getTransaction().commit();

            System.out.println("Товар создан.");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }
}
