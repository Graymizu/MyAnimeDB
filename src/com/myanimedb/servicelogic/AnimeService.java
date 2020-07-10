package com.myanimedb.servicelogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import com.myanimedb.dal.AnimeDAL;
import com.myanimedb.model.Anime;

public class AnimeService {

	public static HashMap<Integer, Anime> animeRAM = new HashMap<Integer, Anime>();
	public static HashMap<String, Integer> reference = new HashMap<String, Integer>();
	public static Stack<Anime> trash = new Stack<Anime>();

	/*
	 * -----------------------------------------------------------------------------
	 * Loads the database into RAM and reference HashMap on start up
	 */
	static {
		List<Anime> loading = new AnimeDAL().fetchAllAnime();
		for (Anime anime : loading) {
			animeRAM.put(anime.getAnimeId(), anime);
			reference.put(anime.getAnimeName(), anime.getAnimeId());
		}
	}

	/*
	 * ----------------------------------------------------------------------
	 * Methods that access the Data Access Layer and the static Random Access Memory
	 * 
	 */

	// Saves anime to database and HashMap
	public boolean saveAnime(Anime anime) {
		AnimeDAL dataAccess = new AnimeDAL();
		boolean saved = dataAccess.saveAnime(anime);
		if (saved) {
			storeAnime(dataAccess.fetchAnime(anime.getAnimeId()));
		}
		return saved;
	}

	// Recreates the Anime that was last deleted
	public Anime reloadAnime() {
		Anime anime = restore();
		if (anime != null) {
			saveAnime(anime);
		}
		return anime;
	}

	// Deletes anime from database by ID
	public boolean deleteAnime(int animeId) {
		AnimeDAL dataAccess = new AnimeDAL();
		boolean deleted = dataAccess.deleteAnime(animeId);
		if (deleted) {
			removeAnime(quickAccess(animeId));
		}
		return deleted;
	}

	// Deletes anime from database by String
	// (needs to be altered to work with RAM)
	public boolean deleteAnime(String animeName) {
		AnimeDAL dataAccess = new AnimeDAL();
		boolean deleted = dataAccess.deleteAnime(animeName);
		if (deleted) {
			removeAnime(quickAccess(animeName));
		}
		return deleted;
	}

	/*
	 * ---------------------------------------------------------------------------
	 * Methods that manipulate the Random Access Memory HashMap and Stack that are
	 * still under development (may be changed or removed)
	 * 
	 */

	// Get from RAM -> [accesses the Random Access Memory]
	public static Anime quickAccess(int animeId) {
		return animeRAM.get(animeId);
	}

	// Get from RAM -> [accesses the Random Access Memory]
	public static Anime quickAccess(String animeName) {
		int animeId = reference.get(animeName);
		return animeRAM.get(animeId);
	}

	// Get all from RAM -> [accesses the Random Access Memory]
	public static List<Anime> quickAccessAll() {
		List<Anime> animeList = new ArrayList<Anime>(animeRAM.values());
		animeList.sort(null);
		return animeList;
	}

	// Get all anime that have a specific traint from RAM -> [Random Access Memory]
	public static List<Anime> quickAccessAll(String trait) {
		List<Anime> traitList = new LinkedList<Anime>();
		List<Anime> animeList = new ArrayList<Anime>(animeRAM.values());
		animeList.parallelStream().filter(a -> a.getAnimeTraits().containsKey(trait)).forEach(a -> traitList.add(a));
		traitList.sort(null);
		return traitList;
	}

	// Save into RAM -> [accesses the Random Access Memory]
	private static void storeAnime(Anime anime) {
		animeRAM.put(anime.getAnimeId(), anime);
		reference.put(anime.getAnimeName(), anime.getAnimeId());
	}

	// Delete from RAM -> [accesses the Random Access Memory]
	private static void removeAnime(Anime anime) {
		trash.push(animeRAM.remove(anime.getAnimeId()));
		reference.remove(anime.getAnimeName());
	}

	// -------- EXPERAMENTAL ------------

	// Updates anime in RAM -> [accesses the Random Access Memory]
	private static void quickSave(Anime anime) {
		animeRAM.replace(anime.getAnimeId(), anime);
	}

	// Restores the last deleted Anime -> [accesses the Random Access Memory]
	private static Anime restore() {
		Anime anime = null;
		if (!trash.isEmpty()) {
			anime = trash.pop();
			if (reference.containsKey(anime.getAnimeName())) {
				return null;
			}
		}
		return anime;
	}

	/*
	 * ----------------------------------------------------------------------------
	 * Standard methods that only access methods from the Data Access Layer
	 * 
	 */

	// Fetches anime by ID -> [accesses the Data Access Layer]
	public Anime deepFetchAnime(int animeId) {
		return new AnimeDAL().fetchAnime(animeId);
	}

	// Fetches anime by Name -> [accesses the Data Access Layer]
	public Anime deepFetchAnime(String animeName) {
		return new AnimeDAL().fetchAnime(animeName);
	}

	// Fetches all anime -> [accesses the Data Access Layer]
	public List<Anime> deepFetchAllAnime() {
		return new AnimeDAL().fetchAllAnime();
	}

	// Fetches all anime that share a specified trait -> [Data Access Layer]
	public List<Anime> deepFetchAllAnime(String animeTrait) {
		return new AnimeDAL().fetchAllAnime(animeTrait);
	}

}
