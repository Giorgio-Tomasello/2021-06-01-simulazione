package it.polito.tdp.genes.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class Simulator {
	
	
	
	//coda degli eventi
	private PriorityQueue<Evento> queue;
	
	//dati in input
	Genes gene;
	int nIngegneri;
	private double probabilita;
	
	//dati in output
	private List<Genes> geniStudiati;
	private int nIngegneri_associati;
	
	
	//stato del mondo
	private Graph< Genes, DefaultWeightedEdge> grafo;
	private List<Coppia> archi;
	private Map<Integer, PriorityQueue<Evento>> mappa_eventi;
	
	
	public void init(int num_ing, Genes g, Graph< Genes, DefaultWeightedEdge> grafo, List<Coppia> archi ) {
		
		this.queue = new PriorityQueue<>();
		this.mappa_eventi = new HashMap<Integer, PriorityQueue<Evento>>();
		this.gene = g;
		this.grafo = grafo;
		this.archi = archi;
		
		
		
		
		
	}
	

}
