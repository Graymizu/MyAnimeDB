package com.myanimedb.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myanimedb.servicelogic.AnimeService;

@WebServlet("/DeleteAnime")
public class DeleteAnimeServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String animeName = request.getParameter("animeName");
		AnimeService saving = new AnimeService();
		boolean saved = saving.deleteAnime(animeName);
		new AnimeService().deepFetchAllAnime();

	}

}
