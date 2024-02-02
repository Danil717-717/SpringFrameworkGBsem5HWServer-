package ru.springgb.sem5HW.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.springgb.sem5HW.Task;
import ru.springgb.sem5HW.repository.TaskRepository;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {

        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(Task task) {

        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task getTask(Long id) {
        return getAllTasks().stream().filter(task -> task.getId().equals(id)).findFirst().orElse(null);
    }

    //&&&&&&&&
    @Override
    public List<Task> getTaskStatus(Task.Status status) {
//        List<Task>listStatus;
//
//        return getAllTasks().stream().filter(task->task.getStatus().equals(status))
//                .findAny().orElseGet()
        return null;
    }


    @Override
    public Task updateTask(Long id, Task task) {
        Task taskStaraya = getTask(id);
        if (taskStaraya != null) {
            taskStaraya.setDescription(task.getDescription());
            taskStaraya.setStatus(task.getStatus());
            taskStaraya.setCompletionTime(task.getCompletionTime());
        }
        return taskStaraya;
    }

    public Task apdateTask(Task task){
        return taskRepository.save(task);
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }



}
