package hh.swd20.harjtyo.musicrepo.webcontroller;

import hh.swd20.harjtyo.musicrepo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestServiceController {

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    SubgenreRepository subgenreRepository;

    @Autowired
    SongRepository songRepository;

    @RequestMapping(value = "/songs", method = RequestMethod.GET)
    public @ResponseBody List<Song> songRest() {
        return (List<Song>) songRepository.findAll();
    }

    @RequestMapping(value = "/genres", method = RequestMethod.GET)
    public @ResponseBody List<Genre> genreRest() {
        return (List<Genre>) genreRepository.findAll();
    }

    @RequestMapping(value = "/subgenres", method = RequestMethod.GET)
    public @ResponseBody List<Subgenre> subgenreRest() {
        return (List<Subgenre>) subgenreRepository.findAll();
    }

}
