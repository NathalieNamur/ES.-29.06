package it.polito.tdp.itunes.model;

import java.util.Comparator;

public class ComparatoreTitolo implements Comparator<Album> {
	
	@Override
	public int compare(Album a1, Album a2) {

		return a1.getTitle().compareTo(a2.getTitle());
	}

}
