package com.myanimedb;

import com.myanimedb.model.WatchHistory;
import com.myanimedb.servicelogic.AnimeService;

public class MyAnimeDBDriver {

	public static void main(String[] args) {

//		System.out.println(new Anime().getAnimeAltName());

//		System.out.println(new AnimeDAL().fetchAnime("Kono Suba"));

//		System.out.println(new AnimeDAL().fetchAllAnime("Shonen"));
//		System.out.println(new AnimeDAL().saveAnime(new Anime("Seven Deadly Sins")));
//		System.out.println(new AnimeDAL().fetchAllAnime("Shonen"));
//		System.out.println(new AnimeDAL().deleteAnime("Seven Deadly Sins"));
//		System.out.println(new AnimeDAL().fetchAllAnime());
//		System.out.println(new AnimeService().saveAnime(new Anime("Akame ga Kill")));
//		System.out.println(new AnimeDAL().deleteAnime("Kono Suba"));
		AnimeService.quickAccess(7).setWatchStatus(WatchHistory.HAVE_WATCHED);
		System.out.println(AnimeService.quickAccess(7));
		System.out.println(AnimeService.quickAccessAll());
		System.out.println(AnimeService.quickAccessAll("Shonen"));

	}

}
