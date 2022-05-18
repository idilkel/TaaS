package com.jb.TaaS.clr;

import com.jb.TaaS.beans.Task;
import com.jb.TaaS.repos.TaskRepository;
import com.jb.TaaS.utils.ArtUtils;
import com.jb.TaaS.utils.TestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@Order(1)
@RequiredArgsConstructor
public class RepositoryTesting implements CommandLineRunner {

    private final TaskRepository taskRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(ArtUtils.REPOSITORY_TESTS);
        Task task1 = Task.builder().group("java147").description("Spring test").when(Timestamp.valueOf(LocalDateTime.of(2022, 05, 26, 9, 00))).build();
        Task task2 = Task.builder().group("java147").description("Learn JS").when(Timestamp.valueOf(LocalDateTime.now().plusWeeks(2))).build();
        Task task3 = Task.builder().group("java147").description("Learn HTML").when(Timestamp.valueOf(LocalDateTime.now().plusWeeks(1))).build();
        Task taskToDelete = Task.builder().group("todelete").description("toDelete").when(Timestamp.valueOf(LocalDateTime.of(2022, 06, 10, 22, 00))).build();

        taskRepository.saveAll(Arrays.asList(task1, task2, task3, taskToDelete));
        TestUtils.printTitle("Get one task#1");
        System.out.println(taskRepository.getById(1));

        TestUtils.printTitle("Get all tasks");
        taskRepository.findAll().forEach(System.out::println);

        TestUtils.printTitle("Delete task#4: Tasks after delete:");
        taskRepository.deleteById(4);
        taskRepository.findAll().forEach(System.out::println);

        TestUtils.printTitle("Get all tasks ascending date");
        taskRepository.findAllByOrderByWhenAsc().forEach(System.out::println);

        TestUtils.printTitle("Get all tasks descending date");
        taskRepository.findAllByOrderByWhenDesc().forEach(System.out::println);

        TestUtils.printTitle("Get all tasks between dates");
        Timestamp start = Timestamp.valueOf(LocalDateTime.now());
        Timestamp end = Timestamp.valueOf(LocalDateTime.now().plusWeeks(1));
        taskRepository.findAllByWhenBetween(start, end).forEach(System.out::println);

    }
}
