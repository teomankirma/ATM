package com.example.atm;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class WithdrawMenu extends SignInMenu {
    static int c;


    public GridPane withdraw(Button button8) {

        Button withdrawMoneyButton = new Button("Withdraw");

        // TextField for getting withdraw amount.
        TextField withdrawAmount = new TextField();

        GridPane withdrawGridPane = new GridPane();
        withdrawGridPane.setBackground(new Background(new BackgroundFill(Color.DARKORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
        withdrawGridPane.add(new Label("Enter the amount that you want to withdraw from your account."), 1, 0);
        withdrawGridPane.add(withdrawAmount, 1, 1);

        // Alignment for GridPane.
        withdrawGridPane.setAlignment(Pos.CENTER);
        withdrawGridPane.setHgap(10);
        withdrawGridPane.setVgap(10);


        // HBox for buttons in withdraw page.
        HBox withdrawHBox = new HBox(70);
        withdrawHBox.getChildren().addAll(button8, withdrawMoneyButton);
        withdrawHBox.setAlignment(Pos.BOTTOM_CENTER);
        withdrawGridPane.add(withdrawHBox, 1, 2);


        // Implementing interface
        class WithdrawOperation extends SignInMenu implements MoneyOperationsInterface {

            int userWithdrawAmount;

            @Override
            public void MoneyTransactions() {

                while (true) {
                    // Getting the current user money.
                    try {
                        Path withdrawGetPath = Paths.get("userinformations.txt");
                        InputStream withdrawInput = Files.newInputStream(withdrawGetPath);
                        BufferedReader withdrawReader = new BufferedReader(new InputStreamReader(withdrawInput));

                        String withdrawMenuLine = null;
                        String withdrawMenuUser;
                        int withdrawMenuCurrentMoney = 0;

                        boolean withdrawUsernameFound = false;

                        while ((withdrawMenuLine = withdrawReader.readLine()) != null) {
                            String[] account = withdrawMenuLine.split(",");
                            withdrawMenuUser = account[0];

                            if (withdrawMenuUser.equals(loggedInUser)) {
                                withdrawMenuCurrentMoney = Integer.parseInt(account[2]);
                                withdrawUsernameFound = true;
                            }
                        }
                        if (withdrawUsernameFound == true) {
                            c = withdrawMenuCurrentMoney;
                            break;

                        }
                        withdrawReader.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                // Assigning user money
                int withdrawMenuLoggedInUserMoney = c;

                while (true) {

                    try {
                        userWithdrawAmount = Integer.parseInt(withdrawAmount.getText());
                    } catch (Exception e) {
                        Alert alert11 = new Alert(Alert.AlertType.ERROR);
                        alert11.setTitle("Error!");
                        alert11.setHeaderText(null);
                        alert11.setContentText("Please enter a number!");
                        alert11.showAndWait();
                        break;
                    }

                    // Logic for withdrawing money.
                    if (userWithdrawAmount > withdrawMenuLoggedInUserMoney) {
                        Alert alert6 = new Alert(Alert.AlertType.ERROR);
                        alert6.setTitle("Error!");
                        alert6.setHeaderText(null);
                        alert6.setContentText("You cannot withdraw more than the money in your account! Your balance is $" + withdrawMenuLoggedInUserMoney);
                        alert6.showAndWait();
                        break;
                    } else if (userWithdrawAmount < 0) {
                        Alert alert7 = new Alert(Alert.AlertType.ERROR);
                        alert7.setTitle("Error!");
                        alert7.setHeaderText(null);
                        alert7.setContentText("Write the amount without putting minus");
                        alert7.showAndWait();
                        break;
                    } else {
                        withdrawMenuLoggedInUserMoney = withdrawMenuLoggedInUserMoney - userWithdrawAmount;

                        Scanner x;

                        // Writing new user money to the file after withdraw operation.
                        String withdrawFilePath = "userinformations.txt";
                        String withdrawEditTerm = loggedInUser;
                        String withdrawUsername = loggedInUser;
                        int withdrawNewMoney = withdrawMenuLoggedInUserMoney;

                        // Generating a new file for overriding current user's money.
                        String withdrawTempFile = "temp.txt";
                        File withdrawOldFile = new File(withdrawFilePath);
                        File withdrawNewFile = new File(withdrawTempFile);

                        String existingname = "";
                        String existingpassword = "";
                        String existingmoney = "";

                        try {
                            FileWriter fw = new FileWriter(withdrawTempFile, true);
                            BufferedWriter bw = new BufferedWriter(fw);
                            PrintWriter pw = new PrintWriter(bw);
                            x = new Scanner(new File(withdrawFilePath));
                            x.useDelimiter("[,\n]");

                            // Overriding logged in users money.
                            while (x.hasNext()) {
                                existingname = x.next();
                                existingpassword = x.next();
                                existingmoney = x.next();

                                if (existingname.equals(withdrawEditTerm)) {

                                    pw.println(withdrawUsername + "," + existingpassword + "," + withdrawNewMoney);

                                } else {
                                    pw.println(existingname + "," + existingpassword + "," + existingmoney);
                                }

                            }
                            x.close();
                            pw.flush();
                            pw.close();
                            // Deleting the old file and changing temp.txt file name to old file's name(userinformations.txt).
                            withdrawOldFile.delete();
                            File trash = new File(withdrawFilePath);
                            withdrawNewFile.renameTo(trash);

                        } catch (Exception e) {
                            System.out.println("Error");
                        }

                        // Alert for after withdraw is successful.
                        Alert alert8 = new Alert(Alert.AlertType.CONFIRMATION);
                        alert8.setTitle("Confirmed");
                        alert8.setHeaderText(null);
                        alert8.setContentText("Withdraw successful! Your available balance is $" + withdrawMenuLoggedInUserMoney);
                        alert8.showAndWait();
                        break;
                    }
                }


            }


        }

        // Action for when pressing withdraw button.
        withdrawMoneyButton.setOnAction(e -> {
            WithdrawOperation wo = new WithdrawOperation();
            wo.MoneyTransactions();
        });

        return withdrawGridPane;
    }
}
