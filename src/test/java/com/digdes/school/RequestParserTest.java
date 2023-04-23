package com.digdes.school;

import com.digdes.school.predicates.EqualsPredicate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RequestParserTest {
    RequestParser requestParser = new RequestParser();
    @Test
    void parseSelect() {
        Operation parsed = requestParser.parse("SELECT");
        Assertions.assertAll(
                () -> Assertions.assertEquals("SELECT", parsed.getAction()),
                () -> Assertions.assertEquals(0, parsed.getValues().size())
        );

    }

    @Test
    void parseSelectWhere() {
        Operation parsed = requestParser.parse("SELECT WHERE ‘age’>=30");
        Assertions.assertAll(
                () -> Assertions.assertEquals("SELECT", parsed.getAction()),
                () -> Assertions.assertEquals(0, parsed.getValues().size())
        );
    }

    @Test
    void parseSelectWhereAnd() {
        Operation parsed = requestParser.parse("SELECT WHERE ‘age’>=30");
        Assertions.assertAll(
                () -> Assertions.assertEquals("SELECT", parsed.getAction()),
                () -> Assertions.assertEquals(0, parsed.getValues().size())
        );
    }

    @Test
    void parseInsert() {
        Operation parsed = requestParser.parse("INSERT VALUES ‘lastName’ = ‘Федоров’ , ‘id’=3, ‘age’=40, ‘active’=true");
        Assertions.assertEquals("INSERT", parsed.getAction());
        Map<String,Object> row = new HashMap<>();
        row.put("id",3l);
        row.put("lastName","Федоров");
        row.put("age",40l);
        row.put("active", true);
        Assertions.assertAll(
                () -> Assertions.assertEquals("INSERT", parsed.getAction()),
                () -> Assertions.assertEquals(row, parsed.getValues())
        );
    }



    @Test
    void parseUpdateWhere() {
        Map<String,Object> row = new HashMap<>();
        row.put("active", true);
        Operation parsed = requestParser.parse("UPDATE VALUES ‘active’=true  where ‘active’=false");
        Assertions.assertAll(
                () -> Assertions.assertEquals("UPDATE", parsed.getAction()),
                () -> Assertions.assertEquals(row, parsed.getValues()),
                () -> Assertions.assertTrue(parsed.getPredicate() instanceof EqualsPredicate)
        );
    }

    @Test
    void parseUpdateWhereWhereLastName() {
        Map<String,Object> row = new HashMap<>();
        row.put("lastName", "Федоров where");
        Operation parsed = requestParser.parse("UPDATE VALUES ‘lastName‘ = ‘Федоров where ‘  where ‘lastName‘ = ‘Федоров WHERE ‘");
        Assertions.assertAll(
                () -> Assertions.assertEquals("UPDATE", parsed.getAction()),
                () -> Assertions.assertEquals(row, parsed.getValues()),
                () -> Assertions.assertTrue(parsed.getPredicate() instanceof EqualsPredicate)
        );
    }

    @Test
    void parseUpdateWhereWhiteSpace() {
        Map<String,Object> row = new HashMap<>();
        row.put("lastName", "Федоров d");
        Operation parsed = requestParser.parse("UPDATE VALUES ‘lastName‘ = ‘Федоров d‘  where ‘lastName‘ = ‘Федоров b‘");
        Assertions.assertAll(
                () -> Assertions.assertEquals("UPDATE", parsed.getAction()),
                () -> Assertions.assertEquals(row, parsed.getValues()),
                () -> Assertions.assertTrue(parsed.getPredicate() instanceof EqualsPredicate)
        );
    }

    @Test
    void parseDelete() {
        Operation parsed = requestParser.parse("DELETE where ‘active’=false");
        Assertions.assertAll(
                () -> Assertions.assertEquals("DELETE", parsed.getAction()),
                () -> Assertions.assertEquals(0, parsed.getValues().size()),
                () -> Assertions.assertTrue(parsed.getPredicate() instanceof EqualsPredicate)
        );
    }

}