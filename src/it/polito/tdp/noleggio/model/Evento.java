package it.polito.tdp.noleggio.model;

import java.time.LocalTime;

public class Evento implements Comparable<Evento> {
	
	public enum TipoEvento {
		CLIENTE_ARRIVA,
		AUTO_RESTITUITA
	}
	
	private LocalTime tempo ;
	private TipoEvento tipo ;
	
	public Evento(LocalTime tempo, TipoEvento tipo) {
		this.tempo = tempo;
		this.tipo = tipo;
	}

	public LocalTime getTempo() {
		return tempo;
	}

	public TipoEvento getTipo() {
		return tipo;
	}

	@Override
	public int compareTo(Evento other) {
		return this.tempo.compareTo(other.tempo);
	}

}
