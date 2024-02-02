package ru.springgb.sem5HW.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.springgb.sem5HW.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByStatus(Task.Status status);
}
