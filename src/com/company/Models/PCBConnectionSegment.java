package com.company.Models;

public class PCBConnectionSegment {
    private boolean horizontal;
    private int directionalLength;
    private int startingPosX;
    private int startingPosY;

    public PCBConnectionSegment(boolean horizontal, int directionalLength, int startingPosX, int startingPosY) {
        this.horizontal = horizontal;
        this.directionalLength = directionalLength;
        this.startingPosX = startingPosX;
        this.startingPosY = startingPosY;
    }

    public PCBConnectionSegment(PCBConnectionSegment pcb){
        horizontal = pcb.isHorizontal();
        directionalLength = pcb.getDirectionalLength();
        startingPosX = pcb.getStartingPosX();
        startingPosY = pcb.getStartingPosY();
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public int getDirectionalLength() {
        return directionalLength;
    }

    public int getStartingPosX() {
        return startingPosX;
    }

    public int getStartingPosY() {
        return startingPosY;
    }

    public void setDirectionalLength(int directionalLength) {
        this.directionalLength = directionalLength;
    }

    public void setStartingPosX(int startingPosX) {
        this.startingPosX = startingPosX;
    }

    public void setStartingPosY(int startingPosY) {
        this.startingPosY = startingPosY;
    }

    public void changeDirectionalLength(int change) {
        this.directionalLength += change;
    }

    public void changeStartingPosX(int change) {
        this.startingPosX += change;
    }

    public void changeStartingPosY(int change) {
        this.startingPosY += change;
    }
}
