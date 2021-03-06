package com.company;

import com.company.Data.PCBDeserializerFile;
import com.company.View.PCBConsolePrinter;

public class Main {

    public static void main(String[] args) {
	    new PCBConsolePrinter(new PCBDeserializerFile().deserializeFromFile("resources/zad0.txt")).print();
    }
}
