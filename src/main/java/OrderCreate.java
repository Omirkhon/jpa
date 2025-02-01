import enums.OrderStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import model.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.*;

public class OrderCreate {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();
        Scanner scanner = new Scanner(System.in);

        TypedQuery<Product> query = entityManager.createQuery("from Product", Product.class);

        List<Product> products = query.getResultList();

        System.out.print("Введите логин: ");
        String login = scanner.nextLine();

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        TypedQuery<User> queryUser = entityManager.createQuery("from User where login = ?1", User.class);
        queryUser.setParameter(1, login);

        User user = queryUser.getSingleResult();

        boolean isCorrectPassword = BCrypt.checkpw(password, user.getPassword());

        if (isCorrectPassword) {
            for (Product product : products) {
                System.out.println(product.getId() + ". " + product.getName()
                        + " - " + product.getPrice());
            }

            System.out.println("Выберите товары (через запятую и пробел)");
            String orders = scanner.nextLine();
            String[] split = orders.split(", ");

            List<Integer> integers = Arrays.stream(split)
                    .map(Integer::parseInt).toList();

            TypedQuery<Product> queryProduct = entityManager.createQuery("from Product where id in (?1)", Product.class);
            queryProduct.setParameter(1, integers);

            List<Product> selectedProducts = queryProduct.getResultList();

            System.out.print("Введите адрес: ");
            String address = scanner.nextLine();

            Order order = new Order();
            order.setAddress(address);
            order.setUser(user);
            order.setStatus(OrderStatus.ORDERED);

            Map<Product, Integer> quantityMap = new HashMap<>();

            List<OrderProduct> orderProducts = selectedProducts.stream()
                    .map(product -> {
                        OrderProduct op = new OrderProduct();

                        op.setOrder(order);

                        if (quantityMap.containsKey(product)) {
                            quantityMap.put(product, quantityMap.get(product)+1);
                        } else {
                            quantityMap.put(product, 1);
                        }

                        op.setProduct(product);
                        op.setQuantity(quantityMap.get(product));

                        return op;
                    }).toList();

            try {
                entityManager.getTransaction().begin();
                entityManager.persist(order);

                orderProducts.forEach(entityManager::persist);

                entityManager.getTransaction().commit();
                System.out.println("Заказ создан.");
                int finalPrice = 0;

                System.out.print("Итог: ");
                for (Product product : selectedProducts) {
                    finalPrice += product.getPrice();
                }
                System.out.println(finalPrice);
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
                System.out.println(e.getMessage());
            }
        } else {
            throw new RuntimeException();
        }
    }
}
