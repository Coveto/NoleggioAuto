package it.polito.tdp.noleggio.model;

import java.time.Duration;

public class TestSimulatore {
	
	public static void main(String[] args) {
		Simulatore sim = new Simulatore();
		
		sim.setInTime(Duration.ofMinutes(5));
		
		sim.init(25);
		
		sim.run();
		
		System.out.format("%d clienti totali, di cui %d insoddisfatti\n", sim.getNumeroClientiTotali(), sim.getNumeroClientiInsoddisfatti());
	}

}
