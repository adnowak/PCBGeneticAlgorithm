package com.company;

import com.company.Algorithm.GeneticAlgorithm.PCBFitness;
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

        ArrayList<PCB> pcbs = new RandomPCBGenerator(pcb).generate(10);
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
    }
}
