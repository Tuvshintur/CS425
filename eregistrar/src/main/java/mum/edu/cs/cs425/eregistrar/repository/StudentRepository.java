package mum.edu.cs.cs425.eregistrar.repository;

import mum.edu.cs.cs425.eregistrar.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    // This interface definition relies on the public abstract methods
    // inherited from the super interface, CrudRepository<T, ID>
    // We may override any or add more methods here, if needed.
    Optional<Student> findStudentByStudentNumber(String studentNumber);
}
