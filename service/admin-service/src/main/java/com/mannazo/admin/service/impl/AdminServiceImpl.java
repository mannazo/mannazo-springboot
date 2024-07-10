package com.mannazo.admin.service.impl;

import com.mannazo.admin.client.PostServiceClient;
import com.mannazo.admin.client.UserServiceClient;
import com.mannazo.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {


    private final UserServiceClient userServiceClient;
    private final PostServiceClient postServiceClient;


    @Override
    public int getNumberOfPosts() {
        int posts = postServiceClient.getNumberOfPosts();
        return posts;
    }

    @Override
    public int getNumberOfUsers(String nationality) {
        int users = userServiceClient.getNumberOfUsers(nationality);
        return users;
    }

    @Override
    public int getNumberOfAllUsers() {
        int Allusers = userServiceClient.getNumberOfAllUsers();
        return Allusers;
    }
}
