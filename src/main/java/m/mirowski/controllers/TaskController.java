package m.mirowski.controllers;

import m.mirowski.models.Task;
import m.mirowski.repositories.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RepositoryRestController
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

//    @RequestMapping(method = RequestMethod.GET, path = "/tasksToDo")
//    Task getTaskById(int id) {
//        logger.debug("Find task by given id.");
//        return repository.findById(id).orElseGet(Task::new);
//    }

}
