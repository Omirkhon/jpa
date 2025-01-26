package model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@ToString
@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "reviews")
public class Review {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
    boolean isPublished;
    int rating;
    String content;
    Date publicationDate;
}
