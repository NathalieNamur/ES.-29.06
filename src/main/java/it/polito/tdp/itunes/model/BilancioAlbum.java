package it.polito.tdp.itunes.model;

public class BilancioAlbum {
	
	private Album album; 
	private int bilancio;
	
	
	
	public BilancioAlbum(Album album, int bilancio) {
	
		this.album = album;
		this.bilancio = bilancio;
	}


	
	public Album getAlbum() {
		return album;
	}

	public int getBilancio() {
		return bilancio;
	}


	@Override
	public String toString() {
		return album.getTitle()+", bilancio=" + bilancio;
	}

	
}
