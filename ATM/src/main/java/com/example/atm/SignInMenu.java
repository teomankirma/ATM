package com.example.atm;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class SignInMenu {


    static String usernameInput, passwordInput;
    static String a;

    public GridPane signIn(Stage stage1, Scene scene1, Button button4) {

        // Generating TextFields for getting username and surname for Sign In Scene.
        TextField signInUsernameText = new TextField();
        PasswordField signInPasswordText = new PasswordField();

        // TextField's size.
        signInUsernameText.setMaxSize(300, 100);
        signInPasswordText.setMaxSize(300, 100);


        Button signInButton = new Button("Sign In");


        // GridPane for TextFields.
        GridPane signInGridPane = new GridPane();
        signInGridPane.setBackground(new Background(new BackgroundFill(Color.DARKORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
        signInGridPane.add(new Label("Welcome back!"), 1, 0);
        signInGridPane.add(new Label("Username:     "), 0, 3);
        signInGridPane.add(signInUsernameText, 1, 3);
        signInGridPane.add(new Label("Password:     "), 0, 4);
        signInGridPane.add(signInPasswordText, 1, 4);


        // Alignment for GridPane.
        signInGridPane.setAlignment(Pos.CENTER);
        signInGridPane.setHgap(10);
        signInGridPane.setVgap(10);


        // HBox for buttons in sign in page.
        HBox signInHBox = new HBox(70);
        signInHBox.getChildren().addAll(button4, signInButton);
        signInHBox.setAlignment(Pos.BOTTOM_CENTER);
        signInGridPane.add(signInHBox, 1, 5);


        // Button action for sign in button.
        signInButton.setOnAction(e -> {

            try {
                // Reading file.
                Path signInGetPath = Paths.get("userinformations.txt");
                InputStream signInInput = Files.newInputStream(signInGetPath);
                BufferedReader signInReader = new BufferedReader(new InputStreamReader(signInInput));

                usernameInput = signInUsernameText.getText();
                passwordInput = signInPasswordText.getText();

                String signInline = null;
                String signInUsername;
                String signInPassword;

                boolean signInFound = false;

                while ((signInline = signInReader.readLine()) != null) {
                    String[] account = signInline.split(",");
                    signInUsername = account[0];
                    signInPassword = account[1];

                    // Searching for the user inputs.
                    if (signInUsername.equals(usernameInput) && signInPassword.equals(passwordInput)) {
                        signInFound = true;
                    }
                }
                // Assignments when username and password has found.
                if (signInFound == true) {
                    a = usernameInput;
                    stage1.setScene(scene1);
                    Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
                    alert4.setTitle("Information");
                    alert4.setHeaderText(null);
                    alert4.setContentText("Welcome back " + a + "!");
                    alert4.showAndWait();

                }
                // Alert if the username or password is wrong.
                else {
                    Alert alert5 = new Alert(Alert.AlertType.ERROR);
                    alert5.setTitle("Error!");
                    alert5.setHeaderText(null);
                    alert5.setContentText("The username or password is wrong. Please try again.");
                    alert5.showAndWait();
                }
                signInReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        return signInGridPane;

    }

    String loggedInUser = a;

}