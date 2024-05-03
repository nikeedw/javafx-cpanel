package org.example.loginapp;

import java.util.ArrayList;
import java.util.Arrays;

public class Database {
    static ArrayList<User> users = new ArrayList<>(Arrays.asList(
            new User("Edward", "123", "admin"),
            new User("John", "456", "user"),
            new User("Vicky", "789", "user")
    ));

    static ArrayList<User> getUsers() {
        return users;
    }

    static void addUser(User user) {
        users.add(user);
    }
}
