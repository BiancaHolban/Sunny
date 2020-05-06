package tasks.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tasks.model.ArrayTaskList;
import tasks.model.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.fail;

public class ArrayTaskListTestL4 {

    private Date dateStart,dateEnd;
    private SimpleDateFormat sdf;
    private ArrayTaskList repo;
    @BeforeEach
    void setUp() {
        repo = new ArrayTaskList();
        sdf = Task.getDateFormat();
        try
        {
            dateStart = sdf.parse("2020-13-23 08:00");
            dateEnd = sdf.parse("2020-03-23 12:00");
        }catch (ParseException e)
        {
            fail(e.getMessage());
        }
    }
    @Test
    public void test_remove_valid(){

        Task task1 = Mockito.mock(Task.class);
        Task task2 = Mockito.mock(Task.class);
        Task task3 = Mockito.mock(Task.class);

        repo.add(task1);
        repo.add(task2);
        repo.add(task3);

        assert repo.size() == 3;
        assert repo.remove(task1);
        assert repo.size() == 2;
    }
    @Test
    public void test_remove_invalid(){
        Task task1 = Mockito.mock(Task.class);
        Task task2 = Mockito.mock(Task.class);
        Task task3 = Mockito.mock(Task.class);

        repo.add(task1);
        repo.add(task2);

        assert repo.size() == 2;
        assert !repo.remove(task3);
        assert repo.size() == 2;
    }
}
