package model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int id;
    String name;
    int price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    @OneToMany(mappedBy = "product")
    final List<Value> values = new ArrayList<>();

    @ManyToMany
    final List<Order> orders = new ArrayList<>();

    public void addValues(Value value) {
        values.add(value);
    }
}