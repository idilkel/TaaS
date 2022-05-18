package com.jb.TaaS.clr;

import com.jb.TaaS.beans.Task;
import com.jb.TaaS.exceptions.CustomTaskException;
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
            Task task1 = Task.builder().group("java147").description("Coupon System Phase 2").when(Timestamp.valueOf(LocalDateTime.of(2022, 6, 20, 9, 00))).build();
            taskService.addTask(task1);
            taskService.getAllTasks().forEach(System.out::println);
        } catch (CustomTaskException e) {
            //e.printStackTrace();
            System.out.println(e);
        }

        try {
            TestUtils.printTitle("Get one task#2");
            System.out.println(taskService.getOneTask(2));
        } catch (CustomTaskException e) {
            //e.printStackTrace();
            System.out.println(e);
        }

        try {
            TestUtils.printTitle("Get un-existing task#20");
            System.out.println(taskService.getOneTask(20));
        } catch (CustomTaskException e) {
            //e.printStackTrace();
            System.out.println(e);
        }

        try {
            TestUtils.printTitle("Try to add an existing task#1");
            Task task1 = taskService.getOneTask(1);
            taskService.addTask(task1);
        } catch (CustomTaskException e) {
            //e.printStackTrace();
            System.out.println(e);
        }

        try {
            TestUtils.printTitle("Updating task#1");
            Task task1 = taskService.getOneTask(1);
            System.out.println("Task#1 before update");
            System.out.println(task1);
            task1.setDescription("Spring exam");
            task1.setWhen(Timestamp.valueOf(LocalDateTime.now().plusDays(5)));
            taskService.updateTask(1, task1);
            task1 = taskService.getOneTask(1);
            System.out.println("Task#1 after update");
            System.out.println(task1);
        } catch (CustomTaskException e) {
            //e.printStackTrace();
            System.out.println(e);
        }

        try {
            TestUtils.printTitle("Updating unexisting task#20");
            Task task1 = taskService.getOneTask(1);
            System.out.println("Task#1 before update");
            System.out.println(task1);
            task1.setDescription("Spring exam");
            task1.setId(20);
            task1.setWhen(Timestamp.valueOf(LocalDateTime.now().plusDays(5)));
            taskService.updateTask(20, task1);
            task1 = taskService.getOneTask(1);
            System.out.println("Task#1 after update");
            System.out.println(task1);
        } catch (CustomTaskException e) {
            //e.printStackTrace();
            System.out.println(e);
        }

        try {
            TestUtils.printTitle("Get all tasks ascending");
            taskService.getAllTasksAsc().forEach(System.out::println);
            TestUtils.printTitle("Get all tasks descending");
            taskService.getAllTasksDesc().forEach(System.out::println);
            TestUtils.printTitle("Get all tasks between");
            Timestamp start = Timestamp.valueOf(LocalDateTime.now());
            Timestamp end = Timestamp.valueOf(LocalDateTime.now().plusWeeks(1));
            taskService.getAllTasksBetween(start, end).forEach(System.out::println);
            TestUtils.printTitle("Get all tasks between end date before start date");
            taskService.getAllTasksBetween(end, start).forEach(System.out::println);
        } catch (CustomTaskException e) {
            //e.printStackTrace();
            System.out.println(e);
        }
    }
}
