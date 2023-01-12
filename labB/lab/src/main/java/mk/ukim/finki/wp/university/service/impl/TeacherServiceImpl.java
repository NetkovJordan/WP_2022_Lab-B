package mk.ukim.finki.wp.university.service.impl;

import mk.ukim.finki.wp.university.model.Teacher;
import mk.ukim.finki.wp.university.repository.jpa.TeacherRepository;
import mk.ukim.finki.wp.university.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> findAll() {
        return this.teacherRepository.findAll();
    }
    @Override
    public Teacher save(String name, String surname) {
        return teacherRepository.save(new Teacher(name, surname));
    }
}
