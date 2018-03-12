package com.example.restaurantmenus.service;

import com.example.restaurantmenus.entity.User;
import com.example.restaurantmenus.repository.RoleDao;
import com.example.restaurantmenus.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findById(Long id) {
        return userDao.findOne(id);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Transactional
    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.create(user);
    }

    @Transactional
    @Override
    public void update(Long id, User user) {
        userDao.update(id, user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        User user = userDao.findOne(id);
        userDao.delete(user);
    }
}
