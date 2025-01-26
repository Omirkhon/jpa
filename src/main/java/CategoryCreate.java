import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Category;
import model.Option;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CategoryCreate {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите название категории: ");
        String name = scanner.nextLine();

        System.out.print("Введите названия характеристики (через запятую и пробел): ");
        String optionString = scanner.nextLine();

        String[] split = optionString.split(", ");

        Category category = new Category();
        category.setName(name);

        List<Option> options = Arrays.stream(split)
                .map(optionName -> {
                    Option option = new Option();
                    option.setName(optionName);
                    option.setCategory(category);

                    return option;
                }).toList();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(category);

            options.forEach(entityManager::persist);

            entityManager.getTransaction().commit();
            System.out.println("Категория создана.");
            System.out.println(category);
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }
}
