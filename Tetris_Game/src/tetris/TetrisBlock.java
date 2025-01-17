package tetris;

import java.awt.Color;
import java.util.Random;

public class TetrisBlock {
    private int x, y;
    private int[][] shape;
    private int[][][] shapes;
    private int currentRotation;

    private Color color;
    private Color[] availableColors = {Color.green, Color.red, Color.blue};
    
    public TetrisBlock(int[][] shape) {
        this.shape = shape;
        
        initShape();
    }
    
    private void initShape() {
        shapes = new int[4][][];
        
        for (int i = 0; i < 4; i++) {
            int r = shape[0].length;
            int c = shape.length;
            
            shapes[i] = new int[r][c];
            for (int y = 0; y < r; y++) {
                for (int x = 0; x < c; x++) {
                    shapes[i][y][x] = shape[c - x - 1][y];
                }
            }

            shape = shapes[i];
        }
    }
    
    public void spawn(int gridWidth) {
        Random r = new Random();
        
        currentRotation = r.nextInt(shapes.length);
        shape = shapes[currentRotation];
        
        y = -getHeight();
        x = r.nextInt(gridWidth - getWidth());
        
        color = availableColors[r.nextInt(availableColors.length)];
    }
    
    public int getX()          { return x;               }
    public void setX(int x)    { this.x = x;             }
    public int getY()          { return y;               }
    public void setY(int y)    { this.y = y;             }
    
    public int getHeight()     { return shape.length;    }
    public int getWidth()      { return shape[0].length; }

    public int getLeftEdge()   { return x;               }
    public int getRightEdge()  { return x + getWidth();  }
    public int getBottomEdge() { return y + getHeight(); }

    public Color getColor()    { return color;           }
    public int[][] getShape()  { return shape;           }
    
    public void moveDown()     { y++;                    }
    public void moveLeft()     { x--;                    }
    public void moveRight()    { x++;                    }
    
    public void rotate() { 
        currentRotation++;
        
        if (currentRotation > 3) {
            currentRotation = 0;
        }
        
        shape = shapes[currentRotation];
    }
}
