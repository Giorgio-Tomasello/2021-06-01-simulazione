package it.polito.tdp.genes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import it.polito.tdp.genes.model.Coppia;
import it.polito.tdp.genes.model.Genes;


public class GenesDao {
	
	public List<Genes> getAllGenes(){
		String sql = "SELECT DISTINCT GeneID, Essential, Chromosome FROM Genes";
		List<Genes> result = new ArrayList<Genes>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Genes genes = new Genes(res.getString("GeneID"), 
						res.getString("Essential"), 
						res.getInt("Chromosome"));
				result.add(genes);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Genes> getVertici(){
		String sql = "SELECT GeneID, Essential, Chromosome "
				+ "FROM Genes "
				+ "WHERE Essential = ? "
				+ "GROUP BY GeneID, Chromosome";
		List<Genes> result = new ArrayList<Genes>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, "Essential");
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Genes genes = new Genes(res.getString("GeneID"), 
						res.getString("Essential"), 
						res.getInt("Chromosome"));
				result.add(genes);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public List<Coppia> getArchi(HashMap<String, Genes> idMap){
		String sql = "SELECT Distinct  i.GeneID1, i.GeneID2, i.Type, i.Expression_Corr, g1.Chromosome, g2.Chromosome "
				+ "FROM interactions i, genes g1, genes g2 "
				+ "WHERE ((i.GeneID1 = g1.GeneID AND i.GeneID2 = g2.GeneID) OR (i.GeneID2 = g1.GeneID AND i.GeneID1 = g2.GeneID)) "
				+ "AND g1.Essential = ? "
				+ "AND g2.Essential = ? "
				+ "AND i.GeneID1 <> i.GeneID2 "
				+ "AND g1.Chromosome >= g2.Chromosome";
		
		List<Coppia> result = new ArrayList<Coppia>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, "Essential");
			st.setString(2, "Essential");
			
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Coppia coppia = new Coppia( idMap.get(res.getString("i.GeneID1")), idMap.get(res.getString("i.GeneID2")));
				if(res.getInt("g1.Chromosome") != res.getInt("g2.Chromosome"))
					{coppia.setPeso(Math.abs(res.getDouble("i.Expression_Corr")));}
				else {
					coppia.setPeso(2 * Math.abs(res.getDouble("i.Expression_Corr")));
				}
				
				result.add(coppia);
				
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	


	
}
