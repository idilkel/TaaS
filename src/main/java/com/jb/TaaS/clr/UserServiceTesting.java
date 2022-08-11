package com.jb.TaaS.clr;

import com.jb.TaaS.services.UserService;
import com.jb.TaaS.services.WelcomeService;
import com.jb.TaaS.utils.TestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;

@Component
@Order(3)
@RequiredArgsConstructor
public class UserServiceTesting implements CommandLineRunner {

    private final UserService userService;
    private final WelcomeService welcomeService;

    @Override
    public void run(String... args) throws Exception {
        TestUtils.printTitle("Login testing for user2");
        System.out.println(welcomeService.login("idil@gmail.com", "1234"));

        TestUtils.printTitle("Tasks Ascending");
        userService.getAllUserTasksTimeAsc(2).forEach(System.out::println);

        TestUtils.printTitle("Tasks Descending");
        userService.getAllUserTasksTimeDesc(2).forEach(System.out::println);

        TestUtils.printTitle("Count tasks");
        System.out.println(userService.count(2));

        TestUtils.printTitle("Tasks between dates");
        Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp1);
        Timestamp timestamp2 = Timestamp.from(timestamp1.toInstant().plus(14, ChronoUnit.DAYS));
        System.out.println(timestamp2);
        userService.getAllTasksBetween(2, timestamp1, timestamp2).forEach(System.out::println);

    }
}
