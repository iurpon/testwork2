package home.webapplication.controller;

import home.webapplication.domain.User;
import home.webapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;


@Controller
public class PagingController {

    @Autowired
    private UserService userService;

    private int currentPage = 0;

    @RequestMapping(value="/list")
     public String listOfUsers(@RequestParam(required = false) Integer page,Model model) throws SQLException {

        if(page != null)
            currentPage = page;
        List<User> users = userService.getAllUsers();
        PagedListHolder<User> pagedListHolder = new PagedListHolder<>(users);
        pagedListHolder.setPageSize(5);
        model.addAttribute("maxPages",pagedListHolder.getPageCount());
        if(page==null || page < 1 || page > pagedListHolder.getPageCount())page=1;

        model.addAttribute("page",page);
        if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
            pagedListHolder.setPage(0);
            model.addAttribute("users",pagedListHolder.getPageList());
        }
        else if(page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page-1);
            model.addAttribute("users",pagedListHolder.getPageList());
        }
        return "list-of-users";
    }

    @RequestMapping("/delete")
    public String deleteUser(@RequestParam("id") String id, Model model)  throws SQLException {
        model.addAttribute("user",userService.deleteUser(id));
        return "forward:/list?page="+currentPage;
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.GET)
    public String addUser(@ModelAttribute("newUser") User newUser)   throws SQLException {
        return "adduser";
    }
    @RequestMapping(value = "/adduser",method = RequestMethod.POST)
    public String showNew(@ModelAttribute("newUser") User newUser)  throws SQLException {
        userService.addUser(newUser);
        return "redirect:/list?page="+currentPage;
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateUser(@RequestParam("id") String id,Model model)   throws SQLException {
        User user = userService.getUserById(id);
        model.addAttribute("user",user);
        return "update";
    }
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String showUpdate(@ModelAttribute("newUser") User newUser)   throws SQLException {
        userService.updateUser(newUser);

        return "redirect:/list?page="+currentPage;

    }



    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@ModelAttribute("search") User newUser)   throws SQLException {

        return "search";
    }
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String showSearch(Model model,@ModelAttribute("searchUser") User searchUser)   throws SQLException {
        model.addAttribute("user",userService.search(searchUser));
        return "searchResult";
    }
}

