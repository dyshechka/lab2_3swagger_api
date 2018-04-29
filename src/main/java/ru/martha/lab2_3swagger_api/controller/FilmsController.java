package ru.martha.lab2_3swagger_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.martha.lab2_3swagger_api.api.FilmsApi;
import ru.martha.lab2_3swagger_api.model.Film;
import ru.martha.lab2_3swagger_api.service.FilmsService;

import java.util.List;

@RestController
public class FilmsController implements FilmsApi {

    private final FilmsService filmsService;

    @Autowired
    public FilmsController(FilmsService filmsService) {
        this.filmsService = filmsService;
    }

    @Override
    public ResponseEntity<Film> filmsCreatePost(@RequestBody Film films) {
        return ResponseEntity.ok(filmsService.createFilm(films));
    }

    @Override
    public ResponseEntity<List<Film>> filmsGet() {
        return ResponseEntity.ok(filmsService.gelAllFilms());
    }

    @Override
    public ResponseEntity<Film> filmsIdEditPatch(@PathVariable("id") Integer id, @RequestBody Film film) {
        return ResponseEntity.ok(filmsService.editFilm(id, film));
    }

    @Override
    public ResponseEntity<Film> filmsIdGet(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(filmsService.getFilmById(id));
    }

    @Override
    public ResponseEntity<Void> filmsIdRemoveDelete(@PathVariable("id") Integer id) {
        filmsService.removeFilm(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
