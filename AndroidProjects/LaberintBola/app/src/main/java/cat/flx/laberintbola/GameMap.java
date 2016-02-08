package cat.flx.laberintbola;


public class GameMap {
    private int width;
    private int height;
    private String map;
    private int startX, startY, endX, endY;
    
    public static final int TOP = 8;
    public static final int RIGHT = 4;
    public static final int BOTTOM = 2;
    public static final int LEFT = 1;
    
    public GameMap(int width, int height, String map, int startX, int startY, int endX, int endY) {
        this.width = width;
        this.height = height;
        this.map = map;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getStartX() { return startX; }
    public int getStartY() { return startY; }
    public int getEndX() { return endX; }
    public int getEndY() { return endY; }
    
    
    public boolean hasWall(int x, int y, int side) {
        int i = y * width + x;
        String c = map.substring(i, i + 1);
        int v = Integer.valueOf(c, 16);
        return (v & side) != 0; 
    }
    
}
