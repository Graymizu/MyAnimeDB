package com.myanimedb.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myanimedb.model.Anime;
import com.myanimedb.servicelogic.AnimeService;

@WebServlet("/DisplayAnime")
public class DisplayAnimeServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Anime ab = new AnimeService().deepFetchAnime("Angel Beats!");

		PrintWriter printWriter = resp.getWriter();
		printWriter.println("<!DOCTYPE html>");
		printWriter.println("<html>");
		printWriter.println("<head>");
		printWriter.println("<title>Display Anime</title>");
		printWriter.println("</head>");
		printWriter.println("<body>");
		printWriter.println("<h1>Hardcoded Example: </h1>");
		printWriter.println("<h4>" + ab.toString() + "</h4></br>");
		printWriter.println("<h4>ID: " + ab.getAnimeId() + "</h4></br>");
		printWriter.println("<h4>Title: " + ab.getAnimeName() + "</h4></br>");
		printWriter.println("<h4>Alternate Title: " + ab.getAnimeScore() + "</h4></br>");
		printWriter.println("<h4>A Favorite: " + ab.isFavorite() + "</h4></br>");
		printWriter.println("<h4>Traits: " + ab.getAnimeTraits().toString() + "</h4></br>");

		printWriter.println("</body>");
		printWriter.println("</html>");
	}

}
