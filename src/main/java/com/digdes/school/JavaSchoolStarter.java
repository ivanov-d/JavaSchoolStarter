package com.digdes.school;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JavaSchoolStarter {

    private final DataStorage dataStorage;
    private final RequestParser requestParser;

    public JavaSchoolStarter(){
        dataStorage = new DataStorage(new ArrayList<>());
        requestParser = new RequestParser();
    }

    public List<Map<String,Object>> execute(String request) throws Exception {
        Operation operation = requestParser.parse(request);
        return switch (operation.getAction()) {
            case "SELECT"-> dataStorage.select(operation.getPredicate());
            case "INSERT"-> dataStorage.insert(operation.getValues());
            case "DELETE"-> dataStorage.delete(operation.getPredicate());
            case "UPDATE"-> dataStorage.update(operation.getValues(), operation.getPredicate());
            default -> throw new IllegalArgumentException("Wrong operation " + operation.getAction());
        };
    }

}
