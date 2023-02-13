package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Roles;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements ApplicationRunner {

    private final UserService userService;

    @Autowired
    public DataLoader(UserService userService) {
        this.userService = userService;
    }

    public void run(ApplicationArguments args) {

        User man = new User("Иван", "Иванов", 25, "ivan", "ivan");
        User woman = new User("Мария", "Петрова", 20, "marya", "marya");
        User cat = new User("Кот", "Котов", 3, "cat", "cat");

        Roles admin = new Roles("ROLE_ADMIN");
        Roles user = new Roles("ROLE_USER");

        man.setRoles(new HashSet<>(Set.of(admin)));
        woman.setRoles(new HashSet<>(Set.of(user)));
        cat.setRoles(new HashSet<>(Set.of(admin, user)));

        userService.addRoles(admin);
        userService.addRoles(user);

        userService.addUser(man);
        userService.addUser(woman);
        userService.addUser(cat);
        System.out.println();

    }
}