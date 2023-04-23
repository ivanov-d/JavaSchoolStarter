package com.digdes.school;

import com.digdes.school.predicates.PredicateParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

class PredicateParserTest {

    private Map<String, Object> row1;
    private Map<String, Object> row2;

    @BeforeEach
    void setUp() {
        row1 = new HashMap<>();
        row1.put("id", 1);
        row1.put("lastName", "Петров");
        row1.put("age", 30);
        row1.put("cost", 5.4);
        row1.put("active", true);

        row2 = new HashMap<>();
        row2.put("id", 2);
        row2.put("lastName", "Иванов");
        row2.put("age", 25);
        row2.put("cost", 4.3);
        row2.put("active", false);

    }

    @Test
    void oneEquals() {
        Predicate<Map<String, Object>> predicate = new PredicateParser().parse("‘active’=false");
        Assertions.assertAll(
                () -> Assertions.assertTrue(predicate.test(row2)),
                () -> Assertions.assertFalse(predicate.test(row1))
        );
    }

    @Test
    void twoEquals() {
        Predicate<Map<String, Object>> predicate = new PredicateParser().parse("‘active’=false or ‘id‘ = 1");
        Assertions.assertAll(
                () -> Assertions.assertTrue(predicate.test(row2)),
                () -> Assertions.assertTrue(predicate.test(row1))
        );
    }

    @Test
    void twoEqualsZero() {
        Predicate<Map<String, Object>> predicate = new PredicateParser().parse("‘age’=99 or ‘id‘ = 3");
        Assertions.assertAll(
                () -> Assertions.assertFalse(predicate.test(row2)),
                () -> Assertions.assertFalse(predicate.test(row1))
        );
    }
}