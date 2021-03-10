package com.company.Algorithm.RandomAlgorithm;

import com.company.Models.PCB;
import com.company.Models.PCBConnection;
import com.company.Models.PCBConnectionSegment;

import java.util.ArrayList;

public class RandomPCBGenerator {
    private PCB pcb;

    public RandomPCBGenerator(PCB basePBC) {
        this.pcb = new PCB(basePBC);
    }

    public void generate(){
        for (PCBConnection connection: pcb.getConnections()) {
            int recentPosX = connection.getEndpoint0().getPosX();
            int recentPosY = connection.getEndpoint0().getPosY();

            int midX = (getRandomNumber(0, pcb.getWidth())*3 +connection.getEndpoint1().getPosX() + recentPosX)/5;
            int midY = (getRandomNumber(0, pcb.getHeight())*3 +connection.getEndpoint1().getPosY() + recentPosY)/5;

            connectTwoPoints(connection, recentPosX, recentPosY, midX, midY);
            connectTwoPoints(connection, midX, midY, connection.getEndpoint1().getPosX(), connection.getEndpoint1().getPosY());
        }
    }

    public ArrayList<PCB> generate(int amount){
        ArrayList<PCB> list = new ArrayList<>();

        PCB recentPCB;
        for(int i = 0; i < amount; i++){
            recentPCB = new PCB(pcb);
            for (PCBConnection connection: recentPCB.getConnections()) {
                int recentPosX = connection.getEndpoint0().getPosX();
                int recentPosY = connection.getEndpoint0().getPosY();

                int midX = (getRandomNumber(0, recentPCB.getWidth())*8 +connection.getEndpoint1().getPosX() + recentPosX)/10;
                int midY = (getRandomNumber(0, recentPCB.getHeight())*8 +connection.getEndpoint1().getPosY() + recentPosY)/10;

                connectTwoPoints(connection, recentPosX, recentPosY, midX, midY);
                connectTwoPoints(connection, midX, midY, connection.getEndpoint1().getPosX(), connection.getEndpoint1().getPosY());
            }
            list.add(recentPCB);
        }

        return list;
    }

    private void connectTwoPoints(PCBConnection connection, int recentPosX, int recentPosY, int destinationPosX, int destinationPosY){
        int xDistanceLeft = destinationPosX - recentPosX;
        int yDistanceLeft = destinationPosY - recentPosY;

        boolean horizontal = true;
        int segmentLength;

        while(xDistanceLeft != 0 || yDistanceLeft != 0){
            if(horizontal){
                if(xDistanceLeft == 1){
                    connection.getPath().add(new PCBConnectionSegment(true, 1, recentPosX, recentPosY));
                    xDistanceLeft -= 1;
                    recentPosX += 1;
                }
                else if(xDistanceLeft == -1){
                    connection.getPath().add(new PCBConnectionSegment(true, -1, recentPosX, recentPosY));
                    xDistanceLeft += 1;
                    recentPosX -= 1;
                }

                if(xDistanceLeft>0){
                    segmentLength = getRandomNumber(1, Math.abs(xDistanceLeft)+1);
                    connection.getPath().add(new PCBConnectionSegment(true, segmentLength, recentPosX, recentPosY));
                    xDistanceLeft -= segmentLength;
                    recentPosX += segmentLength;
                }
                else if (xDistanceLeft<0){
                    segmentLength = getRandomNumber(1, Math.abs(xDistanceLeft)+1);
                    connection.getPath().add(new PCBConnectionSegment(true, -segmentLength, recentPosX, recentPosY));
                    xDistanceLeft += segmentLength;
                    recentPosX -= segmentLength;
                }
                horizontal = false;
            }
            else {
                if(yDistanceLeft == 1){
                    connection.getPath().add(new PCBConnectionSegment(false, 1, recentPosX, recentPosY));
                    yDistanceLeft -= 1;
                    recentPosY += 1;
                }
                else if(yDistanceLeft == -1){
                    connection.getPath().add(new PCBConnectionSegment(false, -1, recentPosX, recentPosY));
                    yDistanceLeft += 1;
                    recentPosY -= 1;
                }

                if(yDistanceLeft>0){
                    segmentLength = getRandomNumber(1, Math.abs(yDistanceLeft)+1);
                    connection.getPath().add(new PCBConnectionSegment(false, segmentLength, recentPosX, recentPosY));
                    yDistanceLeft -= segmentLength;
                    recentPosY += segmentLength;
                }
                else if(yDistanceLeft<0){
                    segmentLength = getRandomNumber(1, Math.abs(yDistanceLeft)+1);
                    connection.getPath().add(new PCBConnectionSegment(false, -segmentLength, recentPosX, recentPosY));
                    yDistanceLeft += segmentLength;
                    recentPosY -= segmentLength;
                }
                horizontal = true;
            }
        }
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
