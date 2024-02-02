package ru.springgb.sem5HW.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.springgb.sem5HW.Task;
import ru.springgb.sem5HW.service.TaskService;


@Controller
@RequestMapping("/tasks")
public class TaskViewController {
    private final TaskService taskService;

    @Autowired
    public TaskViewController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "tasks";
    }

    @GetMapping("/new")
    public String newTask(Model model) {
        model.addAttribute("task", new Task());
        return "new";
    }

    @PostMapping
    public String create(@ModelAttribute("task") @Valid Task task, BindingResult result) {
        if (result.hasErrors()) {
            return "new";
        }
        taskService.createTask(task);
        return "redirect:tasks";
    }

    @GetMapping("/{id}")
    public String getTask(@PathVariable Long id, Model model) {
        model.addAttribute("task", taskService.getTaskById(id));
        return "taskProfile";
    }


    @RequestMapping(path = "/update/{id}", method = RequestMethod.POST)
    private String updateTask(@PathVariable("id") Long id, @ModelAttribute Task task) {
        task.setId(id);
        taskService.apdateTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    private String deleteTask(@PathVariable("id") Long id) {
        taskService.deleteById(id);
        return "redirect:/tasks";
    }

}
