package com.tech.turtles.todos.todoList.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import com.tech.turtles.todos.todoList.entity.Task;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface TaskRepository extends JpaRepository<Task,Integer> {
        Task findByTitle(String title);


}

