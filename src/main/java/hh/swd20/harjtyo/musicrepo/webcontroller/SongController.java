package hh.swd20.harjtyo.musicrepo.webcontroller;

import hh.swd20.harjtyo.musicrepo.domain.GenreRepository;
import hh.swd20.harjtyo.musicrepo.domain.Song;
import hh.swd20.harjtyo.musicrepo.domain.SongRepository;
import hh.swd20.harjtyo.musicrepo.domain.SubgenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SongController {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SubgenreRepository subgenreRepository;

    @Autowired
    private GenreRepository genreRepository;

    //Routing endpoint /songlist to songlist.html thymeleaf template in /resources/templates/
    //The endpoint basically calls this method
    @RequestMapping(value = "/songlist", method = RequestMethod.GET) //Using GET method
    public String showSongs (Model model) {

        model.addAttribute("songList", songRepository.findAll()); //saving all books to Model for use in thymeleaf

        return "songlist"; //songlist.html
    }

    //Routing endpoint /addsong to addsong.html thymeleaf template in /resources/templates/
    //The endpoint basically calls this method
    @RequestMapping(value = "/addsong", method = RequestMethod.GET) //Using GET method
    public String addSong (Model model) {

        model.addAttribute("song", new Song());
        model.addAttribute("subgenres", subgenreRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());

        return "addsong"; //addsong.html
    }

    //Endpoing /save saves the song to the database and redirects to /booklist endpoint
    //The endpoint basically calls this method
    @RequestMapping(value = "/savesong", method = RequestMethod.POST) //Using POST method
    public String saveSong (Song song) {

        songRepository.save(song);

        return "redirect:/songlist"; //Redirects to /songlist endpoint and calls the songList method
    }

    //The endpoint basically calls this method
    @RequestMapping(value = "/deletesong/{id}", method = RequestMethod.GET)
    public String deleteGenre (@PathVariable("id") Long songId) {

        songRepository.deleteById(songId);

        return "redirect:../songlist"; //Redirects to /songlist endpoint and calls the songList method
    }

    //The endpoint basically calls this method
    @RequestMapping(value = "/editsong/{id}", method = RequestMethod.GET)
    public String editsong (@PathVariable ("id") Long songId, Model model) {

        model.addAttribute("song", songRepository.findById(songId));
        model.addAttribute("subgenres", subgenreRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());

        return "editsong"; //editsong.html
    }

    //The endpoint basically calls this method
    @RequestMapping(value = "/updatesong/{id}", method = RequestMethod.POST)
    public String updatesong (@PathVariable("id") Long songId, Song song) {

        songRepository.save(song);

        return "redirect:../songlist"; //Redirects to /songlist endpoint and calls the songList method
    }

}
