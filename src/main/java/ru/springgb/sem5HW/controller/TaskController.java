package ru.springgb.sem5HW.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.springgb.sem5HW.dto.APIResponse;
import ru.springgb.sem5HW.model.Task;
import ru.springgb.sem5HW.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService service;

    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }

//    @GetMapping
//    public List<Task> getAllTask(){
//        return service.getAllTasks();
//    }

    @GetMapping("/{status}")
    public List<Task> getTaskById(@PathVariable Task.Status status) {
        return  null;//service.getTaskStatus(status);
    }

//    @GetMapping("/{field}")
//    private APIRespon

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



    ////////////////

    @GetMapping
    private APIResponse<List<Task>> getTasks() {
        List<Task> allTask = service.getAllTasks();
        return new APIResponse<>(allTask.size(), allTask);
    }

    @GetMapping("/{field}")
    private APIResponse<List<Task>> getTasksWithSort(@PathVariable String field) {
        List<Task> allTasks = service.findTaskWithSorting(field);
        return new APIResponse<>(allTasks.size(), allTasks);
    }


    @GetMapping("/pagination/{offset}/{pageSize}")
    private APIResponse<Page<Task>> getTasksWithPagination(@PathVariable int offset,
                                                           @PathVariable int pageSize) {
        Page<Task> tasksWithPagination = service.findProductsWithPagination(offset, pageSize);
        return new APIResponse<>(tasksWithPagination.getSize(), tasksWithPagination);
    }


    @GetMapping("/paginationAndSort/{offset}/{pageSize}/{field}")
    private APIResponse<Page<Task>> getTasksWithPaginationAndSort(@PathVariable int offset,
                                                                  @PathVariable int pageSize,
                                                                  @PathVariable String field) {
        Page<Task> tasksWithPagination = service.findProductsWithPaginationAndSorting(offset, pageSize, field);
        return new APIResponse<>(tasksWithPagination.getSize(), tasksWithPagination);
    }

}
