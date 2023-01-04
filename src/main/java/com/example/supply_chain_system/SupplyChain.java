package com.example.supply_chain_system;


import game_snake.Player;
import game_snake.SnakeLadder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Shape3D;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

import java.io.IOException;

import static game_snake.SnakeLadder.tileSize;
import static game_snake.SnakeLadder.height;
import static game_snake.SnakeLadder.width;
//import static game_snake.SnakeLadder.;




public class SupplyChain extends Application {
    public static final int w=700,h=600,b=50;
    SnakeLadder g=new SnakeLadder();
    public boolean playerOneAccess=false,startGame=true;
//    public int diceNumber;
    Pane outerPane=new Pane();
    Pane globalRoot;
    Login login=new Login();
    SignIN signIN=new SignIN();
    Button globalSignInButton;
    Button globalLoginButton;
    Label globalCustomerLabel;
    Button globalMoreButton=new Button("More");

    String globalUserEmail=null;
    String globalUserName=null;
    ProductDetails productDetails=new ProductDetails();

    Pane createGameContent(){
        Pane root=new Pane();
        // Setting grid of size equal to board
        root.setPrefSize(width* tileSize,height* tileSize+100);


        // Setting Players coin
        Player playerOne=new Player(tileSize,globalUserName, Color.BLUE);
//        Player playerTwo=new Player(tileSize-10,"Yasin",Color.RED);

        //Start button functionality
        Button reStart =new Button("Restart");
        Button startButton=new Button("START");
        startButton.setTranslateX(300);
        startButton.setTranslateY(height*tileSize+10);
        startButton.setTextFill(Color.GREEN);
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(g.startGame){
                    playerOneAccess=true;
                    startGame=false;
                }
            }
        });
        reStart.setTranslateX(300);
        reStart.setTranslateY(height*tileSize+50);


        //create dice label
        Label diceLabel=new Label("Start Game");
        diceLabel.setTextFill(Color.RED);
        diceLabel.setTranslateX(150);
        diceLabel.setTranslateY(height*tileSize+10);

        // Players button option
        Button player1=new Button("Player one");
        player1.setTranslateY(height*tileSize+10);
        player1.setTranslateX(20);
        Label player1Score=new Label(playerOne.getName()+" : "+playerOne.getCoinPosition());
        player1Score.setTranslateY(height*tileSize+50);
        player1Score.setTranslateX(20);
//        Label player2Score=new Label(playerTwo.getName()+" : "+playerTwo.getCoinPosition());
//        player2Score.setTranslateX(300);
//        player2Score.setTranslateY(height*tileSize+50);
        player1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(playerOneAccess) {
                    g.randNumberGenerator();
                    diceLabel.setText("DiceNumber : " + g.diceNumber);
                    playerOne.moveCoin(g.diceNumber);
                    player1Score.setText( playerOne.getName()+" : "+playerOne.getCoinPosition());
//                    playerOneAccess=false;
//                    playerTwoAccess=true;
                    if(playerOne.getCoinPosition()==100){
//                        playerTwoAccess=false;
                        diceLabel.setText(playerOne.getName()+" Won!");
                        diceLabel.setTextFill(Color.GREEN);
                    }
                }
            }
        });
//


        // Setting Background
        Image img=new Image("C:\\Users\\Admin\\IdeaProjects\\SnakeAndLadder\\src\\main\\SnakeLadderBoard12Nov.jpg");
        ImageView board=new ImageView();
        board.setImage(img);
        board.setFitHeight(tileSize*height);
        board.setFitWidth(tileSize*width);
        reStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                playerOne.setCoinPosition(1);
                playerOne.moveCoin(0);
//                Label player1Score=new Label(playerOne.getName()+" : "+playerOne.getCoinPosition());
                player1Score.setText(playerOne.getName()+" : "+playerOne.getCoinPosition());
