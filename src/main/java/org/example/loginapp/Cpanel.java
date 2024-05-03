package org.example.loginapp;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class Cpanel {
    @FXML
    private TextField newUsernameField;
    @FXML
    private TextField newPasswordField;
    @FXML
    private RadioButton adminRadioButton;
    @FXML
    private TableView<User> userTableView;
    @FXML
    private TableColumn<User, String> usernameColumn;
    @FXML
    private TableColumn<User, String> passwordColumn;
    @FXML
    private TableColumn<User, String> roleColumn;

    public void initialize() {
        usernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        passwordColumn.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
        roleColumn.setCellValueFactory(cellData -> cellData.getValue().roleProperty());

        userTableView.setItems(FXCollections.observableArrayList(Database.getUsers()));
    }

    public void adminLogout() {
        try {
            Main m = new Main();
            m.changeScene("hello-view.fxml");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            addUser();
        }
    }

    public void addUser() {
        String username = newUsernameField.getText().trim();
        String password = newPasswordField.getText().trim();
        String role = adminRadioButton.isSelected() ? "admin" : "user";

        if (!username.isEmpty() && !password.isEmpty()) {
            User newUser = new User(username, password, role);
            Database.addUser(newUser);
            initialize();
            newUsernameField.setText("");
            newPasswordField.setText("");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter username and password.");
            alert.showAndWait();
        }
    }
}
