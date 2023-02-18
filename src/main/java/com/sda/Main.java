package com.sda;

import com.sda.controller.UserController;
import com.sda.dao.UsersDAO;
import com.sda.mapper.UserMapper;
import com.sda.service.UserService;
import model.User;

public class Main {

    public static void main(String[] args) {
        UsersDAO usersDAO=new UsersDAO();
        UserMapper userMapper= new UserMapper();
        UserService userService = new UserService(usersDAO,userMapper);
        UserController userController= new UserController(userService);
        User user = new User();
        user.setUsername("root1");
        userService.create(user);
        userController.findByUsername("root2");


    }

}
