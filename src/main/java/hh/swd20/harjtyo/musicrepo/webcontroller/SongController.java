package hh.swd20.harjtyo.musicrepo.webcontroller;

import hh.swd20.harjtyo.musicrepo.domain.GenreRepository;
import hh.swd20.harjtyo.musicrepo.domain.Song;
import hh.swd20.harjtyo.musicrepo.domain.SongRepository;
import hh.swd20.harjtyo.musicrepo.domain.SubgenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class SongController {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SubgenreRepository subgenreRepository;

    @Autowired
    private GenreRepository genreRepository;

    @RequestMapping(value = "/index", method = RequestMethod.GET) //Using GET method
    public String index () {

        return "index"; //songlist.html
    }

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
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public String addSong (Model model) {

        model.addAttribute("song", new Song());
        model.addAttribute("subgenres", subgenreRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());

        return "/user/addsong"; //addsong.html
    }

    //Endpoing /save saves the song to the database and redirects to /booklist endpoint
    //The endpoint basically calls this method
    @RequestMapping(value = "/savesong", method = RequestMethod.POST) //Using POST method
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public String saveSong (@Valid Song song, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("genres",genreRepository.findAll());
            model.addAttribute("subgenres", subgenreRepository.findAll());
            return "/user/addsong";
        }
        songRepository.save(song);

        return "redirect:/songlist"; //Redirects to /songlist endpoint and calls the songList method
    }

    //The endpoint basically calls this method
    @RequestMapping(value = "/deletesong/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteSong (@PathVariable("id") Long songId) {

        songRepository.deleteById(songId);

        return "redirect:/songlist"; //Redirects to /songlist endpoint and calls the songList method
    }

    //The endpoint basically calls this method
    @RequestMapping(value = "/editsong/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editsong (@PathVariable ("id") Long songId, Model model) {

        model.addAttribute("song", songRepository.findById(songId));
        model.addAttribute("subgenres", subgenreRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());

        return "/admin/editsong"; //editsong.html
    }

    //The endpoint basically calls this method
    @RequestMapping(value = "/updatesong", method = RequestMethod.POST) //Using POST method
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateSong (@Valid Song song, BindingResult bindingResult, RedirectAttributes attributes, Model model) {

        attributes.addAttribute("songId", song.getSongId());

        if (bindingResult.hasErrors()) {
            model.addAttribute("genres",genreRepository.findAll());
            model.addAttribute("subgenres", subgenreRepository.findAll());
            return "/admin/editsong";
        }
        songRepository.save(song);

        return "redirect:/songlist"; //Redirects to /songlist endpoint and calls the songList method
    }

}
