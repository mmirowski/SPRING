package m.mirowski.repositories;

import m.mirowski.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(path = "tasksToDo", collectionResourceRel = "myTasks")
public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Override
    @RestResource(exported = false)
    void deleteById(Integer integer);

    @Override
    @RestResource(exported = false)
    void delete(Task task);

    @RestResource(path = "doneTrue", rel = "doneTrue")
    List<Task> findByDoneIsTrue();

    @RestResource(path = "done", rel = "done")
    List<Task> findByDoneIs(@Param("state") boolean done);

}
