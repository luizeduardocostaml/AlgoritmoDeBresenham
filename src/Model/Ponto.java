package Model;

import java.sql.SQLOutput;

public class Ponto {
    private int x;
    private int y;
    private int[] VX = {-10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private int[] VY = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10};

    public Ponto() {
    }

    public Ponto(int x, int y) {
        setX(x);
        setY(y);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int tX(){
        for(int i = 0; i < 20; i++){
            if(x == VX[i]) {
                return i;
            }
        }
        return 0;
    }
    public int tY(){
        for(int i = 0; i < 20; i++){
            if(y == VY[i]) {
                return i;
            }
        }
        return 0;
    }

}
