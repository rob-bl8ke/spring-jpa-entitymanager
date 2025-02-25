package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    // define field for entity manager
    private EntityManager entityManager;

    // inject manager using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // implement save method
    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    // Note "Transactional" is not required here since this is a read
    // operation.
    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        // Note that most of the query string is not case-sensitive (except for the entity name and entity fields).
        // asc is the default (could choose desc)
        // TypedQuery<Student> theQuery = entityManager.createQuery("from Student order by lastName asc", Student.class);
        TypedQuery<Student> theQuery = entityManager.createQuery("from Student", Student.class);

        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student WHERE lastName=:lastName", Student.class);
        query.setParameter("lastName", lastName);

        return query.getResultList();
    }

    @Transactional
    @Override
    public void update(Student student) {
        entityManager.merge(student);
    }
}
