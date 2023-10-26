package com.backend.ecommerce.controller;

import com.backend.ecommerce.entity.CartItem;
import com.backend.ecommerce.entity.User;
import com.backend.ecommerce.exception.CartItemException;
import com.backend.ecommerce.exception.UserException;
import com.backend.ecommerce.response.ApiResponse;
import com.backend.ecommerce.service.CartItemService;
import com.backend.ecommerce.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cart_items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private UserService userService;

    @DeleteMapping("/{cartItemId}")
    @Operation(description = "Remove Cart Item From Cart")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Delete Item")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartItemId,
                                                      @RequestHeader("Authorization") String jwt) throws UserException, CartItemException{

        User user = userService.findUserProfileByJwt(jwt);
        cartItemService.removeCartItem(user.getId(),cartItemId);

        ApiResponse res = new ApiResponse();
        res.setMessage("item delete from cart");
        res.setStatus(true);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable Long cartItemId, @RequestBody CartItem cartItem,
                                                   @RequestHeader("Authorization") String jwt) throws UserException,CartItemException{
        User user = userService.findUserProfileByJwt(jwt);

        CartItem res = cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);

        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
