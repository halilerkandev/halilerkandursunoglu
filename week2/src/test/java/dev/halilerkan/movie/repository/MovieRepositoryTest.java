package dev.halilerkan.movie.repository;

import dev.halilerkan.movie.entity.*;
import dev.halilerkan.movie.entity.enumeration.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Test
    void saveMovie() {

        MemberEntity memberEntity = MemberEntity
                .builder()
                .memberId(1L)
                .firstName("Erkan")
                .lastName("Dursunoğlu")
                .build();

        memberRepository.save(memberEntity);

        ActorEntity actorEntity = ActorEntity
                .builder()
                .actorId(1L)
                .firstName("qweqwe")
                .lastName("kvfdf")
                .build();

        //actorRepository.save(actorEntity);

        DirectorEntity directorEntity = DirectorEntity
                .builder()
                .directorId(1L)
                .firstName("sdffg")
                .lastName("df")
                .build();

        //directorRepository.save(directorEntity);

        MovieEntity movieEntity = MovieEntity
                .builder()
                .movieId(1L)
                .name("asd")
                .cast(List.of(
                        actorEntity
                ))
                .director(directorEntity)
                .genre(Genre.COMEDY)
                .releaseYear(2021)
                .build();

        movieRepository.save(movieEntity);

        RatingEntity ratingEntity = RatingEntity
                .builder()
                .ratingId(1L)
                .point(5)
                .member(memberEntity)
                .movie(movieEntity)
                .build();

        ratingRepository.save(ratingEntity);

        List<RatingEntity> ratingEntityList = ratingRepository.findAll();

        System.out.println("ratingEntityList" + ratingEntityList);

    }

}