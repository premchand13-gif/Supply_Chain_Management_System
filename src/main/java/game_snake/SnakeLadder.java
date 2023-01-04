package game_snake;




import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import java.io.IOException;


public class SnakeLadder {
    public static final int tileSize=40,height=10,width=10;
    public int diceNumber;
    public boolean playerOneAccess=false,startGame=true;
    //creating random number generator function for rolling dice
    public void randNumberGenerator(){
        diceNumber= (int)(Math.random()*6+1);

    }
    Pane createGameContent(){
        Pane root=new Pane();
        // Setting grid of size equal to board
        root.setPrefSize(width*tileSize,height*tileSize+100);


        // Setting Players coin
        Player playerOne=new Player(tileSize,"Prem", Color.BLUE);
//        Player playerTwo=new Player(tileSize-10,"Yasin",Color.RED);

        //Start button functionality
        Button reStart =new Button("Restart");
        Button startButton=new Button("START");
        startButton.setTranslateX(150);
        startButton.setTranslateY(height*tileSize+50);
        startButton.setTextFill(Color.GREEN);
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(startGame){
                    playerOneAccess=true;
                    startGame=false;
                }
            }
        });
        reStart.setTranslateX(200);
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
                    randNumberGenerator();
                    diceLabel.setText("DiceNumber : " + diceNumber);
                    playerOne.moveCoin(diceNumber);
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
        return root;
    }
}