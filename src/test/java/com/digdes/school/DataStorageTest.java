package com.digdes.school;

import com.digdes.school.predicates.EqualsPredicate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DataStorageTest {
    @Test
    void insertInsert() {
        DataStorage dataStorage = new DataStorage(new ArrayList<>());
        Map<String,Object> row3 = new HashMap<>();
        row3.put("id",3);
        row3.put("lastName","Федоров");
        row3.put("age",40);
        row3.put("active", true);

        Map<String,Object> row4= new HashMap<>();
        row4.put("id",4);
        row4.put("lastName","Федоров");
        row4.put("age",40);
        row4.put("active", true);
        dataStorage.insert(row3 );
        dataStorage.insert(row4);
        List<Map<String, Object>> select = dataStorage.select(x -> true);
        Assertions.assertEquals(2, select.size());
    }

    @Test
    void insertDelete() {
        DataStorage dataStorage = new DataStorage(new ArrayList<>());
        Map<String,Object> row3 = new HashMap<>();
        row3.put("id",3);
        row3.put("lastName","Федоров");
        row3.put("age",40);
        row3.put("active", true);
        dataStorage.insert(row3 );
        dataStorage.delete(x -> true);
        List<Map<String, Object>> select = dataStorage.select(x -> true);
        Assertions.assertEquals(0, select.size());
    }

    @Test
    void insertUpdate() {
        DataStorage dataStorage = new DataStorage(new ArrayList<>());
        Map<String,Object> first = new HashMap<>();
        first.put("id",3l);
        first.put("lastName","Федоров");
        first.put("age",40l);
        first.put("cost", 1.0);
        first.put("active", true);
        dataStorage.insert(first );
        Map<String,Object> row4= new HashMap<>();
        row4.put("active", false);
        dataStorage.update(row4, new EqualsPredicate("active", true));
        List<Map<String, Object>> select = dataStorage.select(x -> true);
        Map<String,Object> expected = new HashMap<>();
        expected.put("id",3l);
        expected.put("lastName","Федоров");
        expected.put("age",40l);
        expected.put("cost", 1.0);
        expected.put("active", false);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, select.size()),
                () -> Assertions.assertEquals(expected, select.get(0))
        );
    }

}