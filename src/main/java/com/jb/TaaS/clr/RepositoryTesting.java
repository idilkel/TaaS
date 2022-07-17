package com.jb.TaaS.clr;

import com.jb.TaaS.beans.ClientType;
import com.jb.TaaS.beans.Task;
import com.jb.TaaS.beans.User;
import com.jb.TaaS.repos.TaskRepository;
import com.jb.TaaS.repos.UserRepository;
import com.jb.TaaS.utils.ArtUtils;
import com.jb.TaaS.utils.TestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
@Order(1)
@RequiredArgsConstructor
public class RepositoryTesting implements CommandLineRunner {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        try {
            System.out.println(ArtUtils.REPOSITORY_TESTS);
            Task task1 = Task.builder().group("java147").title("Spring test").description("Spring test").when(Timestamp.valueOf(LocalDateTime.of(2022, 05, 26, 9, 00))).build();
            Task task2 = Task.builder().group("java147").title("Learn JS").description("Learn JS").when(Timestamp.valueOf(LocalDateTime.now().plusWeeks(2))).build();
            Task task3 = Task.builder().group("java147").title("Learn HTML").description("Learn HTML").when(Timestamp.valueOf(LocalDateTime.now().plusWeeks(1))).build();
            Task taskToDelete = Task.builder().group("todelete").title("toDelete").description("toDelete").when(Timestamp.valueOf(LocalDateTime.of(2022, 06, 10, 22, 00))).build();

            User user1 = User.builder().email("admin@admin.com").password("admin").type(ClientType.ADMIN).build();
            User user2 = User.builder().email("idil@gmail.com").password("1234").type(ClientType.USER).tasks(Arrays.asList(task1, task2)).build();
            User user3 = User.builder().email("ben@gmail.com").password("1234").type(ClientType.USER).tasks(List.of(task3, taskToDelete)).build();

            // TODO: 23/05/2022 Why before this setting the users don't have tasks on printing 
            task1.setUser(user2);
            task2.setUser(user2);
            task3.setUser(user3);
            taskToDelete.setUser(user3);

            userRepository.saveAll(Arrays.asList(user1, user2, user3));

            System.out.println("3 users were added:");

            userRepository.findAll().forEach(System.out::println);

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
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println(e);
        }

        try {
            System.out.println("~~~~~~~~~~~~~~~~Number of tasks of user2:~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(taskRepository.countByUserId(2));
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println(e);
        }
    }
}
