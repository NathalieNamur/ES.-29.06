package it.polito.tdp.itunes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.itunes.db.ItunesDAO;

public class Model {
	
	private ItunesDAO dao;
	
	private Graph<Album,DefaultWeightedEdge> grafo;
	
	private List<Album> vertici; 
	
	
	
	public Model() {
		
		dao = new ItunesDAO();
		
		vertici = new ArrayList<>();
	} 
	
	
	
	//METODO DI CREAZIONE E POPOLAMENTO GRAFO:
	public void creaGrafo(int n) {
		
		grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
		
		Graphs.addAllVertices(grafo, dao.getVertici(n));
		vertici = dao.getVertici(n);
		
		
		List<Adiacenza> adiacenze = new ArrayList<>();
		
		for(Album a1 : grafo.vertexSet()) {
			for(Album a2: grafo.vertexSet()) {
				
				int num1 = dao.getNumCanzoni(a1.getAlbumId(), n); 
				int num2 = dao.getNumCanzoni(a2.getAlbumId(), n); 
				
				if((num1-num2) != 0) {
					
					if(num1>num2) {
						Adiacenza a = new Adiacenza(a2,a1,num1-num2);
						adiacenze.add(a);
					}
					
					else {
						Adiacenza a = new Adiacenza(a1,a2,num2-num1);	
						adiacenze.add(a);
					}
				}
				
			}
		}
		
		for(Adiacenza a : adiacenze) {
			Graphs.addEdgeWithVertices(grafo, a.getA1(), a.getA2(), a.getNum());
		}
	
	}

	public int getNumVertici() {
		return grafo.vertexSet().size();
	}
	
	public int getNumArchi() {
		return grafo.edgeSet().size();
	}
	
	
	//METODO CHE RESTITUISCE LA LISTA ORDINATA DEI VERTICI:
	public List<Album> getVertici(){
		
		Collections.sort(vertici, new ComparatoreTitolo());
		return vertici;
	}
	
	
	//METODO CHE RESTITUISCE LA LISTA DEI SUCCESSORI ORDINATA PER BILANCIO:
	public List<BilancioAlbum> getSuccessori(Album a) {
		
		List<Album> successori = new ArrayList<>();
		
		for(Album v : grafo.vertexSet()) 
			successori = Graphs.successorListOf(grafo, a);
			
		
		List<BilancioAlbum> result = new ArrayList<>();
		
		for(Album s : successori) {
			BilancioAlbum b = new BilancioAlbum(s, this.getBilancio(s));
			result.add(b);
		}
		
		
		Collections.sort(result, new ComparatoreBilancio());
		return result; 
			
	}
	
	public int getBilancio (Album a) {
		
		int bilancio = 0;
		int in = 0;
		int out = 0;
		
		for(DefaultWeightedEdge e : grafo.edgeSet()) {
			
			if(grafo.getEdgeSource(e).equals(a))
				out += grafo.getEdgeWeight(e);
		
			else if(grafo.getEdgeTarget(e).equals(a))
				in += grafo.getEdgeWeight(e);
		}
		
		bilancio = in - out;
		
		return bilancio; 
		
	}
	
	
}
