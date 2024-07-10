package com.mannazo.admin.service;

import java.util.List;
import java.util.UUID;

public interface AdminService {

    int getNumberOfPosts();

    int getNumberOfUsers(String nationality);

    int getNumberOfAllUsers();
}
