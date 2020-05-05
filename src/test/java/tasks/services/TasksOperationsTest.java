package tasks.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.model.TaskList;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TasksOperationsTest {
    private static int interval;
    private static Date startDate, endDate;
    private static String title;

    private static Date getDateFromString(String dateString) throws Exception {
        return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(dateString);
    }

    @BeforeEach
    void initDefaultParameters(){
        interval = 10;
        title = "sample_task";
        try {
            startDate = getDateFromString("07-07-2020 08:00:00");
            endDate = getDateFromString("07-07-2020 10:00:00");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void testLenghtTitle() {
        TasksOperations tasksOperations = new TasksOperations(FXCollections.observableArrayList());
        assertThrows(IllegalArgumentException.class, ()-> tasksOperations.tasksByTitle("titlu_mai_mare_de_25_de_caractere"), "Lungimea introdusa este prea mare");

    }

    @Test
    void testEmptyTitle(){
        TasksOperations tasksOperations = new TasksOperations(FXCollections.observableArrayList());
        assertThrows(IllegalArgumentException.class, ()-> tasksOperations.tasksByTitle(""), "Câmpul introdus este vid");
    }

    @Test
    void testMatch(){
        title = "Ce";
        Task t =new Task(title, startDate);
        ObservableList<Task> tasks =FXCollections.observableArrayList();
        tasks.add(t);
        TasksOperations tasksOperations = new TasksOperations(tasks);
        ArrayList list = (ArrayList) tasksOperations.tasksByTitle("Ce");
        assertEquals(list, tasks);
    }

    @Test
    void testNoMatch(){
        title = "Ceva";
        Task t =new Task(title, startDate);
        ObservableList<Task> tasks =FXCollections.observableArrayList();
        tasks.add(t);
        TasksOperations tasksOperations = new TasksOperations(tasks);
        assertEquals(( tasksOperations.tasksByTitle("Altceva")), new ArrayList<>());
    }
}

