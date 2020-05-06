package tasks.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.model.TaskStubbedMock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RepoIntegrationTest {
    private static ArrayTaskList repo;
    private static TasksService service;
    private Date date;
    private SimpleDateFormat sdf;

    @BeforeEach
    void setUp() {
        repo = new ArrayTaskList();
        service = new TasksService(repo);

        sdf = Task.getDateFormat();
        try
        {
            date = sdf.parse("2020-03-23 08:00");


        }catch (ParseException e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    public void test_getObservableList(){
        TaskStubbedMock task = new TaskStubbedMock();
        repo.add(task);

        assertEquals(1, service.getObservableList().size());
    }

    @Test
    public void test_filterTasks() {
        TaskStubbedMock task = new TaskStubbedMock();
        repo.add(task);
        Iterable filteredTasks = service.filterTasks(date, date);
        assertNotNull(filteredTasks);
    }


}