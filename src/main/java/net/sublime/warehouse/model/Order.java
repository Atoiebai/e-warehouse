package net.sublime.warehouse.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "order_list")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    Date createdAt = new Date();

    @OneToMany(cascade = CascadeType.ALL)
    List<ProductBundle> productBundle = new ArrayList<>();


}
