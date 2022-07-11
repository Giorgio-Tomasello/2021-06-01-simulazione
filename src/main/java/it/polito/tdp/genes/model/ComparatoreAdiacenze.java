package it.polito.tdp.genes.model;

import java.util.Comparator;

public class ComparatoreAdiacenze implements Comparator<Adiacenza> {

	@Override
	public int compare(Adiacenza o1, Adiacenza o2) {
		if(o1.getPeso()<o2.getPeso())
			return 1;
		if(o1.getPeso()>o2.getPeso())
			return -1;
		
		return 0;
					
	}
	
	

}
