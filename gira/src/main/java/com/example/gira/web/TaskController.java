package com.example.gira.web;

import com.example.gira.domain.beans.LoggedUser;
import com.example.gira.domain.dtos.AddTaskDTO;
import com.example.gira.domain.enums.ClassificationName;
import com.example.gira.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        return "add-task";
    }

    @PostMapping("/task/add")
    public String postTask(@Valid @ModelAttribute(name = "addTaskDTO")AddTaskDTO addTaskDTO,
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

    @ModelAttribute(name = "classificationNames")
    public ClassificationName[] classificationNames() {
        return ClassificationName.values();
    }
}
