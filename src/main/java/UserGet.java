import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Scanner;

public class UserGet {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите логин: ");
        String login = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        TypedQuery<User> query = entityManager.createQuery("from User where login = ?1", User.class);
        query.setParameter(1, login);

        User user = query.getSingleResult();

        boolean isCorrectPassword = BCrypt.checkpw(password, user.getPassword());

        if (isCorrectPassword) {
            System.out.println("ID: " + user.getId() + " Логин: " + user.getLogin() +
                    " Роль: " + user.getRole() + " Дата: " + user.getRegistrationDate());
        } else {
            throw new RuntimeException();
        }
    }
}
