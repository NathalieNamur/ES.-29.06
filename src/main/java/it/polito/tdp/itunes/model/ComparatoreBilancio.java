package it.polito.tdp.itunes.model;

import java.util.Comparator;

public class ComparatoreBilancio implements Comparator<BilancioAlbum> {
	
	
	@Override
	public int compare(BilancioAlbum b1, BilancioAlbum b2) {
	
		return Integer.compare(b2.getBilancio(), b1.getBilancio()); //!!
	}

}
