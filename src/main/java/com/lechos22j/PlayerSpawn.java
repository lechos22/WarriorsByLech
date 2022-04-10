package com.lechos22j;

public class PlayerSpawn {
    private int x;
    private int y;
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public PlayerSpawn(int x, int y){
        this.x = x;
        this.y = y;
    }
}
