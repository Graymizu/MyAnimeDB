package com.myanimedb.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myanimedb.model.Anime;
import com.myanimedb.servicelogic.AnimeService;

@WebServlet("/CreateAnime")
public class AddAnimeServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String animeName = request.getParameter("animeName");
		String animeAltName = request.getParameter("animeAltName");
		double animeScore = Double.parseDouble(request.getParameter("animeScore"));
		boolean favorite = Boolean.parseBoolean(request.getParameter("favorite"));
		String animeTraits = request.getParameter("animeTraits");

//		System.out.println(animeName);
//		System.out.println(animeAltName);
//		System.out.println(animeScore);
//		System.out.println(favorite);
//		System.out.println(animeTraits);

		Anime newAnime = new Anime(animeName, animeAltName, animeScore, favorite);
		System.out.println(newAnime.toString());
		newAnime.setAnimeName(animeName);
		newAnime.setAnimeAltName(animeAltName);
		newAnime.setAnimeScore(animeScore);
		newAnime.setFavorite(favorite);
		newAnime.setAnimeTraits(animeTraits);

		AnimeService saving = new AnimeService();

		boolean saved = saving.saveAnime(newAnime);
		if (saved) {
			System.out.println("Success!");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("success.html");
			requestDispatcher.forward(request, response);
		} else {
			System.out.println("Failed!");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("fail.html");
			requestDispatcher.forward(request, response);
		}
	}

}
