package library.controller;

import library.model.User;
import library.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("users")
public class UserController {
    @Autowired
    private IUserService userService;
    @GetMapping("")
    public ModelAndView showLoginUser() {
        ModelAndView modelAndView = new ModelAndView("user/login");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("login")
    public ModelAndView login(@Validated @ModelAttribute("user") User user, BindingResult bindingResult) {
         new User().validate(user,bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return new ModelAndView("user/login");
        }
        return new ModelAndView("redirect:/customers");
    }
@GetMapping("/{id}/list")
    public ModelAndView showListUser(){
        Iterable<User>users = userService.findAll();
        ModelAndView modelAndView = new ModelAndView("user/list");
        modelAndView.addObject("user",users);
        return modelAndView;
}
@PostMapping("/create")
    public ModelAndView showCreateUser(){
ModelAndView modelAndView = new ModelAndView("user/create");
modelAndView.addObject("user",new User());
return modelAndView;
}
}
