package com.backend.ecommerce.service.impl;

import com.backend.ecommerce.entity.Cart;
import com.backend.ecommerce.entity.CartItem;
import com.backend.ecommerce.entity.Product;
import com.backend.ecommerce.entity.User;
import com.backend.ecommerce.exception.CartItemException;
import com.backend.ecommerce.exception.UserException;
import com.backend.ecommerce.repository.CartItemRepository;
import com.backend.ecommerce.repository.CartRepository;
import com.backend.ecommerce.service.CartItemService;
import com.backend.ecommerce.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {

    final private CartItemRepository cartItemRepository;
    final private UserService userService;
    final private CartRepository cartRepository;

    public CartItemServiceImpl(CartItemRepository cartItemRepository, UserService userService, CartRepository cartRepository) {
        this.cartItemRepository = cartItemRepository;
        this.userService = userService;
        this.cartRepository = cartRepository;
    }

    @Override
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice() * cartItem.getQuantity());

        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
        CartItem item = findCartItemById(id);
        User user = userService.findUserById(item.getUserId());

        if (user.getId().equals(userId)){
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(item.getQuantity() * item.getProduct().getPrice());
            item.setDiscountedPrice(item.getProduct().getDiscountedPrice() * item.getQuantity());
        }
        return cartItemRepository.save(item);
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
        return cartItemRepository.isCartItemExist(cart, product, size, userId);
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
        CartItem cartItem = findCartItemById(cartItemId);
        User user = userService.findUserById(cartItem.getUserId());
        User reqUser = userService.findUserById(userId);

        if(user.getId().equals(reqUser.getId())){
            cartItemRepository.deleteById(cartItemId);
        } else {
          throw  new UserException("You cannot remove other user's items");
        }
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        Optional<CartItem> optional = cartItemRepository.findById(cartItemId);

        if(optional.isPresent()){
            return optional.get();
        }
        throw new CartItemException("Cart item not found with id : " + cartItemId);
    }
}