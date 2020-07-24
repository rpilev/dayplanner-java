package com.petproject.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public Iterable<Task> getAll(HttpServletResponse response) {
        return taskRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Task> add(@RequestBody Task task, HttpServletResponse response) {
        if (taskRepository.existsById(task.getId())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        else {
            task.setId(0);
            taskRepository.save(task);

            return new ResponseEntity<>(task, HttpStatus.CREATED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable(value = "id") Long id) {
        Optional<Task> maybeTask = taskRepository.findById(id);

        return maybeTask
                .map(task -> new ResponseEntity<>(task, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long id) {
        Optional<com.petproject.task.Task> maybeTask = taskRepository.findById(id);

        return maybeTask
                .map(task -> {
                    taskRepository.deleteById(id);

                    return new ResponseEntity<>(HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> editById(
            @PathVariable(value = "id") Long id,
            @RequestBody Task task
    ) {

        Optional<Task> taskOptional = taskRepository.findById(id);

        if (taskOptional.isPresent()) {
            task.setId(id);
            taskRepository.save(task);

            return new ResponseEntity<>(task, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
