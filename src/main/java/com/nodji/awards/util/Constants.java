package com.nodji.awards.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Constants {

	public static final String SQL_FIND_BY_MOVIE_WINNER_ORDER_PRODUCER = "select mp from MovieProducer as mp join mp.movie as movie join mp.producer as producer where movie.winner = true order by producer.id, movie.yearm";
	public static final String SQL_FIND_YEAR_MODE_ONE_WINNER = "select new com.nodji.awards.dto.YearWinnerMovieDTO(movie.yearm, count(movie.winner)) from Movie as movie where movie.winner=true group by movie.yearm having count(movie.winner) > 1";
	public static final String SQL_FIND_BY_WINNERS = "select new com.nodji.awards.dto.StudioWinDTO(studio.name, count(movie.winner)) from MovieStudio as ms join ms.movie as movie join ms.studio as studio where movie.winner=true group by studio.name order by 2 desc";

}
