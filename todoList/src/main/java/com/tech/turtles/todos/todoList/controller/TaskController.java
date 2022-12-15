package com.tech.turtles.todos.todoList.controller;

import com.tech.turtles.todos.todoList.entity.Status;
import com.tech.turtles.todos.todoList.entity.Task;
import com.tech.turtles.todos.todoList.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    private static final Logger LOG = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService service;

    @PostMapping(value = "/task/new",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTask(@RequestBody Task t) {
        try {
            Task task = (Task) service.saveTask(t);
            return ResponseEntity.ok().body(task);
        } catch (Exception e) {
            LOG.error("Error sending object", e);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while fetching data");
    }
    @PostMapping("/task/mark")
    public ResponseEntity<?> markTask(@RequestParam int id, @RequestParam Status mark) {
        try {
            Task response = service.markTask(id,mark);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            LOG.error("Error sending object", e);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while fetching data");
    }
    @PostMapping("/task/save")
    public ResponseEntity<?> saveTasksToFile(@RequestParam String filename) {
        try {

            return ResponseEntity.ok().body("not implemented!");
        } catch (Exception e) {
            LOG.error("Error sending object", e);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while fetching data");
    }
    @GetMapping("/task/all")
    public ResponseEntity<?> getTasks() {
        try {
            List<Task> allTasks = service.getTasks();
            return ResponseEntity.ok().body(allTasks);
        } catch (Exception e) {
            LOG.error("Error sending object", e);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while fetching data");
    }
    @GetMapping("/task/all/sort")
    public ResponseEntity<?> sortTasks(@RequestParam String sortOrder) {
        try {
            return ResponseEntity.ok().body("not implemented!");
        } catch (Exception e) {
            LOG.error("Error sending object", e);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while fetching data");
    }
    @GetMapping("/task")
    public ResponseEntity<?> getTask(@RequestParam int id) {
        try {
            Task foundTask = service.getTaskById(id);
            return ResponseEntity.ok().body(foundTask);
        } catch (Exception e) {
            LOG.error("Error sending object", e);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while fetching data");
    }
    @DeleteMapping("/task")
    public ResponseEntity<?> deleteTask(@RequestParam int id) {
        try {
            String response = service.deleteTask(id);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            LOG.error("Error sending object", e);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while fetching data");
    }
    @PutMapping("/task/edit")
    public ResponseEntity<?> updateTask(@RequestParam int id, @RequestBody Task t) {
        try {
            Task task = (Task) service.saveTask(t);
            return ResponseEntity.ok().body(task);
        } catch (Exception e) {
            LOG.error("Error sending object", e);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while fetching data");
    }


}
