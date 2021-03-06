package hh.swd20.harjtyo.musicrepo.webcontroller;

import hh.swd20.harjtyo.musicrepo.domain.Genre;
import hh.swd20.harjtyo.musicrepo.domain.GenreRepository;
import hh.swd20.harjtyo.musicrepo.domain.Subgenre;
import hh.swd20.harjtyo.musicrepo.domain.SubgenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class GenreController {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private SubgenreRepository subgenreRepository;

    //Routing endpoint /genrelist to genrelist.html thymeleaf template in /resources/templates/
    //The endpoint basically calls this method
    @RequestMapping(value = "/genrelist", method = RequestMethod.GET)
    public String genreList (Model genreModel, Model subgenreModel) {

        genreModel.addAttribute("genreList", genreRepository.findAll()); //for use in thymeleaf
        subgenreModel.addAttribute("subgenreList", subgenreRepository.findAll()); //for use in thymeleaf

        return "genrelist"; //genrelist.html

    }

    //The endpoint basically calls this method
    @RequestMapping(value = "/addgenre", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
        public String addGenre (Model model) {

        model.addAttribute("genre", new Genre());

        return "user/addgenre"; //addgenre.html

    }

    //The endpoint basically calls this method
    @RequestMapping(value = "/savegenre", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public String saveGenre (@Valid Genre genre, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            System.out.println(bindingResult);

            return "user/addgenre"; //Redirects back /addgenre endpoint and calls the addGenre method if form contains errors
        }
        genreRepository.save(genre);

        return "redirect:genrelist"; //Redirects to /genrelist endpoint and calls the genreList method

    }

    //The endpoint basically calls this method
    @RequestMapping(value = "/addsubgenre", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public String addSubgenre (Model model) {

        model.addAttribute("subgenre", new Subgenre());
        model.addAttribute("genres",genreRepository.findAll());

        return "user/addsubgenre"; //addgenre.html

    }

    //The endpoint basically calls this method
    @RequestMapping(value = "/savesubgenre", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public String saveSubgenre (@Valid Subgenre subgenre, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            System.out.println(bindingResult);

            return "user/addsubgenre"; //Redirects back /addgenre endpoint and calls the addGenre method if form contains errors
        }
        subgenreRepository.save(subgenre);

        return "redirect:/genrelist"; //Redirects to /genrelist endpoint and calls the genreList method

    }

    //The endpoint basically calls this method
    @RequestMapping(value = "/deletegenre/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteGenre (@PathVariable("id") Long genreId) {

        genreRepository.deleteById(genreId);

        return "redirect:/genrelist";
    }

    //The endpoint basically calls this method
    @RequestMapping(value = "/deletesubgenre/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public String deleteSubgenre (@PathVariable("id") Long subgenreId) {

        subgenreRepository.deleteById(subgenreId);

        return "redirect:/genrelist";
    }

}
