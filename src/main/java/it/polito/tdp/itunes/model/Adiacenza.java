package it.polito.tdp.itunes.model;

public class Adiacenza {
	
	private Album a1;
	private Album a2;
	private int num;
	
	
	
	public Adiacenza(Album a1, Album a2, int num) {
		
		this.a1 = a1;
		this.a2 = a2;
		this.num = num;
	}


	
	public Album getA1() {
		return a1;
	}

	public Album getA2() {
		return a2;
	}

	public int getNum() {
		return num;
	}
	

}
