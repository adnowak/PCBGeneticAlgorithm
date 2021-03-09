package com.company.Algorithm.RandomAlgorithm;

import com.company.Models.PCB;
import com.company.Models.PCBConnection;
import com.company.Models.PCBConnectionSegment;

import java.util.Random;

public class RandomPCBGenerator {
    private PCB pcb;

    public RandomPCBGenerator(PCB basePBC) {
        this.pcb = basePBC;
    }

    public void generate(){
        for (PCBConnection connection: pcb.getConnections()) {
            int recentPosX = connection.getEndpoint0().getPosX();
            int recentPosY = connection.getEndpoint0().getPosY();

            int xDistanceLeft = connection.getEndpoint1().getPosX() - connection.getEndpoint0().getPosX();
            int yDistanceLeft = connection.getEndpoint1().getPosY() - connection.getEndpoint0().getPosY();

            boolean horizontal = true;
            int segmentLength = 1;

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
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
