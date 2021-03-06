package com.company.Models;

public class PCBConnectionSegment {
    private boolean horizontal;
    private int length;
    private PCBPoint startingPoint;

    public PCBConnectionSegment(boolean horizontal, int length, PCBPoint startingPoint) {
        this.horizontal = horizontal;
        this.length = length;
        this.startingPoint = startingPoint;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public int getLength() {
        return length;
    }

    public PCBPoint getStartingPoint() {
        return startingPoint;
    }
}
