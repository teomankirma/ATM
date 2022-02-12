package com.example.atm;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class OpeningMenu {
    public HBox openingScene(Button button1, Button button2) {

        // Changing font size for buttons.
        Font font = new Font(25);
        button1.setFont(font);
        button2.setFont(font);

        // Setting images for buttons and setting button's size.
        Image signUpImage = new Image("file:src/signup.png");
        ImageView signUpImageView = new ImageView(signUpImage);
        signUpImageView.setFitHeight(150);
        signUpImageView.setFitWidth(150);
        button1.setGraphic(signUpImageView);
        button1.setStyle("-fx-background-color: DarkOrange");


        Image signInImage = new Image("file:src/login.png");
        ImageView signInImageView = new ImageView(signInImage);
        signInImageView.setFitHeight(150);
        signInImageView.setFitWidth(150);
        button2.setGraphic(signInImageView);
        button2.setStyle("-fx-background-color: DarkOrange");

        // Spacing between buttons in HBox.
        HBox openingMenuHBox = new HBox(100);
        openingMenuHBox.getChildren().addAll(button1, button2);
        // HBox alignment.
        openingMenuHBox.setAlignment(Pos.CENTER);
        // Background color for HBox.
        openingMenuHBox.setBackground(new Background(new BackgroundFill(Color.DARKORANGE, CornerRadii.EMPTY, Insets.EMPTY)));


        return openingMenuHBox;

    }
}
