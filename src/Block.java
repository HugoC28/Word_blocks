import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.css.PseudoClass;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class Block {
    private int x;
    private int y;
    private int columnNumber;
    private int length;
    private ImageView blockView = new ImageView("file:img/block.png");
    HBox hBoxView = new HBox();
    String style_red = "-fx-border-color: red;"
            + "-fx-border-width: 8;"
            + "-fx-border-style: solid;";

    String style_green = "-fx-border-color: green;"
            + "-fx-border-width: 8;"
            + "-fx-border-style: solid;";

    String style_transparent = "-fx-border-color: transparent;"
            + "-fx-border-width: 8;"
            + "-fx-border-style: solid;";
    private String color;


    public Block(int length, int columnNumber) {
        this.columnNumber = columnNumber;
        this.length = length;
        this.blockView.setViewport(new Rectangle2D(0,0,Camera.blockSize*this.length,Camera.blockSize));
        this.hBoxView.getChildren().add(blockView);
        this.x=Camera.minX+Camera.blockSize*columnNumber;
        this.y=Camera.minY-(Tower.towerHeight[columnNumber]+1)*Camera.blockSize;

    }

    public ImageView getBlockView() {
        return blockView;
    }

    public HBox getHboxView() {
        return hBoxView;
    }

    public String getColor() {
        return this.color;
    }
    public void setColor(String color) {
        this.color = color;
        if (this.color == "red") {
            hBoxView.setStyle(style_red);
        } else if (this.color == "green") {
            hBoxView.setStyle(style_green);
        } else {
            hBoxView.setStyle(style_transparent);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setLength(int length) {
        this.length = length;
        this.blockView.setViewport(new Rectangle2D(0,0,Camera.blockSize*this.length,Camera.blockSize));
    }
}
