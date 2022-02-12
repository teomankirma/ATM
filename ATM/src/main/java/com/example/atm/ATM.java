package com.example.atm;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.stage.Stage;


public class ATM extends Application {


    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {

        // Opening Menu
        Button signUp = new Button("Sign Up");
        signUp.setContentDisplay(ContentDisplay.TOP);
        Button signIn = new Button("Sign In");
        signIn.setContentDisplay(ContentDisplay.TOP);

        OpeningMenu o1 = new OpeningMenu();
        Scene openingScene = new Scene(o1.openingScene(signUp, signIn), 600, 400);


        // Sign Up Menu
        Button signUpGoBack = new Button("Back");

        SignUpMenu su = new SignUpMenu();
        Scene signUpScene = new Scene(su.signUp(signUpGoBack), 600, 400);


        // Main Menu
        Button withdraw = new Button("Withdraw");
        withdraw.setContentDisplay(ContentDisplay.TOP);

        Button deposit = new Button("Deposit");
        deposit.setContentDisplay(ContentDisplay.TOP);

        Button changepassword = new Button("Change Password");
        changepassword.setContentDisplay(ContentDisplay.TOP);

        Button exit = new Button("Exit");
        exit.setContentDisplay(ContentDisplay.TOP);


        MainMenu mm = new MainMenu();
        Scene mainMenuScene = new Scene(mm.mainMenu(withdraw, deposit, changepassword, exit), 750, 400);


        // Sign In Menu
        SignInMenu si = new SignInMenu();
        Button signInGoBack = new Button("Back");
        Scene signInScene = new Scene(si.signIn(primaryStage, mainMenuScene, signInGoBack), 600, 400);


        // Withdraw Menu
        Button withdrawGoBack = new Button("Back");

        SignInMenu wi = new WithdrawMenu();
        Scene withdrawScene = new Scene(((WithdrawMenu) wi).withdraw(withdrawGoBack), 750, 400);


        // Deposit Menu
        Button depositGoBack = new Button("Back");

        SignInMenu de = new DepositMenu();
        Scene depositScene = new Scene(((DepositMenu) de).deposit(depositGoBack), 750, 400);


        // Change Password Menu

        Button changePasswordGoBack = new Button("Back");

        ChangePassword cp = new ChangePassword();
        Scene changePasswordScene = new Scene(cp.changePassword(changePasswordGoBack), 750, 400);


        // Button actions for changing scenes.
        signUp.setOnAction(e -> primaryStage.setScene(signUpScene));
        signIn.setOnAction(e -> primaryStage.setScene(signInScene));
        signUpGoBack.setOnAction(e -> primaryStage.setScene(openingScene));
        signInGoBack.setOnAction(e -> primaryStage.setScene(openingScene));
        withdraw.setOnAction(e -> primaryStage.setScene(withdrawScene));
        withdrawGoBack.setOnAction(e -> primaryStage.setScene(mainMenuScene));
        deposit.setOnAction(e -> primaryStage.setScene(depositScene));
        depositGoBack.setOnAction(e -> primaryStage.setScene(mainMenuScene));
        changepassword.setOnAction(e -> primaryStage.setScene(changePasswordScene));
        changePasswordGoBack.setOnAction(e -> primaryStage.setScene(mainMenuScene));
        exit.setOnAction(e -> System.exit(0));


        primaryStage.setScene(openingScene);
        primaryStage.setTitle("ATM");
        primaryStage.show();
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */

    public static void main(String[] args) {
        launch(args);
    }
}
