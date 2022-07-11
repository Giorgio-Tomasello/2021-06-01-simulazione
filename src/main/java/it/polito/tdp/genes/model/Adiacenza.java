package it.polito.tdp.genes.model;

import java.util.Objects;

public class Adiacenza {
	
	Genes g;
	double peso;
	public Adiacenza(Genes g, double peso) {
		super();
		this.g = g;
		this.peso = peso;
	}
	public Genes getG() {
		return g;
	}
	public void setG(Genes g) {
		this.g = g;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	@Override
	public int hashCode() {
		return Objects.hash(g, peso);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adiacenza other = (Adiacenza) obj;
		return Objects.equals(g, other.g) && Double.doubleToLongBits(peso) == Double.doubleToLongBits(other.peso);
	}
	@Override
	public String toString() {
		return g +" " +peso;
	}
	
	

}
