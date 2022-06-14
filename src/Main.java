import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;
import java.io.File;


public class Main extends Application {
    public static void main(){
    }
    public static final int towerWidth = 12;
    public static int columnNumber =(int) towerWidth/2;
    Group root = new Group();
    Pane pane = new Pane(root);
    Scene mainScene = new Scene(pane,800,600,true);
    Image background = new Image("file:img/background.png");
    ImageView enterBtn = new ImageView(new Image("file:img/enter-btn.png", 139.2, 61.2, false, false));
    GridPane gridPane = new GridPane();
    ImageView backgroundView = new ImageView(background);
    Tower tower = new Tower();
    Camera camera = new Camera();
    ArrayList<Block> blockList = new ArrayList<>();
    ArrayList<String> input = new ArrayList<>();
    ArrayList<Letters> letterSelection = new ArrayList<>();
    ArrayList<String> allWordsDict = new ArrayList<>();
    boolean clickedEnterBtn = false;
    boolean timerPenalty = false;
    String wordCreated = "";
    Block blockPreview = new Block(3,columnNumber);
    Text timerText = new Text(760,30,"60");
    Text scoreText = new Text(15,30,"Score : 0");
    int score =0;


    int rightPressed = 0;
    int leftPressed = 0;
    int enterPressed = 0;
    AnimationTimer at = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if (!clickedEnterBtn && enterPressed == 1) {
                enterPressed = 0;
                System.out.println("trying to stop creating of another block");
            }

            if (input.contains("RIGHT")) {
                rightPressed = 1;
            }
            if (input.contains("LEFT")){
                leftPressed = 1;
            }
            if (!input.contains("RIGHT") && rightPressed == 1) {
                rightPressed = 0;
                columnNumber +=1;
            }
            if (!input.contains("LEFT") && leftPressed == 1) {
                leftPressed = 0;
                columnNumber -=1;
            }
            if (columnNumber<0){
                columnNumber=0;
            }
            if (columnNumber>=towerWidth){
                columnNumber=towerWidth-1;
            }

            if (clickedEnterBtn) {
                int blockLength = letterSelection.get(0).getWord().length();
                int blockHeight = Tower.towerHeight[columnNumber];
                boolean locationPossible = columnNumber + blockLength <= towerWidth;
                blockPreview.setLength(blockLength);
                if (locationPossible) {
                    for(int i = 0;i<blockLength;i++) {
                        if (Tower.towerHeight[columnNumber + i] != blockHeight) {
                            locationPossible = false;
                            break;
                        }
                    }
                }

                int x_dist = (800 - (blockLength*100))/2;
                int y_dist = 422 - 100 * blockHeight;
                if (blockHeight>=3) {
                    y_dist = 200;
                }
                if (blockLength % 2 == 1) {
                    x_dist -= 50;
                }
                blockPreview.getHboxView().setLayoutX(x_dist+200);
                blockPreview.getHboxView().setLayoutY(y_dist);
                blockPreview.getHboxView().setVisible(true);


                if (locationPossible){
                    blockPreview.setColor("green");
                    if (input.contains("ENTER")) {
                        Block block = new Block(blockLength,columnNumber);
                        score+=blockLength;
                        System.out.println("Score : "+String.valueOf(score));
                        scoreText.setText("Score : "+String.valueOf(score));
                        block.setColor("transparent");
                        root.getChildren().add(block.getHboxView());
                        blockList.add(block);
                        System.out.println(String.valueOf(blockList.size()));
                        tower.addTowerHeight(columnNumber,blockLength);
                        blockPreview.getHboxView().setVisible(false);

                        for (int i = 0; i < wordCreated.length(); i++) {
                            Letters ltr = new Letters();
                            letterSelection.add(ltr);
                            root.getChildren().add(ltr.getLetterView());
                        }

                        for (int i = 0; i < 6; i++) {
                            letterSelection.get(i).getLetterView().setX((i*78) + 166);
                            letterSelection.get(i).getLetterView().setY(50);
                        }

                        letterSelection.get(0).resetWord();
                        wordCreated = "";
                        System.out.println(letterSelection.size());
                        clickedEnterBtn = false;
                        enterPressed = 1;
                    }

                } else {
                    blockPreview.setColor("red");
                }
            }

