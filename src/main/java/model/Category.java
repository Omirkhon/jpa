package model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;

    @OneToMany(mappedBy = "category")
    final List<Product> product = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    final List<Option> options = new ArrayList<>();

    public void addOption(Option option) {
        options.add(option);
    }
}
