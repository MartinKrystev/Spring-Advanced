package com.plannerapp.controller;

import com.plannerapp.model.beans.LoggedUser;
import com.plannerapp.model.entity.TaskEntity;
import com.plannerapp.model.entity.UserEntity;
import com.plannerapp.service.TaskService;
import com.plannerapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

        List<TaskEntity> allTasks = this.taskService.findAllByUserIsNull();
        List<TaskEntity> allById = this.taskService.findAllByUserId(loggedUser.getId());

        model.addAttribute("allTasks", allTasks);
        model.addAttribute("allById", allById);

        return "home";
    }

    @GetMapping("/home/assign{id}")
    public String taskAssign(@PathVariable Long id) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        this.taskService.assignTask(id);
        return "redirect:/home";
    }
    @GetMapping("/home/return{id}")
    public String taskReturn(@PathVariable Long id) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        this.taskService.returnTask(id);
        return "redirect:/home";
    }

    @GetMapping("/home/remove{id}")
    public String taskRemove(@PathVariable Long id) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        this.taskService.removeTask(id);
        return "redirect:/home";
    }
}
