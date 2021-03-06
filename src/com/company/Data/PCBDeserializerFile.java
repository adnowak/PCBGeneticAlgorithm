package com.company.Data;

import com.company.Models.PCB;
import com.company.Models.PCBConnection;
import com.company.Models.PCBPoint;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PCBDeserializerFile {
    public PCB deserializeFromFile(String filePath){
        ArrayList<String> fileLines = readFromFile(filePath);

        int width = 0;
        int height = 0;
        ArrayList<PCBConnection> connections = new ArrayList<>();
        for(int i=0; i<fileLines.size(); i++){
            if(i==0){
                String[] sizes = fileLines.get(0).split(";");
                width = Integer.valueOf(sizes[0]);
                height = Integer.valueOf(sizes[1]);
            }
            else {
                String[] axisValues = fileLines.get(i).split(";");
                connections.add(
                        new PCBConnection(
                                new PCBPoint(
                                        Integer.valueOf(axisValues[0]),
                                        Integer.valueOf(axisValues[1])
                                ),
                                new PCBPoint(
                                        Integer.valueOf(axisValues[2]),
                                        Integer.valueOf(axisValues[3])
                                )
                        )
                );
            }
        }

        return new PCB(width, height, connections);
    }

    public ArrayList<String> readFromFile(String filePath){
        ArrayList<String> fileLines = new ArrayList<>();

        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                fileLines.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return fileLines;
    }
}
