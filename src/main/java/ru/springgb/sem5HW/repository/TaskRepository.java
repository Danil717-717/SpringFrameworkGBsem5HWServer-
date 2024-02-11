package ru.springgb.sem5HW.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.springgb.sem5HW.model.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByStatus(Task.Status status);

    @Query(value = "select * from tasks s where s.status like %:keyword% ", nativeQuery = true)
    List<Task> findByKeyword(@Param("keyword") String keyword);
}
