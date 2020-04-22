package hh.swd20.harjtyo.musicrepo.webcontroller;

import hh.swd20.harjtyo.musicrepo.domain.Session;
import hh.swd20.harjtyo.musicrepo.domain.SessionRepository;
import hh.swd20.harjtyo.musicrepo.domain.SignUpForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class SessionController {

    @Autowired
    private SessionRepository sessionRepository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "index";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        return "index";
    }

    @RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
    public String accessdenied() {
        return "accessdenied";
    }

    @RequestMapping(value = "/sessionlist", method = RequestMethod.GET) //Using GET method
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showSessions(Model model) {

        model.addAttribute("sessionlist", sessionRepository.findAll()); //saving all sessions to Model for use in thymeleaf

        return "admin/sessionlist"; //sessionlist.html
    }

    @RequestMapping(value = "/deletesession/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteSession(@PathVariable("id") Long sessionId) {

        sessionRepository.deleteById(sessionId);

        return "redirect:/sessionlist"; //Redirects to /sessionlist endpoint and calls the showSessions method
    }

    @RequestMapping(value = "signup")
    public String addSession(Model model) {
        model.addAttribute("signupform", new SignUpForm());
        return "signup";
    }

    /**
     * Create new user
     * Check if user already exists & form validation
     *
     * @param signupForm
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("signupform") SignUpForm signupForm, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) { // validation errors
            if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match
                String pwd = signupForm.getPassword();
                BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
                String hashPwd = bc.encode(pwd);

                Session newSession = new Session();
                newSession.setPasswordHash(hashPwd);
                newSession.setUsername(signupForm.getUsername());
                newSession.setRole("USER");

                if (sessionRepository.findByUsername(signupForm.getUsername()) == null) { // Check if user exists
                    sessionRepository.save(newSession);
                } else {
                    bindingResult.rejectValue("username", "err.username", "Username already exists");
                    return "signup";
                }
                } else {
                    bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords do not match");
                return "signup";
                }
                } else {
                return "signup";
                }
        return "redirect:/index";
            }

}
