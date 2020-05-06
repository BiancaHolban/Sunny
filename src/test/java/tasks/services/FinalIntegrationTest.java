package tasks.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.model.ArrayTaskList;
import tasks.model.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class FinalIntegrationTest {
    private static ArrayTaskList repo;
    private static TasksService service;
    private Date dateStart,dateEnd;
    private SimpleDateFormat sdf;

    @BeforeEach
    void setUp() {
        repo = new ArrayTaskList();
        service = new TasksService(repo);

        sdf = Task.getDateFormat();
        try
        {
            dateStart = sdf.parse("2020-03-23 08:00");
            dateEnd = sdf.parse("2020-03-24 12:00");


        }catch (ParseException e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    public void test_filterTasks(){
        Task task = new Task("t1", dateStart);
        repo.add(task);
        Iterable filteredTasks = service.filterTasks(dateStart, dateEnd);
        assertNotNull(filteredTasks);
    }

    @Test
    public void test_getObservableList(){
        Task task = new Task("t1", dateStart);
        repo.add(task);
        assertEquals(service.getObservableList().size(), 1);
    }
}
