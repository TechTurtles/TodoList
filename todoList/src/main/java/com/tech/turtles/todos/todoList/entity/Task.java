package com.tech.turtles.todos.todoList.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.Table;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import com.tech.turtles.todos.todoList.entity.Status;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "Task")
public class Task {
    @Id
    @GeneratedValue
    public int id;
    public String title;
    public String description;
    public String project;
    public Date createdAt;
    public Date updatedAt;
    public Status current;


    public Task(String title,String description, String project){
        this.title = title;
        this.description = description;
        this.project = project;
        this.current = Status.PENDING;
        this.createdAt = Date.from(Instant.now());
        this.updatedAt = Date.from(Instant.now());
    }

    public Task(String title,String description){
        this.title = title;
        this.description = description;
        this.project = project;
        this.current = Status.PENDING;
        this.createdAt = Date.from(Instant.now());
        this.updatedAt = Date.from(Instant.now());
    }
    // getters and setters: ~30 extra lines of code
}