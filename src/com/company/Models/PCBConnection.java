package com.company.Models;

import java.util.ArrayList;

public class PCBConnection {
    private PCBPoint endpoint0;
    private PCBPoint endpoint1;
    private ArrayList<PCBConnectionSegment> path;

    public PCBConnection(PCBPoint startPoint, PCBPoint endpoint1) {
        this.endpoint0 = startPoint;
        this.endpoint1 = endpoint1;
        this.path = new ArrayList<>();
    }

    public PCBConnection(PCBConnection connection){
        endpoint0 = new PCBPoint(connection.getEndpoint0());
        endpoint1 = new PCBPoint(connection.getEndpoint1());
        path = new ArrayList<>();
        for(PCBConnectionSegment segment : connection.getPath()){
            path.add(new PCBConnectionSegment(segment));
        }
    }

    public PCBPoint getEndpoint0() {
        return endpoint0;
    }

    public PCBPoint getEndpoint1() {
        return endpoint1;
    }

    public ArrayList<PCBConnectionSegment> getPath() {
        return path;
    }

    public void setPath(ArrayList<PCBConnectionSegment> path) {
        this.path = path;
    }
}
