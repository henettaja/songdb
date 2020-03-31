package hh.swd20.harjtyo.musicrepo.webcontroller;

import hh.swd20.harjtyo.musicrepo.domain.SongRepository;
import hh.swd20.harjtyo.musicrepo.domain.SubgenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SongController {

    @Autowired
    private SongRepository songRepository;

    //Routing endpoint /songlist to songlist.html thymeleaf template in /resources/templates/
    @RequestMapping(value = "/songlist", method = RequestMethod.GET) //Using GET method
    public String showBooks (Model model) {

        model.addAttribute("songList", songRepository.findAll()); //saving all books to Model for use in thymeleaf

        return "songlist"; //songlist.html
    }

}
