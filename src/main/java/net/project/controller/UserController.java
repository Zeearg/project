package net.project.controller;


import net.project.model.User;
import net.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public ModelAndView listUsers(@RequestParam(required = false)Integer page) {
        List<User> listUsers = userService.listUsers();
        PagedListHolder<User> pagedListHolder = new PagedListHolder<User>(listUsers);
        pagedListHolder.setPageSize(10);
        ModelAndView model = new ModelAndView("listUsers");
        model.addObject("maxPages", pagedListHolder.getPageCount());
        if (page == null || page < 1 || page > pagedListHolder.getPageCount())
            page = 1;
        model.addObject("page", page);
        pagedListHolder.setPage(page - 1);
        model.addObject("listUsers", pagedListHolder.getPageList());
        return model;
    }

    @RequestMapping(value = "/remove")
    public ModelAndView removeUser(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        userService.delete(id);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/edit")
    public ModelAndView editUser(HttpServletRequest request){
        ModelAndView model = new ModelAndView("userForm");
        User user;
        if (request.getParameter("id") == null){
            user = new User();
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            user = userService.findByID(id);
        }
        model.addObject("user", user);
        return model;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView updateUser(@ModelAttribute User user) throws IOException {
        userService.update(user);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView searchUsers(@RequestParam (defaultValue = "") String search){
        List<User> listUsers = userService.searchUsers(search);
        int page = 1;
        ModelAndView model = new ModelAndView("listUsers");
        model.addObject("maxPages", 1);
        model.addObject("page", page);
        model.addObject("listUsers", listUsers);
        return model;
    }
}
