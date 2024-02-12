package ru.springgb.sem5HW.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import ru.springgb.sem5HW.model.Task;
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

    @Override
    public Page<Task> findPaginated(int pageNo, int pageSize,String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.taskRepository.findAll(pageable);
    }


    @Override
    public List<Task> findTaskWithSorting(String fi) {
        return taskRepository.findAll(Sort.by(Sort.Direction.ASC,fi));
    }

    @Override
    public Page<Task> findProductsWithPagination(int offset,int pageSize){
        Page<Task> tasks = taskRepository.findAll(PageRequest.of(offset, pageSize));
        return  tasks;
    }
    @Override
    public Page<Task> findProductsWithPaginationAndSorting(int offset,int pageSize,String field){
        Page<Task> tasks = taskRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return  tasks;
    }


    @Override
    public List<Task> getByKeyword(String keyword){
        return taskRepository.findByKeyword(keyword);
    }


}