//                root.getChildren().clear();
//                root.getChildren().addAll(board,diceLabel,player1Score,player1,playerOne.getCoin(),reStart,startButton);

            }
        });
        root.getChildren().addAll(board,diceLabel,player1Score,player1,playerOne.getCoin(),reStart,startButton);
        root.setStyle("-fx-background-color: #aaa7cc");
        root.setTranslateX(150);
         root.setBorder(Border.stroke(Color.RED));
        return root;
    }

    private GridPane moreOption(){
        Button customerDetails=new Button("My Details");
        Button game=new Button("Game Zone");
        game.setTextFill(Color.GREEN);
        game.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                outerPane.getChildren().clear();

                outerPane.getChildren().add(createGameContent());

            }
        });
        Button log_out=new Button("Log Out");
        log_out.setTextFill(Color.RED);
        log_out.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                globalRoot.getChildren().clear();
                globalUserEmail=null;
                outerPane.getChildren().clear();
                outerPane.getChildren().add(productDetails.getProductPane());
                globalRoot.getChildren().addAll(outerPane,headBar(),footBar());
            }
        });
        GridPane gridPane=new GridPane();
        gridPane.setMinSize(w,h);
        gridPane.add(customerDetails,0,0);
        gridPane.add(game,0,1);
        gridPane.add(log_out,0,2);
        gridPane.setVgap(20);
        gridPane.setStyle("-fx-background-color: #66669a");
        gridPane.setAlignment(Pos.CENTER);
        return gridPane;
    }
    private GridPane headBar(){
        TextField searchText=new TextField();
        Button searchButton =new Button("search button");
        searchButton.setBackground(Background.fill(Color.LIGHTBLUE));
        searchButton.setBorder(Border.stroke(Color.BLACK));
//        searchButton.setShape();
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String productName=searchText.getText();

                outerPane.getChildren().clear();
                outerPane.getChildren().add(productDetails.getProductPaneByName(productName));
            }
        });
        globalLoginButton =new Button("Log In");
        globalLoginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                outerPane.getChildren().clear();
                outerPane.getChildren().add(loginPage());

            }
        });
        globalCustomerLabel=new Label();

        globalSignInButton=new Button("Sign In");
        globalSignInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                outerPane.getChildren().clear();
                outerPane.getChildren().add(signInPage());

            }
        });

        Button cartButton=new Button("Cart");
        cartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                outerPane.getChildren().clear();
                outerPane.getChildren().add(productDetails.getAddToCartPane());
            }
        });
        globalMoreButton.setVisible(false);
        globalMoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                outerPane.getChildren().clear();
                outerPane.getChildren().add(moreOption());
            }
        });
        GridPane x=new GridPane();
        x.setMinSize(w,b-10);
        x.setVgap(5);
        x.setHgap(20);
        x.setStyle("-fx-background-color: #F7D8BA");
        x.add(globalMoreButton,0,0);
        x.add(searchText,1,0);
        x.add(searchButton,2,0);

        x.add(globalCustomerLabel,7,0);
        x.add(globalSignInButton,5,0);
        x.add(globalLoginButton,6,0);
        x.add(cartButton,8,0);
        x.setAlignment(Pos.CENTER);
        return x;
    }
    private GridPane loginPage(){
        Label emailLabel=new Label("Email");
        Label passwordLabel=new Label("Password");
        TextField textField=new TextField();
        PasswordField passwordFields=new PasswordField();
        Button loginButton =new Button("Login ");
        Label message =new Label(" Enter email and password");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String a=textField.getText();
                String b=passwordFields.getText();
//                message.setText(a+"***"+b);
//                message.setTextFill(Color.BLACK);
                if(login.customerLogin(a,b)){
                    message.setText(" Login successful");
                    message.setTextFill(Color.GREEN);
                    outerPane.getChildren().clear();
                    globalUserEmail=a;
//
                    outerPane.getChildren().add(productDetails.getProductPane());
                    globalLoginButton.setDisable(true);
                    globalLoginButton.setVisible(false);
                    globalSignInButton.setDisable(true);
                    globalSignInButton.setVisible(false);
                    globalMoreButton.setVisible(true);
                    if (globalUserName==null){
                        DatabaseConnection databaseConnection=new DatabaseConnection();
                        globalUserName=databaseConnection.getCustomerName(globalUserEmail);
                    }
                    globalCustomerLabel.setText("Welcome : "+globalUserName.toUpperCase());
                }
                else {
                    message.setText(" Login failed");
                    message.setTextFill(Color.RED);
                }
            }
        });
        GridPane gridPane=new GridPane();
        gridPane.setMinSize(w,h);
        gridPane.setStyle("-fx-background-color: #FFE7C7");
//        gridPane.setTranslateY(b);

