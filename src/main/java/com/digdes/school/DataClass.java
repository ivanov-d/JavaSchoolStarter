package com.digdes.school;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class DataClass {
    private final Long id;
    private final String lastName;
    private final Long age;
    private final Double cost;
    private final Boolean active;

    public DataClass(Long id, String lastName, Long age, Double cost, Boolean active) {
        this.id = id;
        this.lastName = lastName;
        this.age = age;
        this.cost = cost;
        this.active = active;
    }

    public Long id() {
        return id;
    }

    public String lastName() {
        return lastName;
    }

    public Long age() {
        return age;
    }

    public Double cost() {
        return cost;
    }

    public Boolean active() {
        return active;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (DataClass) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.lastName, that.lastName) &&
                Objects.equals(this.age, that.age) &&
                Objects.equals(this.cost, that.cost) &&
                Objects.equals(this.active, that.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, age, cost, active);
    }

    @Override
    public String toString() {
        return "DataClass[" +
                "id=" + id + ", " +
                "lastName=" + lastName + ", " +
                "age=" + age + ", " +
                "cost=" + cost + ", " +
                "active=" + active + ']';
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("lastName", lastName);
        result.put("age", age);
        result.put("cost", cost);
        result.put("active", active);
        return result;
    }
}
