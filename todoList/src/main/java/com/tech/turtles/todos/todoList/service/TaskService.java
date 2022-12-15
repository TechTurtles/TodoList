package com.tech.turtles.todos.todoList.service;

import com.tech.turtles.todos.todoList.controller.TaskController;
import com.tech.turtles.todos.todoList.entity.Status;
import com.tech.turtles.todos.todoList.entity.Task;
import com.tech.turtles.todos.todoList.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {
    private static final Logger LOG = LoggerFactory.getLogger(TaskService.class);
    @Autowired
    private TaskRepository repository;

    public Task saveTask(Task newTask) {
        newTask.setUpdatedAt(Date.from(Instant.now()));
        newTask.setCreatedAt(Date.from(Instant.now()));
        newTask.setCurrent(Status.PENDING);
        return repository.save(newTask);
    }

    public List<Task> saveTasks(List<Task> tasks) {
        for (Task task : tasks) {
            task.setUpdatedAt(Date.from(Instant.now()));
            task.setCreatedAt(Date.from(Instant.now()));
        }

        return repository.saveAll(tasks);
    }

    public List<Task> getTasks() {
        return repository.findAll();
    }

    public Task getTaskById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Task getTaskByTitle(String title) {
        return repository.findByTitle(title);
    }

    public String deleteTask(int id) {
        repository.deleteById(id);
        return "Task removed !! " + id;
    }

    public Task updateTask(int id,Task task) {
        Task existingTask = repository.findById(id).orElse(null);
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setProject(task.getProject());
        existingTask.setUpdatedAt(Date.from(Instant.now()));
        return repository.save(existingTask);
    }

    public Task markTask(int id,Status status){
        Task existingTask = repository.findById(id).orElse(null);
        existingTask.setCurrent(status);

        return repository.save(existingTask);
    }

    public List<Task> sortTasks(String sortOrder){
        List<Task> allTasks = repository.findAll();
        switch (sortOrder){
            case "ByDate":
                allTasks.sort(new Comparator<Task>() {
                    @Override
                    public int compare(Task o1, Task o2) {
                        return o1.getCreatedAt().compareTo(o2.getCreatedAt());
                    }
                });
            case "ByProject":
                allTasks.sort(new Comparator<Task>() {
                    @Override
                    public int compare(Task o1, Task o2) {
                        return o1.getProject().compareTo(o2.getProject());
                    }
                });
            default:
                LOG.info("not Implemented!");
        }
        return allTasks;
    }
    public String saveTasks(String filename) throws IOException {
        List<Task> allTasks = repository.findAll();
        LOG.info("not Implemented!");
        //gson.toJson(allTasks, new FileWriter(System.getProperty("user.dir").toLowerCase()+filename));
        return "Saved";
    }
}
