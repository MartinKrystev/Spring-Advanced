package com.example.gira.web;

import com.example.gira.domain.beans.LoggedUser;
import com.example.gira.domain.entities.TaskEntity;
import com.example.gira.services.TaskService;
import com.example.gira.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    private final LoggedUser loggedUser;
    private final UserService userService;
    private final TaskService taskService;

    @Autowired
    public HomeController(LoggedUser loggedUser, UserService userService, TaskService taskService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/home")
    public String getHome(Model model) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        List<TaskEntity> allTasks = this.taskService.findAll();

        model.addAttribute("allTasks", allTasks);

        return "home";
    }

    @GetMapping("/home/progress{id}")
    public String taskProgress(@PathVariable Long id) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        this.taskService.updateProgress(id);
        return "redirect:/home";
    }


    @GetMapping("/home/delete{id}")
    public String deleteTask(@PathVariable Long id) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        this.taskService.deleteTask(id);
        return "redirect:/home";
    }
}
