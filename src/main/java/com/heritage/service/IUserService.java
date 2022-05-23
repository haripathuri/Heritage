package com.heritage.service;

import com.heritage.pojo.User;

import java.util.List;

/**
 * @author Hari Pathuri
 * 5/17/2022 5:21 PM
 */
public interface IUserService {

    User createUser(User user);
    void updateUser(User user);
    List<User> getUserById(Long Id);
    List<User> getAllUsers();
    void delete(Long id);
}
