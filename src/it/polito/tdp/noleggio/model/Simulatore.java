package it.polito.tdp.noleggio.model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class Simulatore {
	
	private PriorityQueue<Evento> queue = new PriorityQueue<>() ;

	// World Status
	private int totCars ;
	private int availCars ;
	
	// Simulation parameter
	private Duration inTime = Duration.ofMinutes(10);
	private LocalTime startH = LocalTime.of(8, 0);
	private LocalTime endH = LocalTime.of(20, 0);
	private List<Duration> durateNoleggio;
	
	// Data and statistics
	private int numeroClientiTotali ;
	private int numeroClientiInsoddisfatti ;
	
	// Internal Variable
	private Random rand = new Random();
	
	public Simulatore() {
		durateNoleggio = new ArrayList<Duration>();
		durateNoleggio.add(Duration.ofHours(1));
		durateNoleggio.add(Duration.ofHours(2));
		durateNoleggio.add(Duration.ofHours(3));
	}
	
	public void init(int totCars) {
		// Prepare fundamentals data
		this.totCars = totCars;
		this.availCars = this.totCars;
		this.numeroClientiInsoddisfatti = 0;
		this.numeroClientiTotali = 0;
		
		this.queue.clear();
		
		// Init starting events (anche metodo separato)
		
		for(LocalTime h = startH; h.isBefore(endH); 
				h = h.plus(inTime)) {
			
			Evento e = new Evento(h, Evento.TipoEvento.CLIENTE_ARRIVA);
			queue.add(e);
		}
		
	}
	


	public void run() {
		
		while(!queue.isEmpty()) {
			// Lo tolgo dalla lista con poll, peek lo lascia
			Evento ev = queue.poll();
			
			switch(ev.getTipo()) {
			// per ogni evento (posso) aggiornare statistiche, world status e creare nuovi eventi
			case CLIENTE_ARRIVA:
				this.numeroClientiTotali++ ;
				
				if(this.availCars==0) {
					this.numeroClientiInsoddisfatti++;
				} else {
					this.availCars--;
					int i = rand.nextInt(durateNoleggio.size());
//					int i = (int) (Math.random()*durateNoleggio.size())
					Duration d = this.durateNoleggio.get(i);
					
					LocalTime back = ev.getTempo().plus(d);
					
					queue.add(new Evento(back, Evento.TipoEvento.AUTO_RESTITUITA));
				}
				break;
				
			case AUTO_RESTITUITA:
				this.availCars++;
				break;
				
			}
		}
		
	}

	public Duration getInTime() {
		return inTime;
	}

	public void setInTime(Duration inTime) {
		this.inTime = inTime;
	}

	public LocalTime getStartH() {
		return startH;
	}

	public void setStartH(LocalTime startH) {
		this.startH = startH;
	}

	public LocalTime getEndH() {
		return endH;
	}

	public void setEndH(LocalTime endH) {
		this.endH = endH;
	}

	public List<Duration> getDurateNoleggio() {
		return durateNoleggio;
	}

	public void setDurateNoleggio(List<Duration> durateNoleggio) {
		this.durateNoleggio = durateNoleggio;
	}

	public int getTotCars() {
		return totCars;
	}

	public int getAvailCars() {
		return availCars;
	}

	public int getNumeroClientiTotali() {
		return numeroClientiTotali;
	}

	public int getNumeroClientiInsoddisfatti() {
		return numeroClientiInsoddisfatti;
	}
	
	
	
}
