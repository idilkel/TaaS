package com.jb.TaaS.repos;

import com.jb.TaaS.beans.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findAllByOrderByWhenAsc();

    List<Task> findAllByOrderByWhenDesc();

    List<Task> findAllByWhenBetween(Timestamp start, Timestamp end);

    List<Task> findByUserId(int userId);

    int countByUserId(int userId);

    @Transactional
    @Modifying
    @Query(value = "delete from `spring-147`.tasks where id=2 and user_id=2 and id<>0;", nativeQuery = true)
    void deleteByIdAndUserId(int id, int userId);
}
