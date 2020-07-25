package com.petproject.task;

import com.petproject.custom.pagination.CustomPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.util.Optional;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomPage<Task> getAll(
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "5") int size,
        @RequestParam(value = "sort", defaultValue = "id") String sort,
        @RequestParam(value = "direction", defaultValue = "asc") String direction
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(
            direction.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC,
            sort
        ));

        return new CustomPage<Task>(taskRepository.findAll(pageable));
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
    public ResponseEntity<Task> getById(@PathVariable("id") Long id) {
        Optional<Task> maybeTask = taskRepository.findById(id);

        return maybeTask
                .map(task -> new ResponseEntity<>(task, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") Long id) {
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
        @PathVariable(value = "id") Long id, @RequestBody Task task
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
