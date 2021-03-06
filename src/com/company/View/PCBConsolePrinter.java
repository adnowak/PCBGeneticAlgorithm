package com.company.View;

import com.company.Models.PCB;
import com.company.Models.PCBConnection;

public class PCBConsolePrinter {
    private PCB pcb;

    public PCBConsolePrinter(PCB pcb) {
        this.pcb = pcb;
    }

    private String prepareEmptyPCB(){
        String emptyPCB = "";
        for (int y=0; y<pcb.getHeight(); y++){
            emptyPCB += " ";
            for (int x=0; x<pcb.getWidth(); x++){
                emptyPCB += "  ";
            }
            emptyPCB += "\n";
        }
        return emptyPCB;
    }

    private String addConnectionEndpoints(String emptyPCB, PCBConnection connection){
        StringBuilder sb = new StringBuilder(emptyPCB);
        sb.setCharAt(connection.getEndpoint0().getPosY()*(pcb.getWidth()+1)*2+connection.getEndpoint0().getPosX()*2+1, 'X');
        sb.setCharAt(connection.getEndpoint1().getPosY()*(pcb.getWidth()+1)*2+connection.getEndpoint1().getPosX()*2+1, 'X');
        return sb.toString();
    }

    private String addConnections(String PCB){
        for (PCBConnection connection : pcb.getConnections()) {
            PCB = addConnectionEndpoints(PCB, connection);
        }
        return PCB;
    }

    public void print(){
        System.out.println(addConnections(prepareEmptyPCB()));
    }
}
