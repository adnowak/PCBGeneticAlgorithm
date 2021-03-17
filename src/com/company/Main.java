package com.company;

import com.company.Algorithm.GeneticAlgorithm.*;
import com.company.Algorithm.RandomAlgorithm.RandomPCBGenerator;
import com.company.Data.PCBDeserializerFile;
import com.company.Models.PCB;
import com.company.View.PCBConsolePrinter;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        PCB pcb = new PCBDeserializerFile().deserializeFromFile("resources/zad0.txt");
        System.out.println("Empty PCB: ");
        new PCBConsolePrinter(pcb).print();

        ArrayList<PCB> pcbs = new RandomPCBGenerator(pcb).generate(100);
        PCB best = pcb;
        int bestFitness = 0;
        int recentFitness;
        for (PCB recentPCB : pcbs){
            recentFitness = new PCBFitness(recentPCB).getIndividualFitnessValue();
            if(recentFitness > bestFitness){
                best = recentPCB;
                bestFitness = recentFitness;
            }
        }

        System.out.println("Best found PCB: ");
        new PCBConsolePrinter(best).print();
        System.out.println("Fitness: " + bestFitness);

        System.out.println("Tournament");

        ArrayList<PCB> pcbsPair = new PCBSelection(pcbs).tournamentSelection(10);
        new PCBConsolePrinter(pcbsPair.get(0)).print();
        System.out.println("Fitness: " + new PCBFitness(pcbsPair.get(0)).getIndividualFitnessValue());

        new PCBConsolePrinter(pcbsPair.get(1)).print();
        System.out.println("Fitness: " + new PCBFitness(pcbsPair.get(1)).getIndividualFitnessValue());

        System.out.println("Roulette");

        pcbsPair = new PCBSelection(pcbs).rouletteSelection();
        new PCBConsolePrinter(pcbsPair.get(0)).print();
        System.out.println("Fitness: " + new PCBFitness(pcbsPair.get(0)).getIndividualFitnessValue());

        new PCBConsolePrinter(pcbsPair.get(1)).print();
        System.out.println("Fitness: " + new PCBFitness(pcbsPair.get(1)).getIndividualFitnessValue());

        PCBCrossing crossing = new PCBCrossing(pcbsPair.get(0), pcbsPair.get(1));
        pcbsPair = crossing.crossover();

        System.out.println("After crossing");

        new PCBConsolePrinter(pcbsPair.get(0)).print();
        System.out.println("Fitness: " + new PCBFitness(pcbsPair.get(0)).getIndividualFitnessValue());

        new PCBConsolePrinter(pcbsPair.get(1)).print();
        System.out.println(pcbsPair.get(1).getConnections().get(0).getPath().size());
        System.out.println("Fitness: " + new PCBFitness(pcbsPair.get(1)).getIndividualFitnessValue());

        PCB mutatedPCB = new PCBMutation(pcbsPair.get(1)).mutate();

        System.out.println("After mutation");

        new PCBConsolePrinter(mutatedPCB).print();
        System.out.println(mutatedPCB.getConnections().get(0).getPath().size());
        System.out.println("Fitness: " + new PCBFitness(mutatedPCB).getIndividualFitnessValue());

        System.out.println("After genetic algorithm:");
        pcb = new PCBGeneticAlgorithm(pcbs, 100).generateBestWithRoulette();
        new PCBConsolePrinter(pcb).print();
        System.out.println("Fitness: " + new PCBFitness(pcb).getIndividualFitnessValue());
    }
}
