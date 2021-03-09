package com.company;

import com.company.Algorithm.GeneticAlgorithm.PCBFitness;
import com.company.Algorithm.RandomAlgorithm.RandomPCBGenerator;
import com.company.Data.PCBDeserializerFile;
import com.company.Models.PCB;
import com.company.View.PCBConsolePrinter;

public class Main {

    public static void main(String[] args) {
        PCB pcb = new PCBDeserializerFile().deserializeFromFile("resources/zad0.txt");
        new RandomPCBGenerator(pcb).generate();
        new PCBConsolePrinter(pcb).print();
        System.out.println("Fitness: " + new PCBFitness(pcb).getIndividualFitnessValue());
    }
}
