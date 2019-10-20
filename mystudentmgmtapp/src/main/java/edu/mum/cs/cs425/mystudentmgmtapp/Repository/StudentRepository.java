package edu.mum.cs.cs425.mystudentmgmtapp.Repository;

import edu.mum.cs.cs425.mystudentmgmtapp.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  StudentRepository extends CrudRepository<Student, Long> {

}
