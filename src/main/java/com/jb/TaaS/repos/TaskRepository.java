package com.jb.TaaS.repos;

import com.jb.TaaS.beans.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findAllByOrderByWhenAsc();

    List<Task> findAllByOrderByWhenDesc();

    List<Task> findAllByWhenBetween(Timestamp start, Timestamp end);

    List<Task> findByUserId(int userId);
}
