package com.company;

import com.company.Algorithm.GeneticAlgorithm.PCBCrossing;
import com.company.Algorithm.GeneticAlgorithm.PCBFitness;
import com.company.Algorithm.GeneticAlgorithm.PCBSelection;
import com.company.Algorithm.RandomAlgorithm.RandomPCBGenerator;
import com.company.Data.PCBDeserializerFile;
import com.company.Models.PCB;
import com.company.View.PCBConsolePrinter;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        PCB pcb = new PCBDeserializerFile().deserializeFromFile("resources/zad1.txt");
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
        crossing.crossover();

        System.out.println("After crossing");

        new PCBConsolePrinter(pcbsPair.get(0)).print();
        System.out.println("Fitness: " + new PCBFitness(pcbsPair.get(0)).getIndividualFitnessValue());

        new PCBConsolePrinter(pcbsPair.get(1)).print();
        System.out.println("Fitness: " + new PCBFitness(pcbsPair.get(1)).getIndividualFitnessValue());
    }
}
