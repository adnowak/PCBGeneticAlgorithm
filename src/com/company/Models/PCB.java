package com.company.Models;

import java.util.ArrayList;

public class PCB {
    private int width;
    private int height;
    private ArrayList<PCBConnection> connections;

    public PCB(int width, int height, ArrayList<PCBConnection> connections) {
        this.width = width;
        this.height = height;
        this.connections = connections;
    }

    public PCB(PCB pcb){
        width = pcb.getWidth();
        height = pcb.getHeight();
        connections = new ArrayList<>();
        for(PCBConnection connection : pcb.getConnections()){
            connections.add(new PCBConnection(connection));
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ArrayList<PCBConnection> getConnections() {
        return connections;
    }
}
