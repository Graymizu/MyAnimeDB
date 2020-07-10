package com.myanimedb.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.myanimedb.model.Anime;

public class AnimeDAL {

	// Fetches a single anime by ID
	public Anime fetchAnime(int animeId) {
		try {
			Anime anime = new Anime();
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Creating connection
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/myanimedb", "root", "root");
			// Create the statement
			Statement statement = connect.createStatement();
			// Getting the results by executing SQL query
			ResultSet results = statement.executeQuery("SELECT * FROM test_anime WHERE anime_id = " + animeId + ";");
			if (results.next()) {
				anime.setAnimeId(results.getInt("anime_id"));
				anime.setAnimeName(results.getString("anime_name"));
				anime.setAnimeAltName(results.getString("anime_alt_name"));
				anime.setAnimeScore(results.getDouble("anime_score"));
				anime.setFavorite(results.getBoolean("favorite"));
				anime.setAnimeTrait(results.getString("anime_traits"), 0.0);
			}
			results.close();
			statement.close();
			connect.close();
			return anime;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Fetches a single anime by name
	public Anime fetchAnime(String title) {
		try {
			Anime anime = new Anime();
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/myanimedb", "root", "root");
			Statement statement = connect.createStatement();
			ResultSet results = statement.executeQuery(
					"SELECT * FROM test_anime WHERE anime_name = '" + title + "' OR anime_alt_name = '" + title + "';");
			if (results.next()) {
				anime.setAnimeId(results.getInt("anime_id"));
				anime.setAnimeName(results.getString("anime_name"));
				anime.setAnimeAltName(results.getString("anime_alt_name"));
				anime.setAnimeScore(results.getDouble("anime_score"));
				anime.setFavorite(results.getBoolean("favorite"));
				anime.setAnimeTrait(results.getString("anime_traits"), 0.0);
			}
			results.close();
			statement.close();
			connect.close();
			return anime;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Returns an array-list of all anime in the database
	public List<Anime> fetchAllAnime() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			List<Anime> animeList = new ArrayList<Anime>();
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/myanimedb", "root", "root");
			Statement statement = connect.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM test_anime ORDER BY anime_score DESC;");
			while (results.next()) {
				Anime anime = new Anime();
				anime.setAnimeId(results.getInt("anime_id"));
				anime.setAnimeName(results.getString("anime_name"));
				anime.setAnimeAltName(results.getString("anime_alt_name"));
				anime.setAnimeScore(results.getDouble("anime_score"));
				anime.setFavorite(results.getBoolean("favorite"));
				anime.setAnimeTrait(results.getString("anime_traits"), 0.0);
				animeList.add(anime);
			}
			results.close();
			statement.close();
			connect.close();
			return animeList;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Fetches all anime that have a specified trait
	public List<Anime> fetchAllAnime(String animeTrait) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			List<Anime> animeList = new ArrayList<Anime>();
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/myanimedb", "root", "root");
			Statement statement = connect.createStatement();
			ResultSet results = statement.executeQuery(
					"SELECT * FROM test_anime WHERE anime_traits = '" + animeTrait + "' ORDER BY anime_score DESC;");
			while (results.next()) {
				Anime anime = new Anime();
				anime.setAnimeId(results.getInt("anime_id"));
				anime.setAnimeName(results.getString("anime_name"));
				anime.setAnimeAltName(results.getString("anime_alt_name"));
				anime.setAnimeScore(results.getDouble("anime_score"));
				anime.setFavorite(results.getBoolean("favorite"));
				anime.setAnimeTrait(results.getString("anime_traits"), 0.0);
				animeList.add(anime);
			}
			results.close();
			statement.close();
			connect.close();
			return animeList;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Saves an anime to the database
	public boolean saveAnime(Anime anime) {
		if (anime.getAnimeName() != null) {
			String anime_alt_name = "NULL";
			String anime_traits = "NULL";
			if (anime.getAnimeAltName() != null) {
				anime_alt_name = "'" + anime.getAnimeAltName() + "'";
			}
			if (!anime.getAnimeTraits().isEmpty()) {
				anime_traits = "'" + new LinkedList<String>(anime.getAnimeTraits().keySet()).element() + "'";
			}
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/myanimedb", "root",
						"root");
				Statement statement = connect.createStatement();
				statement.executeUpdate(
						"INSERT INTO test_anime (anime_name, anime_alt_name, anime_score, favorite, anime_traits) VALUES ('"
								+ anime.getAnimeName() + "', " + anime_alt_name + ", " + anime.getAnimeScore() + ", "
								+ anime.isFavorite() + ", " + anime_traits + ");");
				statement.close();
				connect.close();
				return true;
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("\n\tFailed to save entry,\n\tAnime name must not be NULL.");
		}
		return false;
	}

	// Deletes the anime with the given ID
	public boolean deleteAnime(int animeId) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/myanimedb", "root", "root");
			Statement statement = connect.createStatement();
			statement.executeUpdate("DELETE FROM test_anime WHERE anime_id = " + animeId + ";");
			statement.close();
			connect.close();
			return true;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("\n\tFailed to delete #" + animeId);
		return false;
	}

	// Deletes the anime with the given title
	public boolean deleteAnime(String animeName) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/myanimedb", "root", "root");
			Statement statement = connect.createStatement();
			statement.executeUpdate("DELETE FROM test_anime WHERE anime_name = '" + animeName + "';");
			statement.close();
			connect.close();
			return true;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("\n\tFailed to delete " + animeName);
		return false;
	}
}
