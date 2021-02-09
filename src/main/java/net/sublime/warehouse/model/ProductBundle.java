package net.sublime.warehouse.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class ProductBundle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private Long price;
    @Column(nullable = false)
    private Long count;


}
