package model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import model.Category;

import java.util.List;

@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "options")
public class Option {
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Id
    int id;
    String name;
    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    @OneToMany(mappedBy = "option")
    List<Value> values;
}
