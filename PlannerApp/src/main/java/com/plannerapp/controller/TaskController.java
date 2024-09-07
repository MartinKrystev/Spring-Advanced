package com.plannerapp.controller;

import com.plannerapp.model.beans.LoggedUser;
import com.plannerapp.model.dtos.AddTaskDTO;
import com.plannerapp.model.enums.PriorityName;
import com.plannerapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class TaskController {
    private final LoggedUser loggedUser;
    private final TaskService taskService;

    @Autowired
    public TaskController(LoggedUser loggedUser, TaskService taskService) {
        this.loggedUser = loggedUser;
        this.taskService = taskService;
    }

    @GetMapping("/task/add")
    public String getTask() {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/login";
        }

        return "task-add";
    }

    @PostMapping("/task/add")
    public String postTask(@Valid @ModelAttribute(name = "addTaskDTO") AddTaskDTO addTaskDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors() || !this.taskService.createTask(addTaskDTO)) {
            redirectAttributes.addFlashAttribute("addTaskDTO", addTaskDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addTaskDTO", bindingResult);

            return "redirect:/task/add";
        }

        return "redirect:/home";
    }

    //Model Attributes
    @ModelAttribute(name = "addTaskDTO")
    public AddTaskDTO initAddTaskDTO() {
        return new AddTaskDTO();
    }

    @ModelAttribute(name = "priorityNames")
    public PriorityName[] priorityNames() {
        return PriorityName.values();
    }
}
