package com.example.healthhub.repo;
import com.example.healthhub.model.Person;
import java.util.ArrayList;
import java.util.List;


public class PersonRepo {
    private static final List<Person> personList = new ArrayList<>();


    public static void save(Person p) {
        personList.add(p);
    }


    public static List<Person> getAll() {
        return personList;
    }


    public static int count() {
        return personList.size();
    }


    public static Person findByIndex(int index) {
        return (index >= 0 && index < personList.size()) ? personList.get(index) : null;
    }


    public static boolean deleteByIndex(int index) {
        if (index >= 0 && index < personList.size()) {
            personList.remove(index);
            return true;
        }
        return false;
    }
}

