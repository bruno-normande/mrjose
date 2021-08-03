package com.jolly.mrjose.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    @Id
    private long id;
    @ManyToOne
    private Product product;
    //TODO: Create class to map measuring (TS to grams, etc)
    private int quantityInGrams;
    @ManyToOne
    @JsonIgnore  // TODO: doesn't seem good to add this in the model, we should make our onw json parser
    private Recipe recipe;
}
