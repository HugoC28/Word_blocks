public class Camera {
    private double x;
    private double y;
    private Tower tower;
    static int minX=400;
    static int minY=1000;
    static int maxX=1600;
    static int maxY=80;
    static int blockSize=100;


    public Camera() {
        this.x = (int) (minX+(Main.columnNumber)*blockSize);
        this.y = minY;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void update(int columnNumber){
        x = (int) (minX+ columnNumber*blockSize);
        y = (int) (minY-Tower.towerHeight[columnNumber]*blockSize);
        if (y>780){
            y=780;
        }
        if (y<300){
            y=300;
        }
    }
}
