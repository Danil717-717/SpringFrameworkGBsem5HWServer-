package ru.springgb.sem5HW.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.springgb.sem5HW.Task;
import ru.springgb.sem5HW.service.TaskService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


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
        //return findPaginated(1, model);

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

    @PostMapping("/update/{id}")
    private String updateTaskValid(@PathVariable("id") Long id, @ModelAttribute @Valid Task task, BindingResult result) {
        if (result.hasErrors()) {
            return "updateTask";
        }
        task.setId(id);
        taskService.apdateTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/updateTask/{id}")
    public String updateTask(@PathVariable(value = "id") Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "updateTask";
    }


    @GetMapping("/delete/{id}")
    private String deleteTask(@PathVariable("id") Long id) {
        taskService.deleteById(id);
        return "redirect:/tasks";
    }

    @GetMapping({"/searchTask","/searchTask{status}"})
    public String searchTaskByTitle(@ModelAttribute("status") @RequestParam("status") Optional<String> status,
                                    Model model,Task task){
        System.out.println(status);

        if(status.isPresent()) {
            List<Task> taskList = taskService.getTaskStatus(status.get());
            model.addAttribute("task",task);
            model.addAttribute("task2", taskList);
            return "tasks";
        }
        else
        {
            return "task/searchTask";}
    }



}
