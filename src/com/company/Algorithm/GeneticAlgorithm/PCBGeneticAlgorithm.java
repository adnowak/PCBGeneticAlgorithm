package com.company.Algorithm.GeneticAlgorithm;

import com.company.Models.PCB;
import com.company.Utils;

import java.util.ArrayList;

public class PCBGeneticAlgorithm {
    private ArrayList<PCB> recentGeneration;
    private int generationsAmount;

    public PCBGeneticAlgorithm(ArrayList<PCB> recentGeneration, int generationsAmount) {
        this.recentGeneration = recentGeneration;
        this.generationsAmount = generationsAmount;
    }

    public PCB generateBestWithRoulette(){
        runWithRoulette();
        return getRecentBest();
    }

    public PCB generateBestWithTournament(){
        runWithTournament();
        return getRecentBest();
    }

    private void runWithRoulette() {
        ArrayList<PCB> newGeneration = new ArrayList<>();
        ArrayList<PCB> newPair;
        for(int i = 0; i<generationsAmount; i++){
            for(int j = 0; j<recentGeneration.size(); j+=2){
                newPair = new PCBSelection(recentGeneration).rouletteSelection();
                newPair = new PCBCrossing(newPair.get(0), newPair.get(1)).crossover();
                newGeneration.add(new PCBMutation(newPair.get(0)).mutate());
                newGeneration.add(new PCBMutation(newPair.get(1)).mutate());
            }
        }
    }

    private void runWithTournament() {
        ArrayList<PCB> newGeneration = new ArrayList<>();
        ArrayList<PCB> newPair;
        for(int i = 0; i<generationsAmount; i++){
            for(int j = 0; j<recentGeneration.size(); j+=2){
                newPair = new PCBSelection(recentGeneration).tournamentSelection(Utils.TOURNAMENT_SIZE);
                newPair = new PCBCrossing(newPair.get(0), newPair.get(1)).crossover();
                newGeneration.add(new PCBMutation(newPair.get(0)).mutate());
                newGeneration.add(new PCBMutation(newPair.get(1)).mutate());
            }
        }
    }

    private PCB getRecentBest(){
        PCB recentBest = recentGeneration.get(0);
        int recentBestFitness = new PCBFitness(recentBest).getIndividualFitnessValue();
        int recentFitness;

        for (PCB pcb : recentGeneration) {
            recentFitness = new PCBFitness(pcb).getIndividualFitnessValue();
            if(recentFitness > recentBestFitness){
                recentBest = pcb;
                recentBestFitness = recentFitness;
            }
        }

        return recentBest;
    }
}
