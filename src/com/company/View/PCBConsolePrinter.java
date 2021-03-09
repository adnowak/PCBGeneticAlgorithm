package com.company.View;

import com.company.Models.PCB;
import com.company.Models.PCBConnection;
import com.company.Models.PCBConnectionSegment;

public class PCBConsolePrinter {
    private PCB pcb;

    public PCBConsolePrinter(PCB pcb) {
        this.pcb = pcb;
    }

    private String prepareEmptyPCB(){
        String emptyPCB = "";
        for (int y=pcb.getHeight()-1; y>=0; y--){
            emptyPCB += "| ";
            for (int x=0; x<pcb.getWidth(); x++){
                emptyPCB += "  ";
            }
            emptyPCB += "|\n";
        }
        return emptyPCB;
    }

    private String addConnectionEndpoints(String emptyPCB, PCBConnection connection, int connectionNumber){
        StringBuilder sb = new StringBuilder(emptyPCB);
        int firstCharIndex = connection.getEndpoint0().getPosY()*(pcb.getWidth()+2)*2+connection.getEndpoint0().getPosX()*2+1;
        int secondCharIndex = connection.getEndpoint1().getPosY()*(pcb.getWidth()+2)*2+connection.getEndpoint1().getPosX()*2+1;

        if(sb.charAt(firstCharIndex) == ' ' || sb.charAt(firstCharIndex) == (char) (connectionNumber)){
            sb.setCharAt(firstCharIndex, (char) (connectionNumber));
        }
        else{
            sb.setCharAt(firstCharIndex, '&');
        }

        if(sb.charAt(secondCharIndex) == ' ' || sb.charAt(secondCharIndex) == (char) (connectionNumber)){
            sb.setCharAt(secondCharIndex, (char) (connectionNumber));
        }
        else{
            sb.setCharAt(secondCharIndex, '&');
        }
        return sb.toString();
    }

    private String addConnectionPaths(String emptyPCB, PCBConnection connection, int connectionNumber){
        StringBuilder sb = new StringBuilder(emptyPCB);

        for (PCBConnectionSegment segment : connection.getPath()) {
            int recentPosX = segment.getStartingPosX();
            int recentPosY = segment.getStartingPosY();

            if (segment.getDirectionalLength() > 0){
                for(int i = 0; i<segment.getDirectionalLength(); i++){
                    switchCharacter(sb, recentPosX, recentPosY, connectionNumber);
                    if (segment.isHorizontal()){
                        recentPosX++;
                    }
                    else {
                        recentPosY++;
                    }
                }
            }
            else if (segment.getDirectionalLength() < 0){
                for(int i = 0; i<Math.abs(segment.getDirectionalLength()); i++){
                    switchCharacter(sb, recentPosX, recentPosY, connectionNumber);
                    if (segment.isHorizontal()){
                        recentPosX--;
                    }
                    else {
                        recentPosY--;
                    }
                }
            }
        }

        return sb.toString();
    }

    private void switchCharacter(StringBuilder sb, int recentPosX, int recentPosY, int charNumber) {
        int charIndex = recentPosY*(pcb.getWidth()+2)*2+recentPosX*2+1;

        if(sb.charAt(charIndex) == ' ' || sb.charAt(charIndex) == (char) (charNumber)){
            sb.setCharAt(charIndex, (char) (charNumber));
        }
        else{
            sb.setCharAt(charIndex, '&');
        }
    }

    private String addConnections(String PCB){
        int connectionNumber = 65;
        for (PCBConnection connection : pcb.getConnections()) {
            PCB = addConnectionPaths(addConnectionEndpoints(PCB, connection, connectionNumber), connection, connectionNumber);
            connectionNumber++;


            if(connectionNumber > 66){
//                break;
            }
        }
        return PCB;
    }

    public void print(){
        System.out.println(addConnections(prepareEmptyPCB()));
    }
}