//        gridPane.setTranslateX(b);
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.add(emailLabel,0,0);

        gridPane.add(passwordLabel,0,1);
        gridPane.add(textField,1,0);
        gridPane.add(passwordFields,1,1);
        gridPane.add(loginButton,0,2);
        gridPane.add(message,1,2);
        gridPane.setAlignment(Pos.CENTER);
        return gridPane;
    }

    // foot
    private GridPane signInPage(){
        Label firstNameLabel=new Label("First Name");
        Label lasttNameLabel=new Label("Last Name");

        Label emailLabel=new Label("Email");
        Label passwordLabel=new Label("Password");
        Label mobileLabel=new Label("Mobile");
        Label signMessage=new Label("Fill the Details");
        Label address=new Label("Address");

        TextField textFieldFirstName=new TextField();
        TextField textFieldLastName=new TextField();
        TextField textFieldEmail=new TextField();
        PasswordField textFieldPassword=new PasswordField();
        TextField textFieldMobile=new TextField();
        TextField textFieldAddress=new TextField();

        Button signButton=new Button("Sign In");
        signButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String firstName= textFieldFirstName.getText();
                String lastName= textFieldLastName.getText();

                String email=textFieldEmail.getText();
                String password=textFieldPassword.getText();
                String mobile=textFieldMobile.getText();
                String addressString=textFieldAddress.getText();
                if(firstName.isEmpty()||lastName.isEmpty()||email.isEmpty()||password.isEmpty()||mobile.isEmpty()){
                    signMessage.setText("Some Details Missing");
                    signMessage.setTextFill(Color.RED);
                }
                else if(login.customerLogin(email,password)){
                    signMessage.setText("Account already exist");
                    signMessage.setTextFill(Color.RED);
                }
                else if(signIN.getSignIn(firstName,lastName,email,password,mobile,addressString)){

                    outerPane.getChildren().clear();
                    outerPane.getChildren().add(productDetails.getProductPane());
                    globalSignInButton.setDisable(true);
                    globalSignInButton.setVisible(false);

                }
                else{
                    signMessage.setText("Sign In Failed");
                    signMessage.setTextFill(Color.RED);
                }
            }
        });

        GridPane signGrid=new GridPane();
        signGrid.setMinSize(w,h);
//        signGrid.setTranslateY(b);
        signGrid.add(firstNameLabel,0,0);
        signGrid.add(textFieldFirstName,1,0);
        signGrid.add(lasttNameLabel,0,1);
        signGrid.add(textFieldLastName,1,1);
        signGrid.add(emailLabel,0,2);
        signGrid.add(textFieldEmail,1,2);
        signGrid.add(passwordLabel,0,3);
        signGrid.add(textFieldPassword,1,3);
        signGrid.add(mobileLabel,0,4);
        signGrid.add(textFieldMobile,1,4);
        signGrid.add(address,0,5);
        signGrid.add(textFieldAddress,1,5);
        signGrid.add(signButton,0,6);
        signGrid.add(signMessage,1,6);
        signGrid.setVgap(5);
        signGrid.setHgap(20);
        signGrid.setStyle("-fx-background-color: #E1F8DC");

        signGrid.setAlignment(Pos.CENTER);
        return signGrid;

    }
    private GridPane footBar(){
        Button homeButton=new Button("Home");
//        homeButton.setBorder(Border.stroke(Color.BLUE));
//        homeButton.setBackground(Background.fill(Color.L));
        homeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                outerPane.getChildren().clear();
                outerPane.getChildren().add(productDetails.getProductPane());
            }
        });
        Button addToCartButton =new Button("Add To Cart");
        Button buyNowButton =new Button("Buy Now");
        Button previousOrders=new Button("My Orders");
        previousOrders.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                outerPane.getChildren().clear();
                outerPane.getChildren().add(productDetails.getProductPaneOfPreviousOrders(globalUserEmail));
            }
        });
        Label footLabel=new Label();
         buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent actionEvent) {
                 Product product=productDetails.getProductDetails();
                 if(Order.getOrderUpdated(globalUserEmail,product)){
                     footLabel.setText("Order Successful");
                     footLabel.setTextFill(Color.GREEN);
                 }
                 else {
                     footLabel.setText("Order Failed");
                     footLabel.setTextFill(Color.RED);
                 }
             }
         });

          addToCartButton.setOnAction(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent actionEvent) {
                  productDetails.setAddToCart_list();
                  footLabel.setText("Added to Cart");
                  footLabel.setTextFill(Color.GREEN);
              }
          });
        GridPane x=new GridPane();
        x.setMinSize(w,b-10);
        x.setVgap(5);
        x.setHgap(50);
        x.setStyle("-fx-background-color: #ACDDDE");
        x.add(previousOrders,0,0);
        x.add(addToCartButton,1,0);
        x.add(homeButton,2,0);
        x.add(buyNowButton,3,0);
        x.add(footLabel,4,0);
        x.setTranslateY(b+h+5);
        x.setAlignment(Pos.CENTER);
        return x;
    }
    private Pane createContent(){
        globalRoot=new Pane();

        globalRoot.setPrefSize(w,h+2*b+5);
        outerPane.setMinSize(w,h);
        outerPane.setTranslateY(b);
        outerPane.setStyle("-fx-background-color: #E1F8DC");
        outerPane.getChildren().add(productDetails.getProductPane());//loginPage());
//        root.getChildren().addAll(headBar(),loginPage());
        globalRoot.setStyle("-fx-background-color: #5C4033");

        globalRoot.getChildren().addAll(headBar(),outerPane,footBar());

        return globalRoot;
    }
    @Override
    public void start(Stage stage) throws IOException  {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Shopping Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}