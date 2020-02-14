package com.user.service.service;

import com.user.service.domain.User;

public interface UserService {
    User registerUser(User input);

    Iterable<User> findAll();

    User findById(Long id);

}