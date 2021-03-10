package com.company.Models;

import java.util.ArrayList;

public class PCBPoint {
    private int posX;
    private int posY;

    public PCBPoint(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public PCBPoint(PCBPoint point){
        posX = point.getPosX();
        posY = point.getPosY();
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
