package tasks.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskStubbedMock extends Task {

    @Override
    public String getTitle(){
        return "task";
    }

    @Override
    public Date nextTimeAfter(Date current) {
        try {
            return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse("05-05-2020 08:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
