package it.polito.tdp.genes.model;

public class Evento implements Comparable<Evento>{
	
	public enum EventType{
		
		STESSOGENE,
		CAMBIOGENE
		
	}
	
	private EventType tipo;
	

	@Override
	public int compareTo(Evento o) {
		
		return 0;
	}
	

}
