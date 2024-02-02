package ru.springgb.sem5HW.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.springgb.sem5HW.Task;

import java.util.List;

public interface TaskService {

    Task createTask(Task task);
    List<Task> getAllTasks();

    Task getTaskById(Long id);
    List<Task> getTaskStatus(String status);

    Task updateTask(Long id, Task task);

    Task apdateTask(Task task);
    void deleteById(Long id);

    Page<Task> findPaginated(int pageNo, int pageSize);


    Page<Task> findPaginated(Pageable pageable);
}
