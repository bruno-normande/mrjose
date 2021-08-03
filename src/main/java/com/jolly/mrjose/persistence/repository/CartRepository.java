package com.jolly.mrjose.persistence.repository;

import com.jolly.mrjose.persistence.model.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Long> {
}
