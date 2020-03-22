package app.mvc.controller;

import app.mvc.dto.UserRegistDTO;
import app.mvc.jms.Sender;
import app.mvc.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private Sender sender;

    @RequestMapping(value = "/regist", method = RequestMethod.GET)
    public String getRegistPage(Model model) {
        model.addAttribute("message", "Register page");
        return "register";
    }


    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public String registNewUser(@ModelAttribute("userRegistDTO") UserRegistDTO userRegistDTO) {
        registerService.addRegisterUser(userRegistDTO);
        sender.send("inbound.queue", "A new user registered. Screenname: " + userRegistDTO.getUsername());
        return "index";
    }
}
