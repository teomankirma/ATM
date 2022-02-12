package com.example.atm;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MainMenu {
    public HBox mainMenu(Button button5, Button button6, Button button7, Button button8) {

        // Setting images for buttons.
        Image withdrawImage = new Image("file:src/withdraw.png");
        ImageView withdrawImageView = new ImageView(withdrawImage);
        button5.setGraphic(withdrawImageView);
        withdrawImageView.setFitHeight(100);
        withdrawImageView.setFitWidth(100);
        button5.setStyle("-fx-background-color: DarkOrange");


        Image depositImage = new Image("file:src/deposit.png");
        ImageView depositImageView = new ImageView(depositImage);
        button6.setGraphic(depositImageView);
        depositImageView.setFitHeight(100);
        depositImageView.setFitWidth(100);
        button6.setStyle("-fx-background-color: DarkOrange");

        Image changePasswordImage = new Image("file:src/changepassword.png");
        ImageView changePasswordImageView = new ImageView(changePasswordImage);
        button7.setGraphic(changePasswordImageView);
        changePasswordImageView.setFitHeight(100);
        changePasswordImageView.setFitWidth(100);
        button7.setStyle("-fx-background-color: DarkOrange");


        Image exitImage = new Image("file:src/exit.png");
        ImageView exitImageView = new ImageView(exitImage);
        button8.setGraphic(exitImageView);
        exitImageView.setFitHeight(100);
        exitImageView.setFitWidth(100);
        button8.setStyle("-fx-background-color: DarkOrange");


        // Spacing between buttons in HBox.
        HBox mainMenuHBox = new HBox(30);
        mainMenuHBox.getChildren().addAll(button5, button6, button7, button8);
        // HBox alignment.
        mainMenuHBox.setAlignment(Pos.CENTER);
        // Background color for HBox.
        mainMenuHBox.setBackground(new Background(new BackgroundFill(Color.DARKORANGE, CornerRadii.EMPTY, Insets.EMPTY)));

        // Changing font size for buttons.
        Font font = new Font(25);
        button5.setFont(font);
        button6.setFont(font);
        button8.setFont(font);

        Font font1 = new Font(22);
        button7.setFont(font1);

        // Setting button size.
        button5.setMaxSize(200, 100);
        button6.setMaxSize(200, 100);
        button7.setMaxSize(200, 100);
        button8.setMaxSize(200, 100);
        HBox.setHgrow(button5, Priority.ALWAYS);
        HBox.setHgrow(button6, Priority.ALWAYS);
        HBox.setHgrow(button7, Priority.ALWAYS);
        HBox.setHgrow(button8, Priority.ALWAYS);

        return mainMenuHBox;
    }
}
