package com.company;

import com.company.Algorithm.RandomAlgorithm.RandomPCBGenerator;
import com.company.Data.PCBDeserializerFile;
import com.company.Models.PCB;
import com.company.Models.PCBConnection;
import com.company.View.PCBConsolePrinter;

public class Main {

    public static void main(String[] args) {
        PCB pcb = new PCBDeserializerFile().deserializeFromFile("resources/zad1.txt");
        new RandomPCBGenerator(pcb).generate();
        new PCBConsolePrinter(pcb).print();
    }
}
