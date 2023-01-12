package mk.ukim.finki.wp.university.repository.impl;

import mk.ukim.finki.wp.university.bootstrap.DataHolder;
import mk.ukim.finki.wp.university.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryTeacherRepository {

    public List<Teacher> findAll(){
        return DataHolder.teacherList;
    }

    public Optional<Teacher> findById(Long id){
        return DataHolder.teacherList.stream().filter(teacher -> teacher.getId().equals(id)).findFirst();
    }
}
