package com.homeproject.helper;

import com.homeproject.models.User;
import com.homeproject.processing.GenerateUser;
import com.homeproject.processing.GenerateUserApi;
import com.homeproject.worker.HttpWorker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StorageUsers {

    private Random random=new Random();
    private List<User> users = new ArrayList();
    private GenerateUser generateUser =new GenerateUser();
    private GenerateUserApi generateUserApi=new GenerateUserApi();
    private int count= 1+random.nextInt(30);

    public void populate() {

        for(int i=0;i<count;i++) {
            StringBuffer httpResponse = new HttpWorker().getResponse();

            if (httpResponse != null) {
                users.add(generateUserApi.getUserFromJSON(httpResponse,i));// добавляем из randomuser/api
            } else {
                users.add(generateUser.getUserFromLocalDatabase(i)); // добавляем из локальной базы пользователя
            }
        }
    }

    public List<User> get(){
        return users;
    }
}
