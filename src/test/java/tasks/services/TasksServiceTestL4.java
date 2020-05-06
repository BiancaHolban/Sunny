package tasks.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tasks.model.ArrayTaskList;
import tasks.model.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TasksServiceTestL4 {

    private Date dateStart, dateEnd;
    private SimpleDateFormat sdf;

    @Mock
    private ArrayTaskList repo;

    @InjectMocks
    private TasksService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        sdf = Task.getDateFormat();
        try {
            dateStart = sdf.parse("2020-03-23 08:00");
            dateEnd = sdf.parse("2020-03-26 12:00");


        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test_size() {
        Task t = new Task("t", dateStart);
        Task t1 = new Task("t1", dateStart);
        Task t2 = new Task("t2", dateStart);
        Mockito.when(repo.getAll()).thenReturn(Arrays.asList(t, t1));

        assert 3 == service.getObservableList().size();
    }

    @Test
    public void test_filterTasks() {
        Task t = new Task("t", dateStart);
        Task t1 = new Task("t1", dateStart);
        Task t2 = new Task("t2", dateStart);
        Mockito.when(repo.getAll()).thenReturn(Arrays.asList(t, t1));

        Iterable<Task> filteredTasks = service.filterTasks(dateStart, dateEnd);

        assertNotNull(filteredTasks);
    }
}

