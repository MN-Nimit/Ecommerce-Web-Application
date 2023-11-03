package com.backend.ecommerce.service.impl;

import com.backend.ecommerce.config.JwtProvider;
import com.backend.ecommerce.entity.User;
import com.backend.ecommerce.exception.UserException;
import com.backend.ecommerce.repository.UserRepository;
import com.backend.ecommerce.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    final private UserRepository userRepository;
    final private JwtProvider jwtProvider;

    public UserServiceImpl(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public User findUserById(Long userId) throws UserException {
        return userRepository.findById(userId).get();
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        String email = jwtProvider.getEmailFromToken(jwt);
        User user = findUserByEmail(email);

        if(user == null){
            throw new UserException("User not found with email " + email);
        }
        return user;
    }
}
