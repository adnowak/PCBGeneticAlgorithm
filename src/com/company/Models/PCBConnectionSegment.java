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
}
