package com.tbk.ThoiKhoaBieu.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Population {

	private List<Individual> population;
	private double populationFitness = -1;

	public Population(int populationSize, Timetable timetable) {
		this.population = new ArrayList<>();

		// Loop over population size
		for (int individualCount = 0; individualCount < populationSize; individualCount++) {
			
			Individual individual = new Individual(timetable);
			// Add individual to population
			this.population.add(individual);
		}
	}
}
