package com.jolly.mrjose.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JsonIgnore // TODO: doesn't seem good to add this in the model, we should make our onw json parser
    private Cart cart;
    @OneToOne
    private Product product;
    // TODO: add measuring unit
    private int quantityInGrams;

    public void addQuantityInGrams(int quantityInGrams) {
        this.quantityInGrams += quantityInGrams;
    }
}