            if (letterSelection.get(0).getWord().length() > 2 && !clickedEnterBtn) {
                enterBtn.setVisible(true);
                enterBtn.setX(330.4);
                enterBtn.setY(220);
            }

            camera.update(columnNumber);

            render(l);
        }
    };

    @Override
    public void start(Stage primaryStage) throws Exception {
        // read in all the words from txt file to an ArrayList
        try (Scanner scan = new Scanner(new File("src/all-words.txt"))) {
            while (scan.hasNext()) {
                allWordsDict.add(scan.next());
            }
        } catch (Exception e) {
            System.out.printf("Caught Exception: %s%n", e.getMessage());
            e.printStackTrace();
        }

        // set up the initial letters that need to be used
        for (int i = 0; i < 6; i++) {
            Letters l = new Letters();
            l.getLetterView().setX((i*78) + 166);
            l.getLetterView().setY(50);
            letterSelection.add(l);
        }

        primaryStage.setTitle("Word Blocks");
        primaryStage.setScene(mainScene);
        primaryStage.show();
        root.getChildren().add(backgroundView);
        root.getChildren().add(gridPane);
        root.getChildren().add(enterBtn);
        enterBtn.setVisible(false);
        root.getChildren().add(blockPreview.getHboxView());
        blockPreview.getHboxView().setVisible(false);
        timerText.setFont(new Font(25));
        root.getChildren().add(timerText);
        scoreText.setFont(new Font(25));
        root.getChildren().add(scoreText);
        Text gameOverText = new Text(300,200,"GAME OVER \n");
        gameOverText.setFont(new Font(50));
        gameOverText.setVisible(false);
        root.getChildren().add(gameOverText);
        enterBtn.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            clickedEnterBtn = true;
            enterBtn.setVisible(false);
            System.out.println("clicked the enter btn");
            String word = letterSelection.get(0).getWord();
            if (allWordsDict.contains(word)) {
                System.out.println("word in dictionary");
                wordCreated = word;
                for (int i = 0; i < word.length(); i++) {
                    char letter = word.charAt(i);
                    // remove letter from the static object
                    letterSelection.get(0).removeLetter(letter);
                    // remove letter from the list in main
                    findAndRemoveLetter(letter);
                }

            } else {
                System.out.println("not a word in dictionary");
                timerPenalty = true;
                for (int i = 0; i < letterSelection.size(); i++) {
                    letterSelection.get(i).getLetterView().setX((i*78) + 166);
                    letterSelection.get(i).getLetterView().setY(50);
                    letterSelection.get(i).setClicked();
                }
                letterSelection.get(0).resetWord();
                clickedEnterBtn = false;
            }
        });

        for (Letters l : letterSelection) {
            root.getChildren().add(l.getLetterView());
        }
        mainScene.setOnKeyPressed(
                e->{
                    String code = e.getCode().toString();
                    if ( !input.contains(code) )
                        input.add( code );
                }
        );

        mainScene.setOnKeyReleased(
                e->{
                    String code = e.getCode().toString();
                    if ( input.contains(code) )
                        input.remove( code );
                }
        );
        ImageView test = letterSelection.get(0).getLetterView();



        at.start();
        Timer t = new Timer();
        TimerTask task = new TimerTask() {
            int timer = 60;
            public void run() {
                timerText.setText(String.valueOf(timer));
                if(timer<=0){
                    timerText.setText(String.valueOf(0));
                    t.cancel();
                    System.out.println("game over");
                    gameOverText.setText("GAME OVER \n Score : "+ String.valueOf(score));
                    gameOverText.setVisible(true);

                }
                if (timerPenalty) {
                    timerPenalty = false;
                    timer-=5;
                } else {
                    timer--;
                }
            }

        };
        t.schedule(task,new Date(),1000);

    }
    public void render(long l) {
        for (Block block:blockList) {
            block.getHboxView().setLayoutX(block.getX()-camera.getX()+400);
            block.getHboxView().setLayoutY(block.getY()-camera.getY()+300);
        }
        backgroundView.setViewport(new Rectangle2D(camera.getX()-400, camera.getY()-300, 800, 600));
    }

    public void findAndRemoveLetter(char c) {
        for (int i = 0; i < letterSelection.size(); i++) {
            if (letterSelection.get(i).getLetter() == c) {
                letterSelection.get(i).getLetterView().setVisible(false);
                letterSelection.remove(i);
                return;
            }
        }
    }
}
