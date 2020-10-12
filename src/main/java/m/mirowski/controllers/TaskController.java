package m.mirowski.controllers;

import m.mirowski.models.Task;
import m.mirowski.repositories.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

//@RepositoryRestController
@RestController
public class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private final TaskRepository repository;

    TaskController(final TaskRepository repository) {
        this.repository = repository;
    }

    // We get inside this custom findAll() query implementation ONLY IF there are no sort, page or size parameters
    //  specified in the query sent to our app.
    @GetMapping(value = "/tasksToDo", params = {"!sort", "!page", "!size"})
    ResponseEntity<List<Task>> readAllTasks() {
        logger.debug("Read all the tasks.");
        return ResponseEntity.ok(repository.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/tasksToDo")
    ResponseEntity<Page<Task>> readAllTasks(Pageable page) {
        logger.debug("Custom pageable method invoked.");
        return ResponseEntity.ok(repository.findAll(page));
    }

    @GetMapping(path = "/tasksToDo/{id}")
    ResponseEntity<Task> readTask(@PathVariable int id) {
        logger.debug("Read task by ID.");
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/tasksToDo/{id}")
    ResponseEntity<Task> updateTask(@PathVariable int id, @RequestBody @Valid Task taskToBeUpdated) {
        logger.debug("Put an updated task on the list");

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        taskToBeUpdated.setId(id);
        repository.save(taskToBeUpdated);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/tasksToDo")
    ResponseEntity<Task> putTask(@RequestBody @Valid Task taskToBePosted) {
        logger.debug("Post a new task on the list");
        Task result = repository.save(taskToBePosted);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }
}
