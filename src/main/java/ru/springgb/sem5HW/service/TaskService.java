package ru.springgb.sem5HW.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.springgb.sem5HW.model.Task;

import java.util.List;

public interface TaskService {

    Task createTask(Task task);
    List<Task> getAllTasks();

    Task getTaskById(Long id);
    //List<Task> getTaskStatus(String status);

    Task updateTask(Long id, Task task);

    Task apdateTask(Task task);
    void deleteById(Long id);

    Page<Task> findPaginated(int pageNo, int pageSize,String sortField, String sortDirection);


    //Page<Task> findPaginated(Pageable pageable);

    List<Task> findTaskWithSorting(String field);
    Page<Task> findProductsWithPagination(int offset,int pageSize);
    Page<Task> findProductsWithPaginationAndSorting(int offset,int pageSize,String field);


    List<Task> getByKeyword(String keyword);
}
