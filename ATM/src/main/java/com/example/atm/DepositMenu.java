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

public class DepositMenu extends SignInMenu {

    static int d;

    public GridPane deposit(Button button9) {

        Button depositMoneyButton = new Button("Deposit");

        // TextField for deposit amount.
        TextField depositAmount = new TextField();

        GridPane depositGridPane = new GridPane();
        depositGridPane.setBackground(new Background(new BackgroundFill(Color.DARKORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
        depositGridPane.add(new Label("Enter the amount that you want to deposit to your account."), 1, 0);
        depositGridPane.add(depositAmount, 1, 1);

        // Alignment for GridPane.
        depositGridPane.setAlignment(Pos.CENTER);
        depositGridPane.setHgap(10);
        depositGridPane.setVgap(10);


        // HBox for buttons in deposit page.
        HBox depositHBox = new HBox(70);
        depositHBox.getChildren().addAll(button9, depositMoneyButton);
        depositHBox.setAlignment(Pos.BOTTOM_CENTER);
        depositGridPane.add(depositHBox, 1, 2);


        class DepositOperation extends SignInMenu implements MoneyOperationsInterface {

            int userDepositAmount;

            @Override
            public void MoneyTransactions() {

                while (true) {

                    // Getting the current user money.
                    try {
                        Path depositGetPath = Paths.get("userinformations.txt");
                        InputStream depositInput = Files.newInputStream(depositGetPath);
                        BufferedReader reader = new BufferedReader(new InputStreamReader(depositInput));


                        String depositMenuLine = null;
                        String depositMenuUser;
                        int depositMenuCurrentMoney = 0;

                        boolean depositUsernameFound = false;

                        while ((depositMenuLine = reader.readLine()) != null) {
                            String[] account = depositMenuLine.split(",");
                            depositMenuUser = account[0];


                            if (depositMenuUser.equals(loggedInUser)) {
                                depositMenuCurrentMoney = Integer.parseInt(account[2]);
                                depositUsernameFound = true;
                            }
                        }
                        if (depositUsernameFound == true) {
                            d = depositMenuCurrentMoney;
                            break;


                        }
                        reader.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

                // Assigning user money.
                int depositMenuLoggedInUserMoney = d;

                while (true) {

                    try {
                        userDepositAmount = Integer.parseInt(depositAmount.getText());
                    } catch (Exception e) {
                        Alert alert12 = new Alert(Alert.AlertType.ERROR);
                        alert12.setTitle("Error!");
                        alert12.setHeaderText(null);
                        alert12.setContentText("Please enter a number!");
                        alert12.showAndWait();
                        break;
                    }

                    // Logic for depositing money.
                    if (userDepositAmount < 0) {
                        Alert alert9 = new Alert(Alert.AlertType.ERROR);
                        alert9.setTitle("Error!");
                        alert9.setHeaderText(null);
                        alert9.setContentText("Write the amount without putting minus");
                        alert9.showAndWait();
                        break;
                    } else if (userDepositAmount == 0) {
                        Alert alert13 = new Alert(Alert.AlertType.ERROR);
                        alert13.setTitle("Error!");
                        alert13.setHeaderText(null);
                        alert13.setContentText("Write the amount without putting minus");
                        alert13.showAndWait();
                        break;
                    } else {
                        depositMenuLoggedInUserMoney = depositMenuLoggedInUserMoney + userDepositAmount;

                        Scanner x1;

                        // Writing new user money to the file after deposit operation.
                        String depositFilePath = "userinformations.txt";
                        String depositEditTerm = loggedInUser;
                        String depositUsername = loggedInUser;
                        int depositNewMoney = depositMenuLoggedInUserMoney;


                        // Generating a new file for overriding current user's money.
                        String depositTempFile = "temp.txt";
                        File depositOldFile = new File(depositFilePath);
                        File depositNewFile = new File(depositTempFile);

                        String existingname1 = "";
                        String existingpassword1 = "";
                        String existingmoney1 = "";

                        try {
                            FileWriter fw1 = new FileWriter(depositTempFile, true);
                            BufferedWriter bw1 = new BufferedWriter(fw1);
                            PrintWriter pw1 = new PrintWriter(bw1);
                            x1 = new Scanner(new File(depositFilePath));
                            x1.useDelimiter("[,\n]");

                            // Overriding logged in users money.
                            while (x1.hasNext()) {
                                existingname1 = x1.next();
                                existingpassword1 = x1.next();
                                existingmoney1 = x1.next();

                                if (existingname1.equals(depositEditTerm)) {

                                    pw1.println(depositUsername + "," + existingpassword1 + "," + depositNewMoney);

                                } else {
                                    pw1.println(existingname1 + "," + existingpassword1 + "," + existingmoney1);
                                }

                            }
                            x1.close();
                            pw1.flush();
                            pw1.close();
                            // Deleting the old file and changing temp.txt file name to old file's name(userinformations.txt).
                            depositOldFile.delete();
                            File trash1 = new File(depositFilePath);
                            depositNewFile.renameTo(trash1);

                        } catch (Exception e) {
                            System.out.println("Error");
                        }

                        Alert alert10 = new Alert(Alert.AlertType.CONFIRMATION);
                        alert10.setTitle("Confirmed");
                        alert10.setHeaderText(null);
                        alert10.setContentText("Deposit successful! Your available balance is $" + depositMenuLoggedInUserMoney);
                        alert10.showAndWait();
                        break;
                    }
                }
            }
        }
        // Action for when pressing deposit button.
        depositMoneyButton.setOnAction(e -> {
            DepositOperation depositOperation = new DepositOperation();
            depositOperation.MoneyTransactions();
        });


        return depositGridPane;
    }
}
