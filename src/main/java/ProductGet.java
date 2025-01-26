import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import model.Product;

import java.util.List;
import java.util.Scanner;

public class ProductGet {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите минимальное значение диапазона: ");
        int min = scanner.nextInt();
        System.out.print("Введите максимальное значение диапазона: ");
        int max = scanner.nextInt();

        TypedQuery<Product> query = entityManager.createQuery("select p from Product p where p.price between ?1 and ?2", Product.class);
        query.setParameter(1, min);
        query.setParameter(2, max);

        List<Product> products = query.getResultList();

        System.out.println(products);
    }
}
