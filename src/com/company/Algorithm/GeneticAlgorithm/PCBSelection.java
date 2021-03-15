package com.company.Algorithm.GeneticAlgorithm;

import com.company.Models.PCB;
import com.company.Utils;
import java.util.ArrayList;

public class PCBSelection {
    private ArrayList<PCB> generation;

    public PCBSelection(ArrayList<PCB> generation) {
        this.generation = generation;
    }

    public ArrayList<PCB> tournamentSelection(int tournamentSize){
        ArrayList<PCB> pcbsPair = new ArrayList<>();
        PCB bestFirstTourPCB = null;
        PCB bestSecondTourPCB = null;
        PCB newFirstTourPCB;
        PCB newSecondTourPCB;
        int bestFirstTourFitness = 0;
        int bestSecondTourFitness = 0;
        int newFirstTourFitness = 0;
        int newSecondTourFitness = 0;

        for(int i = 0; i <tournamentSize; i++){
            newFirstTourPCB = generation.get(Utils.getRandomNumber(0, generation.size()));
            newSecondTourPCB = generation.get(Utils.getRandomNumber(0, generation.size()));
            newFirstTourFitness = new PCBFitness(newFirstTourPCB).getIndividualFitnessValue();
            newSecondTourFitness = new PCBFitness(newSecondTourPCB).getIndividualFitnessValue();

            if(newFirstTourFitness > bestFirstTourFitness){
                bestFirstTourFitness = newFirstTourFitness;
                bestFirstTourPCB = newFirstTourPCB;
            }

            if(newSecondTourFitness > bestSecondTourFitness){
                bestSecondTourFitness = newSecondTourFitness;
                bestSecondTourPCB = newSecondTourPCB;
            }
        }

        pcbsPair.add(bestFirstTourPCB);
        pcbsPair.add(bestSecondTourPCB);

        return pcbsPair;
    }

    public ArrayList<PCB> rouletteSelection(){
        ArrayList<PCB> pcbsPair = new ArrayList<>();

        long fitnessSum = 0;
        for (PCB individual : generation) {
            fitnessSum += new PCBFitness(individual).getIndividualFitnessValue();
        }

        long firstIndex = Utils.getRandomNumber(0, fitnessSum);
        long secondIndex = Utils.getRandomNumber(0, fitnessSum);

        int recentIndex = 0;
        for (PCB individual : generation) {
            recentIndex += new PCBFitness(individual).getIndividualFitnessValue();
            if(recentIndex > firstIndex){
                pcbsPair.add(individual);
                break;
            }
        }

        for (PCB individual : generation) {
            recentIndex += new PCBFitness(individual).getIndividualFitnessValue();
            if(recentIndex > secondIndex){
                pcbsPair.add(individual);
                break;
            }
        }

        return pcbsPair;
    }
}
