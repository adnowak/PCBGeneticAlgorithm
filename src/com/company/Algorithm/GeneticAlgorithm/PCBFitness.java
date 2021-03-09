package com.company.Algorithm.GeneticAlgorithm;

import com.company.Models.PCB;
import com.company.Models.PCBConnection;
import com.company.Models.PCBConnectionSegment;
import com.company.View.PCBConsolePrinter;

public class PCBFitness extends Fitness<PCB>{
    public PCBFitness(PCB individual) {
        super(individual);
    }

    @Override
    public int getIndividualFitnessValue() {
        int collisionsAmount = (int) new PCBConsolePrinter(individual).getPCBConsoleRepresentation().chars().filter(ch -> ch == '&').count();
        int totalLength = 0;
        for (PCBConnection connection : individual.getConnections()) {
            for (PCBConnectionSegment segment : connection.getPath()) {
                totalLength += Math.abs(segment.getDirectionalLength());
            }
        }

        return 1000 - collisionsAmount*10 - totalLength;
    }
}
