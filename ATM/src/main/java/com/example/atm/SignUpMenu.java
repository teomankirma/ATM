package com.example.atm;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class SignUpMenu {
    public GridPane signUp(Button button3) {

        // Generating TextFields for getting username and surname for Sign Up Scene.
        TextField signUpUsernameText = new TextField();
        PasswordField signUpPasswordText = new PasswordField();

        // TextField's size.
        signUpUsernameText.setMaxSize(300, 100);
        signUpPasswordText.setMaxSize(300, 100);

        Button signUpButton = new Button("Sign Up");

        // Button action for sign up.
        signUpButton.setOnAction(e -> {
                    // Generating new file.
                    File newFile = new File("userinformations.txt");
                    String username, password;
                    int totalMoney = 0;

                    // Getting user input.
                    username = signUpUsernameText.getText();
                    password = signUpPasswordText.getText();

                    // Logic for signing up.
                    if (Pattern.matches("[a-z]+", username) && username.length() >= 5 && username.length() <= 16
                            && Pattern.matches("[0-9]+", password) && password.length() == 6) {

                        // Writing the input to the file if logic is correct.
                        FileWriter signUpFileWriter = null;
                        try {
                            // Reading the file for if username exists.
                            Path signUpGetPath = Paths.get("userinformations.txt");
                            InputStream signUpInput = Files.newInputStream(signUpGetPath);
                            BufferedReader signUpReader = new BufferedReader(new InputStreamReader(signUpInput));

                            String signUpLine;
                            String signUpIfUserExists;
                            boolean usernameExists = false;

                            while ((signUpLine = signUpReader.readLine()) != null) {
                                // Checking if username is taken or not.
                                String[] existingUsernames = signUpLine.split(",");
                                signUpIfUserExists = existingUsernames[0];

                                if (username.equals(signUpIfUserExists)) {
                                    // Alert if the username is already taken.
                                    usernameExists = true;

                                }
                            }
                            if (usernameExists == true) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error!");
                                alert.setHeaderText(null);
                                alert.setContentText("The username that you entered is taken!");
                                alert.showAndWait();
                                signUpReader.close();

                            } else {
                                // Writing user information to the file.
                                try {
                                    signUpFileWriter = new FileWriter("userinformations.txt", true);
                                    signUpFileWriter.write(username + ",");
                                    signUpFileWriter.write(password + ",");
                                    signUpFileWriter.write(totalMoney + "\r\n");
                                    signUpFileWriter.close();

                                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                                    alert2.setTitle("Information");
                                    alert2.setHeaderText(null);
                                    alert2.setContentText("You signed up successfully!");
                                    alert2.showAndWait();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }

                            }
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }


                    } else {
                        // Alert for if the username logic is not correct.
                        Alert alert3 = new Alert(Alert.AlertType.ERROR);
                        alert3.setTitle("Error!");
                        alert3.setHeaderText(null);
                        alert3.setContentText("The username or password that you enter is invalid. Your username" +
                                " must contain 5-16 lowercase letters, no numbers. Your password must be " +
                                "6 digits, no letters. Please try again.");
                        alert3.showAndWait();


                    }
                }
        );


        // GridPane for TextFields.
        GridPane signUpGridPane = new GridPane();
        signUpGridPane.setBackground(new Background(new BackgroundFill(Color.DARKORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
        signUpGridPane.add(new Label("Welcome!"), 1, 0);
        signUpGridPane.add(new Label("Username:     "), 0, 3);
        signUpGridPane.add(signUpUsernameText, 1, 3);
        signUpGridPane.add(new Label("Password:     "), 0, 4);
        signUpGridPane.add(signUpPasswordText, 1, 4);


        // Alignment for GridPane.
        signUpGridPane.setAlignment(Pos.CENTER);
        signUpGridPane.setHgap(10);
        signUpGridPane.setVgap(10);


        // HBox for buttons in sign up page.
        HBox signUpHBox = new HBox(70);
        signUpHBox.getChildren().addAll(button3, signUpButton);
        signUpHBox.setAlignment(Pos.BOTTOM_CENTER);
        signUpGridPane.add(signUpHBox, 1, 5);


        return signUpGridPane;
    }
}