package ru.springgb.sem5HW.service;

import ru.springgb.sem5HW.Task;

import java.util.List;

public interface TaskService {

    Task createTask(Task task);
    List<Task> getAllTasks();

    Task getTaskById(Long id);
    List<Task> getTaskStatus(Task.Status status);


    Task updateTask(Long id, Task task);

    Task apdateTask(Task task);
    void deleteById(Long id);

}
