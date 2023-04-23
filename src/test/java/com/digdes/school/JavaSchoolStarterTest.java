package com.digdes.school;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JavaSchoolStarterTest {
    @Test
    void insertOne() throws Exception {
        JavaSchoolStarter database = new JavaSchoolStarter();
        List<Map<String, Object>> execute = database
                .execute("INSERT VALUES ‘lastName’ = ‘Федоров’ , ‘id’=3, ‘age’=40, ‘active’=true");
        List<Map<String, Object>> select = database.execute("SELECT");
    }

    @Test
    void insertInsert() throws Exception {
        JavaSchoolStarter database = new JavaSchoolStarter();
        database.execute("INSERT VALUES ‘lastName’ = ‘Федоров’ , ‘id’=3, ‘age’=40, ‘active’=true");
        database.execute("INSERT VALUES ‘lastName’ = ‘Федоров’ , ‘id’=3, ‘age’=40, ‘active’=true");
        List<Map<String, Object>> select = database.execute("SELECT");
    }
}