package app.mvc.controller;

import app.mvc.dto.UserDTO;
import app.mvc.model.enums.RoleType;
import app.mvc.service.AuthenticationService;
import app.mvc.service.UserService;
import app.mvc.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    private User loggedUser;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Hi there! Please, log in if you want to access our page");
        return "index";
    }

    @RequestMapping(value = "/welcome")
    public String submit() {
        loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        if (loggedUser != null) {
            if (loggedUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleType.ROLE_ADMIN.getValue()))) {
                return "redirect:/allusers";
            } else if (loggedUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleType.ROLE_USER.getValue()))) {
                return "redirect:/personal";
            }
        }
        return "redirect:/error";
    }

    @RequestMapping(value = "/allusers", method = RequestMethod.GET)
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("title", "Admin Panel");
        model.addAttribute("message", "Here are all our users:");
        return "adminCab";
    }

    @RequestMapping(value = "/personal", method = RequestMethod.GET)
    public String showPersonalData(Model model) {
        List<UserDTO> listOfUsers = new ArrayList<>();
        listOfUsers.add(AppUtils.userConvert(userService.getUserByUserName(loggedUser.getUsername())));

        model.addAttribute("users", listOfUsers);
        model.addAttribute("title", "Personal Cabinet");
        model.addAttribute("message", "Personal data:");
        return "personalCab";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String errorConnection(ModelMap model) {
        model.addAttribute("errorMessage", "Invalid Details");
        return "error";
    }
}