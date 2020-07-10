package com.myanimedb.model;

import java.util.HashMap;

public class Anime implements Comparable<Object> {
	private int animeId = 0;
	private String animeName;
	private String animeAltName;
	private String description;
	private int episodeCount = 0;
	private double animeScore = 0;
	private boolean favorite = false;
	private WatchHistory watchStatus;
	private HashMap<String, Double> animeTraits;

	// Overloaded Constructors
	public Anime() {
		this.animeTraits = new HashMap<String, Double>();
		this.watchStatus = WatchHistory.UNKNOWN;
	}

	public Anime(String animeName) {
		this.animeTraits = new HashMap<String, Double>();
		this.watchStatus = WatchHistory.UNKNOWN;
		this.animeName = animeName;
	}

	public Anime(String animeName, double animeScore, boolean favorite) {
		this.animeTraits = new HashMap<String, Double>();
		this.watchStatus = WatchHistory.UNKNOWN;
		this.animeName = animeName;
		this.animeScore = animeScore;
		this.favorite = favorite;
	}

	// Using an ellipsis to add multiple traits
	public Anime(String animeName, String animeAltName, double animeScore, boolean favorite, int episodeCount,
			String description, String... animeTraits) {
		this.animeTraits = new HashMap<String, Double>();
		this.watchStatus = WatchHistory.UNKNOWN;
		this.animeName = animeName;
		this.animeAltName = animeAltName;
		this.animeScore = animeScore;
		this.favorite = favorite;
		this.episodeCount = episodeCount;
		this.description = description;
		for (String strings : animeTraits) {
			this.animeTraits.put(strings, 0.0);
		}
	}

	// Access and Modifiers
	public String getAnimeName() {
		return animeName;
	}

	public int getAnimeId() {
		return animeId;
	}

	public String getAnimeAltName() {
		return animeAltName;
	}

	public double getAnimeScore() {
		return animeScore;
	}

	public int getEpisodeCount() {
		return episodeCount;
	}

	public boolean isFavorite() {
		return favorite;
	}

	public String getDescription() {
		return description;
	}

	public WatchHistory getWatchStatus() {
		return watchStatus;
	}

	public HashMap<String, Double> getAnimeTraits() {
		return animeTraits;
	}

	public void setAnimeId(int animeId) {
		this.animeId = animeId;
	}

	public void setAnimeName(String animeName) {
		this.animeName = animeName;
	}

	public void setAnimeAltName(String animeAltName) {
		this.animeAltName = animeAltName;
	}

	public void setAnimeScore(double animeScore) {
		this.animeScore = animeScore;
	}

	public void setEpisodeCount(int episodeCount) {
		this.episodeCount = episodeCount;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setWatchStatus(WatchHistory status) {
		this.watchStatus = status;
	}

	public void setAnimeTrait(String trait, Double score) {
		this.animeTraits.put(trait, score);
	}

	@Override
	public String toString() {
		String theTraits = "( ";
		for (String string : animeTraits.keySet()) {
			theTraits += string + " ";
		}
		theTraits += ")";
		return "\nAnime #" + animeId + ": [" + animeName + ", (Alt-Name): " + animeAltName + ", Score: " + animeScore
				+ ", Favorite: " + favorite + ", Episodes: " + episodeCount + ",\n\t\tDescription: " + description
				+ ", Watch Status: " + watchStatus + " Anime Traits: " + theTraits + "]";
	}

	// Made to compare anime scores in descending order
	@Override
	public int compareTo(Object o) {
		Anime otherAnime = (Anime) o;
		if (this.animeScore > otherAnime.animeScore) {
			return -1;
		} else if (this.animeScore < otherAnime.animeScore) {
			return 1;
		}
		return 0;
	}

}
