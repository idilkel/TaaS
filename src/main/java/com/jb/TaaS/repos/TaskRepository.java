package com.jb.TaaS.repos;

import com.jb.TaaS.beans.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    @Query(value = "delete from `spring-147`.tasks where id= :id and user_id= :userId and id<>0;", nativeQuery = true)
    void deleteByIdAndUserId(@Param("id") int id, @Param("userId") int userId);

    @Query(value = "SELECT * FROM (SELECT * FROM `spring-147`.tasks  where user_id= :userId) AS T  ORDER BY task_time ASC", nativeQuery = true)
    List<Task> findAllByUserIdAndByOrderByWhenAsc(@Param("userId") int userId);

    @Query(value = "SELECT * FROM (SELECT * FROM `spring-147`.tasks  where user_id= :userId) AS T  ORDER BY task_time DESC", nativeQuery = true)
    List<Task> findAllByUserIdAndByOrderByWhenDesc(@Param("userId") int userId);

    //    @Query(value = "SELECT * FROM (SELECT * FROM `spring-147`.tasks  where user_id= :userId) AS T  WHERE (task_time BETWEEN '2022-08-16 14:15:55' AND '2023-09-20 10:15:55')", nativeQuery = true)
    @Query(value = "SELECT * FROM (SELECT * FROM `spring-147`.tasks  where user_id= :userId) AS T  WHERE (task_time BETWEEN :start AND :end)", nativeQuery = true)
    List<Task> findAllByUserIdAndByWhenBetween(@Param("userId") int userId, @Param("start") Timestamp start, @Param("end") Timestamp end);


    //Doesn't work:
//    List<Task> findAllByUserIdAndByOrderByWhenAsc(int userId);
//
//    List<Task> findAllByUserIdAndByOrderByWhenDesc(int userId);
//
//    List<Task> findAllByUserIdAndByWhenBetween(int userId, Timestamp start, Timestamp end);
}
