package com.heritage.service;

import com.heritage.jpa.UserJpa;
import com.heritage.model.UserEntity;
import com.heritage.pojo.User;
import com.heritage.util.Heritageconstants;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Hari Pathuri
 * 5/17/2022 5:23 PM
 */

@Service
public class UserServiceImpl implements IUserService {

    private UserJpa userJpa;

    @Autowired
    public UserServiceImpl(UserJpa userJpa) {
        this.userJpa = userJpa;
    }

    /**
     * @param user
     */
    @Override
    public User createUser(User user) {

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        userEntity.setActive(Heritageconstants.Y);
        userEntity = userJpa.save(userEntity);
        BeanUtils.copyProperties(userEntity, user);
        return user;
    }

    /**
     * @param user
     */
    @Override
    public void updateUser(User user) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        userEntity.setActive(Heritageconstants.Y);
        userJpa.save(userEntity);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<User> getUserById(Long id) {
        UserEntity userEntity = userJpa.findUserEntityById(id);
        if (ObjectUtils.isNotEmpty(userEntity)) {
            User user = new User();
            BeanUtils.copyProperties(userEntity, user);
            return Collections.singletonList(user);
        }
        return Collections.emptyList();
    }

    /**
     * @return
     */
    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        userJpa.findAll().forEach(entity -> {
            User user = new User();
            BeanUtils.copyProperties(entity, user);
            userList.add(user);
        });
        return userList;
    }

    /**
     * @param id
     */
    @Override
    public void delete(Long id) {
        userJpa.deleteById(id);
    }
}
