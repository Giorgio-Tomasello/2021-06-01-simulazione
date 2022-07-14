package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
	
	private GenesDao dao;
	private Graph< Genes, DefaultWeightedEdge> grafo;
	private List<Genes> vertici;
	private HashMap<String, Genes> idMap = new HashMap<String, Genes>();
	private List <Coppia> archi;
	

	public Model() {
			
			this.dao = new GenesDao();
			
		}
	

	public Graph<Genes, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}


	public void setGrafo(Graph<Genes, DefaultWeightedEdge> grafo) {
		this.grafo = grafo;
	}


	public String  creaGrafo() {
			
			this.grafo = new SimpleWeightedGraph<Genes,DefaultWeightedEdge>(DefaultWeightedEdge.class);
			
			vertici = new ArrayList<Genes>(dao.getVertici());
			for(Genes g : vertici)
				{idMap.put(g.getGeneId(), g);}
			
			Graphs.addAllVertices(this.grafo, vertici);
			
			archi = new ArrayList<Coppia>(dao.getArchi(idMap));
			
			for(Coppia c : archi)
				Graphs.addEdge(this.grafo, c.getG1(), c.getG2(), c.getPeso());
			
			String output = "GRAFO CREATO" + "\n" + "Numero vertici: " + this.grafo.vertexSet().size() + 
					"\nNumero Archi: " + this.grafo.edgeSet().size();

			return output;
		}
	
	public String getAdiacenze(Genes g) {
		
		List<Adiacenza> adiacenti = new ArrayList<Adiacenza>(); 
		
		for(Coppia c : archi) {
			if(c.getG1().equals(g))
				{adiacenti.add(new Adiacenza(c.getG2(), c.getPeso()));}
			else if(c.getG2().equals(g))
			{
				adiacenti.add(new Adiacenza(c.getG1(), c.getPeso()));
			}
		}
		
		Collections.sort(adiacenti, new ComparatoreAdiacenze());		
		
		String output = "Geni adiacenti a "+ g.getGeneId()+"\n";
		for(Adiacenza a : adiacenti)
			output += a.getG() + " " + a.getPeso() + "\n";
		
		
		return output;		
	}
	
	//public simula(Genes g, int n) {
		
		
	//}
	
	
	
}
