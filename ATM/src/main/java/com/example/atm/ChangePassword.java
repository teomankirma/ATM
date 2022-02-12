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
import java.util.Scanner;
import java.util.regex.Pattern;

public class ChangePassword {

    public GridPane changePassword(Button button10) {

        // Generating TextFields for getting old password and new password for Change Password Scene.
        PasswordField oldPasswordText = new PasswordField();
        PasswordField newPasswordText = new PasswordField();
        PasswordField newPasswordAgainText = new PasswordField();


        // TextField's size.
        oldPasswordText.setMaxSize(200, 100);
        newPasswordText.setMaxSize(200, 100);
        newPasswordAgainText.setMaxSize(200, 100);

        Button changePasswordButton = new Button("Change Password");


        // GridPane for TextFields.
        GridPane changePasswordGridPane = new GridPane();
        changePasswordGridPane.setBackground(new Background(new BackgroundFill(Color.DARKORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
        changePasswordGridPane.add(new Label("Change Password"), 1, 0);
        changePasswordGridPane.add(new Label("Old Password:"), 0, 2);
        changePasswordGridPane.add(oldPasswordText, 1, 2);
        changePasswordGridPane.add(new Label("New Password:"), 0, 3);
        changePasswordGridPane.add(newPasswordText, 1, 3);
        changePasswordGridPane.add(new Label("New Password (again):"), 0, 4);
        changePasswordGridPane.add(newPasswordAgainText, 1, 4);


        // Alignment for GridPane.
        changePasswordGridPane.setAlignment(Pos.CENTER);
        changePasswordGridPane.setHgap(10);
        changePasswordGridPane.setVgap(10);

        // HBox for buttons in change password page.
        HBox changePasswordHBox = new HBox(40);
        changePasswordHBox.getChildren().addAll(button10, changePasswordButton);
        changePasswordHBox.setAlignment(Pos.BOTTOM_CENTER);
        changePasswordGridPane.add(changePasswordHBox, 1, 5);


        class ChangePasswordOperation extends SignInMenu {

            String oldPassword, newPassword, newPasswordAgain;
            static String e;

            public void ChangePasswordLogic() {

                while (true) {
                    // Getting logged in user's current password.
                    try {
                        Path changePasswordGetPath = Paths.get("userinformations.txt");
                        InputStream changePasswordInput = Files.newInputStream(changePasswordGetPath);
                        BufferedReader changePasswordReader = new BufferedReader(new InputStreamReader(changePasswordInput));


                        String changePasswordMenuLine = null;
                        String changePasswordMenuUser;
                        String changePasswordCurrentPassword = "";

                        boolean changePasswordFound = false;

                        while ((changePasswordMenuLine = changePasswordReader.readLine()) != null) {
                            String[] account = changePasswordMenuLine.split(",");
                            changePasswordMenuUser = account[0];


                            if (changePasswordMenuUser.equals(loggedInUser)) {
                                changePasswordCurrentPassword = account[1];
                                changePasswordFound = true;
                            }
                        }
                        if (changePasswordFound == true) {
                            e = changePasswordCurrentPassword;
                            break;


                        }
                        changePasswordReader.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
                String changePasswordLoggedInUserPassword = e;

                while (true) {

                    oldPassword = oldPasswordText.getText();
                    newPassword = newPasswordText.getText();
                    newPasswordAgain = newPasswordAgainText.getText();


                    if (Pattern.matches("[0-9]+", newPassword) && newPassword.length() == 6 &&
                            Pattern.matches("[0-9]+", newPasswordAgain) && newPasswordAgain.length() == 6) {

                        // Logic for changing password.
                        if (oldPassword.equals(changePasswordLoggedInUserPassword) == false) {
                            Alert alert14 = new Alert(Alert.AlertType.ERROR);
                            alert14.setTitle("Error!");
                            alert14.setHeaderText(null);
                            alert14.setContentText("Your old password is not matched!");
                            alert14.showAndWait();
                            break;
                        } else if (oldPassword.equals(newPassword)) {
                            Alert alert15 = new Alert(Alert.AlertType.ERROR);
                            alert15.setTitle("Error!");
                            alert15.setHeaderText(null);
                            alert15.setContentText("Your new password cannot be same as your old password!");
                            alert15.showAndWait();
                            break;
                        } else if (newPassword.equals(newPasswordAgain) == false) {
                            Alert alert16 = new Alert(Alert.AlertType.ERROR);
                            alert16.setTitle("Error!");
                            alert16.setHeaderText(null);
                            alert16.setContentText("Your new password is not matching! Please try again!");
                            alert16.showAndWait();
                            break;
                        } else {

                            Scanner x2;

                            // Writing new user password to the file after changing password operation.
                            String changePasswordFilePath = "userinformations.txt";
                            String changePasswordEditTerm = loggedInUser;
                            String changePasswordUsername = loggedInUser;
                            String changedPassword = newPassword;


                            // Generating a new file for overriding current user's password.
                            String changePasswordTempFile = "temp.txt";
                            File changePasswordOldFile = new File(changePasswordFilePath);
                            File changePasswordNewFile = new File(changePasswordTempFile);

                            String existingname2 = "";
                            String existingpassword2 = "";
                            String existingmoney2 = "";

                            try {
                                FileWriter fw2 = new FileWriter(changePasswordTempFile, true);
                                BufferedWriter bw2 = new BufferedWriter(fw2);
                                PrintWriter pw2 = new PrintWriter(bw2);
                                x2 = new Scanner(new File(changePasswordFilePath));
                                x2.useDelimiter("[,\n]");

                                // Overriding logged in users password.
                                while (x2.hasNext()) {
                                    existingname2 = x2.next();
                                    existingpassword2 = x2.next();
                                    existingmoney2 = x2.next();

                                    if (existingname2.equals(changePasswordEditTerm)) {

                                        pw2.println(changePasswordUsername + "," + changedPassword + "," + existingmoney2);

                                    } else {
                                        pw2.println(existingname2 + "," + existingpassword2 + "," + existingmoney2);
                                    }

                                }
                                x2.close();
                                pw2.flush();
                                pw2.close();
                                // Deleting the old file and changing temp.txt file name to old file's name(userinformations.txt).
                                changePasswordOldFile.delete();
                                File trash2 = new File(changePasswordFilePath);
                                changePasswordNewFile.renameTo(trash2);

                            } catch (Exception e) {
                                System.out.println("Error");
                            }

                            Alert alert17 = new Alert(Alert.AlertType.CONFIRMATION);
                            alert17.setTitle("Confirmed");
                            alert17.setHeaderText(null);
                            alert17.setContentText("Password successfully changed.");
                            alert17.showAndWait();
                            break;
                        }
                    } else {
                        Alert alert18 = new Alert(Alert.AlertType.ERROR);
                        alert18.setTitle("Error!");
                        alert18.setHeaderText(null);
                        alert18.setContentText("Your new password must be 6 digits, only numbers!");
                        alert18.showAndWait();
                        break;
                    }
                }

            }
        }

        // Action when pressing change password button.
        changePasswordButton.setOnAction(e -> {
            ChangePasswordOperation changePasswordOperation = new ChangePasswordOperation();
            changePasswordOperation.ChangePasswordLogic();
        });

        return changePasswordGridPane;
    }
}
