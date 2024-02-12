package ru.springgb.sem5HW.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.springgb.sem5HW.model.Task;
import ru.springgb.sem5HW.service.TaskService;

import java.util.List;



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
        return findPaginated(1,"id","asc", "keyword", model);

    }


    @GetMapping("/new")
    public String newTask(Model model) {
        model.addAttribute("task", new Task());
        return "new";
    }

    @PostMapping("/save")
    public String create(@ModelAttribute("task") @Valid Task task, BindingResult result) {
        if (result.hasErrors()) {
            return "new";
        }
        taskService.createTask(task);
        return "redirect:/tasks";
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

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                String keyword,
                                Model model) {
        int pageSize = 5;

        Page<Task> page = taskService.findPaginated(pageNo, pageSize, sortField, sortDir);

        if(keyword!=null) {
            List<Task> list = taskService.getByKeyword(keyword);
            model.addAttribute("list", list);
        }else {
            List<Task> list = taskService.getAllTasks();
            model.addAttribute("list", list);
        }

        List<Task> listTasks = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listTasks", listTasks);
        return "tasks";
    }


    @GetMapping({"/search"})
    public String taskSearch(Task task, Model model, String keyword) {
        if(keyword!=null) {
            taskService.getByKeyword(keyword);
            model.addAttribute("task", task);

        }else {
            List<Task> list = taskService.getAllTasks();
            model.addAttribute("list", list);
        }
        return "redirect:/tasks";
    }



}
