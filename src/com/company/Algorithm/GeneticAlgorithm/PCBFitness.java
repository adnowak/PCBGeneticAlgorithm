package com.company.Algorithm.GeneticAlgorithm;

import com.company.Models.PCB;
import com.company.Models.PCBConnection;
import com.company.Models.PCBConnectionSegment;
import com.company.View.PCBConsolePrinter;

public class PCBFitness{
    private PCB individual;

    public PCBFitness(PCB individual) {
        this.individual = individual;
    }

    public int getIndividualFitnessValue() {
        int collisionsAmount = (int) new PCBConsolePrinter(individual).getPCBConsoleRepresentation().chars().filter(ch -> ch == '&').count();

        int totalLength = 0;
        for (PCBConnection connection : individual.getConnections()) {
            for (PCBConnectionSegment segment : connection.getPath()) {
                totalLength += Math.abs(segment.getDirectionalLength());
            }
        }

        return 10000 - collisionsAmount*100 - totalLength;
    }
}
