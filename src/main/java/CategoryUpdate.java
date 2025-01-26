import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Category;

import java.util.Scanner;

public class CategoryUpdate {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите идентификатор категории: ");
        int id = scanner.nextInt();

        Category category = entityManager.find(Category.class, id);

        System.out.print("Введите новое название категории " + category.getName() + ": ");
        String name = scanner.next();
        category.setName(name);
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(category);
            entityManager.getTransaction().commit();
            System.out.println(category);
        } catch (Exception e) {
            System.out.print("Категория с id=" + id + "не существует.");
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }
}
