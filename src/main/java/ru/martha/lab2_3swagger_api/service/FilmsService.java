package ru.martha.lab2_3swagger_api.service;

import org.springframework.stereotype.Service;
import ru.martha.lab2_3swagger_api.exception.FilmNotFoundException;
import ru.martha.lab2_3swagger_api.exception.FilmValidationException;
import ru.martha.lab2_3swagger_api.model.Film;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.springframework.util.StringUtils.isEmpty;

@Service
public class FilmsService {

    private final List<Film> films;
    private Integer idCounter = 0;

    public FilmsService() {
        films = new ArrayList<>();
        films.add(createFilm(1, "Тебя здесь никогда не было", "18+", 95, "Великобритания", 7.5));
        films.add(createFilm(2, "Три билборда на границе Эббинга, Миссури", "18+", 115, "США", 8.2));
        films.add(createFilm(3, "Матрица", "16+", 136, "США", 8.7));
        films.add(createFilm(4, "Властелин колец: Братство кольца", "12+", 178, "Новая Зеландия", 8.8));
        films.add(createFilm(5, "Бегущий по лезвию 2049", "18+", 164, "Канада", 8.2));
        for (Film film : films) {
            if (film.getId() > idCounter) {
                idCounter = film.getId() + 1;
            }
        }
    }

    public List<Film> gelAllFilms() {
        return films;
    }

    public Film getFilmById(Integer id) {
        for (Film film : films) {
            if (id.equals(film.getId())) {
                return film;
            }
        }
        throw new FilmNotFoundException();
    }

    public Film createFilm(Film film) {
        films.add(film);
        film.setId(idCounter);
        idCounter++;
        return film;
    }

    public void removeFilm(Integer id) {
        Iterator<Film> iterator = films.iterator();
        //noinspection Java8CollectionRemoveIf
        while (iterator.hasNext()) {
            Film film = iterator.next();
            if (id.equals(film.getId())) {
                iterator.remove();
                return;
            }
        }
        throw new FilmNotFoundException();
    }

    public Film editFilm(Integer id, Film newFilm) {
        Film film = getFilmById(id);
        if (film == null) {
            throw new FilmNotFoundException();
        }
        if (!isEmpty(newFilm.getName())) {
            film.setName(newFilm.getName());
        }
        if (!isEmpty(newFilm.getAgeRestriction())) {
            film.setAgeRestriction(newFilm.getAgeRestriction());
        }
        if (newFilm.getDuration() != null) {
            if (newFilm.getDuration() <= 0) {
                throw new FilmValidationException("Invalid film duration");
            }
            film.setDuration(newFilm.getDuration());
        }
        if (!isEmpty(newFilm.getCountry())) {
            film.setCountry(newFilm.getCountry());
        }
        if (newFilm.getImdb() != null) {
            if (newFilm.getImdb() <= 0 || newFilm.getImdb() > 10) {
                throw new FilmValidationException("Invalid film imdb");
            }
            film.setImdb(newFilm.getImdb());
        }
        return film;
    }

    private Film createFilm(Integer id, String name, String ageRestriction, Integer duration, String country, Double imdb) {
        Film film = new Film();
        film.setId(id);
        film.setName(name);
        film.setAgeRestriction(ageRestriction);
        film.setDuration(duration);
        film.setCountry(country);
        film.setImdb(imdb);
        return film;
    }
}
