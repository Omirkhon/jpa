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

public class ProductUpdate {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите id товара: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Введите новое название товара: ");
        String name = scanner.nextLine();
        System.out.println("Введите новую стоимость товара: ");
        int price = Integer.parseInt(scanner.nextLine());

        Product product = entityManager.find(Product.class, id);
        product.setName(name);
        product.setPrice(price);

        List<Option> options = product.getCategory().getOptions();

        for (Option option : options) {
            System.out.print(option.getName() + ": ");
            String valueName = scanner.nextLine();
            Value foundValue = null;
            for (Value value : option.getValues()) {
                if (value.getProduct().getId() == product.getId()) {
                    value.setName(valueName);
                    foundValue = value;
                    break;
                }
            }

            if (foundValue == null) {
                System.out.println(option.getName() + "[null]: ");
                foundValue = new Value();
                foundValue.setProduct(product);
                foundValue.setOption(option);
            }
        }

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(product);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }
}
