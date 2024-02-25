package com.nodji.awards.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nodji.awards.model.MovieStudio;
import com.nodji.awards.model.MovieStudioId;

public interface MovieStudioRepository extends JpaRepository<MovieStudio, MovieStudioId> {

}
