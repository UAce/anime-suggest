package anime.controller;

import java.net.*;

import anime.model.Anime;
import anime.model.AnimeListManager;
import anime.persistence.PersistenceXStream;

public class AnimeController {
	
	public AnimeController(){}
	
	public void createAnime(String name, int id, double score, int numEp, boolean finishedAiring, URL image) throws InvalidInputException{
		if(name == null || name.trim().length() == 0)
			throw new InvalidInputException("Anime name cannot be empty...");
		Anime a = new Anime(name, id, score, numEp, finishedAiring, image);
		AnimeListManager aml = AnimeListManager.getInstance();
		aml.addAnime(a);
		PersistenceXStream.saveToXMLwithXStream(aml);
	}
}
	