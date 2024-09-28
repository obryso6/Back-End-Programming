package com.example.D288.services;

import com.example.D288.entities.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Purchase {

    private Customer customer;
    private Set<CartItem> cartItems = new HashSet<>();
    private Cart cart;
    private Set<Excursion> excursions = new HashSet<>();
}