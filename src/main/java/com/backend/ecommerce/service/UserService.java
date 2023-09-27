package com.backend.ecommerce.service;

import com.backend.ecommerce.entity.User;
import com.backend.ecommerce.exception.UserException;

public interface UserService {

    public User findUserById(Long userId) throws UserException;

    public User findUserByEmail(String email);

    public User saveUser(User user);
}
