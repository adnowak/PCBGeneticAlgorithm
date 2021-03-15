package com.company.Algorithm.GeneticAlgorithm;

import com.company.Models.PCB;
import com.company.Models.PCBConnection;
import com.company.Utils;

public class PCBCrossing {
    private PCB firstIndividual;
    private PCB secondIndividual;

    public PCBCrossing(PCB firstIndividual, PCB secondIndividual) {
        this.firstIndividual = firstIndividual;
        this.secondIndividual = secondIndividual;
    }

    public void crossover(){
        if(Math.random() < Utils.CROSS_PROB){
            int connectionsAmount = firstIndividual.getConnections().size();
            int splitIndex = Utils.getRandomNumber(1, connectionsAmount);

            for(int i = 0; i < splitIndex; i++){
                swapChromosome(i);
            }
        }
    }

    private void swapChromosome(int index){
        PCBConnection chromosome1 = firstIndividual.getConnections().get(index);
        firstIndividual.getConnections().set(index, secondIndividual.getConnections().get(index));
        secondIndividual.getConnections().set(index, chromosome1);
    }
}
