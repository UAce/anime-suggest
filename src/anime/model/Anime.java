package anime.model;

import java.net.*;
import java.util.*;

public class Anime {
	private String name;
	private int id;
	private double score;
	private int numEp;
	private boolean finishedAiring;
	private URL image;
	private List<String> tags = new ArrayList<String>();;
	
	public Anime(String name, int id, double score, int numEp, boolean finishedAiring, URL image){
		this.name = name;
		this.id = id;
		this.score = score;
		this.numEp = numEp;
		this.finishedAiring = finishedAiring;
		this.image = image;
	}
	
	
	public String getName(){
		return this.name;
	}
	
	public void addTags(ArrayList<String> tag){
		this.tags = tag;
	}
}
