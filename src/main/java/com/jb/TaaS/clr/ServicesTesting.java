package com.jb.TaaS.clr;

import com.jb.TaaS.dto.TaskDto;
import com.jb.TaaS.exceptions.TaskSystemException;
import com.jb.TaaS.services.TaskService;
import com.jb.TaaS.utils.ArtUtils;
import com.jb.TaaS.utils.TestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
@Order(2)
@RequiredArgsConstructor
public class ServicesTesting implements CommandLineRunner {

    private final TaskService taskService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(ArtUtils.SERVICE_TESTS);

        try {
            TestUtils.printTitle("Add new task and get all tasks");
            TaskDto taskDto1 = TaskDto.builder().caption("java147").classification("java147").info("Coupon System Phase 2").dueDate((LocalDateTime.of(2022, 6, 20, 9, 00))).build();
            taskService.addTask(taskDto1);
            taskService.getAllTasks().forEach(System.out::println);
        } catch (TaskSystemException e) {
            //e.printStackTrace();
            System.out.println(e);
        }

        try {
            TestUtils.printTitle("Get one task#2");
            System.out.println(taskService.getOneTask(2));
        } catch (TaskSystemException e) {
            //e.printStackTrace();
            System.out.println(e);
        }

        try {
            TestUtils.printTitle("Get un-existing task#20");
            System.out.println(taskService.getOneTask(20));
        } catch (TaskSystemException e) {
            //e.printStackTrace();
            System.out.println(e);
        }

        try {
            TestUtils.printTitle("Try to add an existing task#1");
            TaskDto taskDto1 = taskService.getOneTask(1);
            taskService.addTask(taskDto1);
        } catch (TaskSystemException e) {
            //e.printStackTrace();
            System.out.println(e);
        }

        try {
            TestUtils.printTitle("Updating task#1");
            TaskDto taskDto1 = taskService.getOneTask(1);
            System.out.println("Task#1 before update");
            System.out.println(taskDto1);
            taskDto1.setInfo("Spring exam");
            taskDto1.setDueDate(LocalDateTime.now().plusDays(5));
            taskService.updateTask(1, taskDto1);
            taskDto1 = taskService.getOneTask(1);
            System.out.println("Task#1 after update");
            System.out.println(taskDto1);
            System.out.println("****************************");
            System.out.println(taskDto1.getDueDate());
        } catch (TaskSystemException e) {
            //e.printStackTrace();
            System.out.println(e);
        }

        try {
            TestUtils.printTitle("Updating unexisting task#20");
            TaskDto taskDto1 = taskService.getOneTask(1);
            System.out.println("Task#1 before update");
            System.out.println(taskDto1);
            taskDto1.setInfo("Spring exam");
            taskDto1.setId(20);
            taskDto1.setDueDate(LocalDateTime.now().plusDays(5));
            taskService.updateTask(20, taskDto1);
            taskDto1 = taskService.getOneTask(1);
            System.out.println("Task#1 after update");
            System.out.println(taskDto1);
        } catch (TaskSystemException e) {
            //e.printStackTrace();
            System.out.println(e);
        }

        try {
            TestUtils.printTitle("Get all tasks ascending");
            taskService.getAllTasksOrderByTimeAsc().forEach(System.out::println);
            TestUtils.printTitle("Get all tasks descending");
            taskService.getAllTasksOrderByTimeDesc().forEach(System.out::println);
            TestUtils.printTitle("Get all tasks between");
            Timestamp start = Timestamp.valueOf(LocalDateTime.now());
            Timestamp end = Timestamp.valueOf(LocalDateTime.now().plusWeeks(1));
            taskService.getAllTasksBetween(start, end).forEach(System.out::println);
            TestUtils.printTitle("Get all tasks between end date before start date");
            taskService.getAllTasksBetween(end, start).forEach(System.out::println);
        } catch (TaskSystemException e) {
            //e.printStackTrace();
            System.out.println(e);
        }

        try {
            TestUtils.printTitle("Delete task#5");
            System.out.println("Tasks before delete:");
            taskService.getAllTasks().forEach(System.out::println);
            taskService.deleteTask(5);
            System.out.println("Tasks after delete:");
            taskService.getAllTasks().forEach(System.out::println);
        } catch (TaskSystemException e) {
            //e.printStackTrace();
            System.out.println(e);
        }
    }
}
