package com.company.Algorithm.GeneticAlgorithm;

import com.company.Models.PCB;
import com.company.Models.PCBConnection;
import com.company.Utils;

import java.util.ArrayList;

public class PCBCrossing {
    private PCB firstIndividual;
    private PCB secondIndividual;

    public PCBCrossing(PCB firstIndividual, PCB secondIndividual) {
        this.firstIndividual = new PCB(firstIndividual);
        this.secondIndividual = new PCB(secondIndividual);
    }

    public ArrayList<PCB> crossover(){
        if(Math.random() < Utils.CROSS_PROB){
            int connectionsAmount = firstIndividual.getConnections().size();
            int splitIndex = Utils.getRandomNumber(1, connectionsAmount);

            for(int i = 0; i < splitIndex; i++){
                swapChromosome(i);
            }
        }
        ArrayList<PCB> result = new ArrayList<>();
        result.add(firstIndividual);
        result.add(secondIndividual);
        return result;
    }

    private void swapChromosome(int index){
        PCBConnection chromosome1 = firstIndividual.getConnections().get(index);
        firstIndividual.getConnections().set(index, secondIndividual.getConnections().get(index));
        secondIndividual.getConnections().set(index, chromosome1);
    }
}
