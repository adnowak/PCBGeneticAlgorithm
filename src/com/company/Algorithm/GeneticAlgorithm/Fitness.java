package com.company.Algorithm.GeneticAlgorithm;

public abstract class Fitness<T> {
    protected T individual;

    public Fitness(T individual) {
        this.individual = individual;
    }

    public abstract int getIndividualFitnessValue();
}
