package org.example.loginapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Login {
    @FXML
    private Label error;
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    public void userLogin() throws IOException {
        checkLogin();
    }

    @FXML
    private void handleKeyPress() {
        password.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    checkLogin();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        });
    }

    private void checkLogin() throws IOException {
        String inputUsername = username.getText().trim();
        String inputPassword = password.getText().trim();

        if (inputUsername.isEmpty() || inputPassword.isEmpty()) {
            error.setText("Пожалуйста, введите ваши данные!");
            return;
        }

        ArrayList<User> users = Database.getUsers();
        Iterator<User> iterator = users.listIterator();

        boolean userFound = false;
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getUsername().equals(inputUsername) && user.getPassword().equals(inputPassword)) {
                userFound = true;
                if(user.getRole().equals("admin")) {
                    error.setText("Success!");
                    Main m = new Main();
                    m.changeScene("cpanel.fxml");
                }
                if(user.getRole().equals("user")) {
                    error.setText("Success!");
                    Main m = new Main();
                    m.changeScene("after-login.fxml");
                }
                break;
            }
        }

        if (!userFound) {
            error.setText("Неверный логин или пароль!");
        }
    }
}
