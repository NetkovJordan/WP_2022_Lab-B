package mk.ukim.finki.wp.university.service;

import mk.ukim.finki.wp.university.model.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> findAll();
    Teacher save(String name, String surname);
}
