package com.example.D288.services;

import com.example.D288.dao.CartRepository;
import com.example.D288.dao.CustomerRepository;
import com.example.D288.entities.Cart;
import com.example.D288.entities.CartItem;
import com.example.D288.entities.Customer;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository, CartRepository cartRepository) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
    }
    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        try {

            Cart cart = purchase.getCart();


            String orderTrackingNumber = generateOrderTrackingNumber();
            cart.setOrderTrackingNumber(orderTrackingNumber);


            Set<CartItem> cartItems = purchase.getCartItems();
            cartItems.forEach(item -> item.setCart(cart));


            cart.setCartItem(cartItems);

            Customer customer = purchase.getCustomer();
            cart.setCustomer(customer);
            cart.setStatus(Cart.StatusType.ordered);
            customerRepository.save(customer);
            cartRepository.save(cart);


            if (cartItems.isEmpty()) {
                throw new IllegalArgumentException("Customer cannot be null and cart items cannot be empty.");
            }


            return new PurchaseResponse(orderTrackingNumber);

        } catch (Exception e) {

            return new PurchaseResponse(e.getMessage());
        }
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}