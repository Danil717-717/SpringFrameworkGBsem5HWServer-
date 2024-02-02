package ru.springgb.sem5HW.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.springgb.sem5HW.Task;
import ru.springgb.sem5HW.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService service;

    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public List<Task> getAllTask(){
        return service.getAllTasks();
    }

    @GetMapping("/{status}")
    public List<Task> getTaskById(@PathVariable Task.Status status) {
        return service.getTaskStatus(status);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return service.createTask(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return service.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        service.deleteById(id);
    }

}
