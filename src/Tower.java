
public class Tower {
    static int[] towerHeight;

    public Tower() {

        towerHeight = new int[Main.towerWidth];
    }

    public void addTowerHeight(int columnNumber, int blockLength) {
        for (int i = 0;i<blockLength;i++) {
            towerHeight[columnNumber+i] += 1;
        }
    }
}
