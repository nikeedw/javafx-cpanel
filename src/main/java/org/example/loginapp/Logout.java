package org.example.loginapp;

import java.io.IOException;

public class Logout {
    public void userLogout() throws IOException {
        Main m = new Main();
        m.changeScene("hello-view.fxml");
    }
}
