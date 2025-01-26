package model;

import enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ToString
@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "orders")
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @ManyToMany
    final List<Product> products = new ArrayList<>();
    @Enumerated(EnumType.ORDINAL)
    OrderStatus status;
    String address;
    Date orderDate;
}
