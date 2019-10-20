package mum.edu.cs.cs425.etdm.repository;

import mum.edu.cs.cs425.etdm.model.Athlete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete, Long> {

    Page<Athlete> findAthletesByFullNameStartsWith(String searchString, Pageable pageable);

    Optional<Athlete> findAthleteBySsn(String ssn);

    Page<Athlete> findAthletesByDateOfRegistrationBeforeAndTotalNumberOfMedalsWonGreaterThan(LocalDate date, int year, Pageable pageable);

    int countAthletesByDateOfRegistrationBeforeAndTotalNumberOfMedalsWonGreaterThan(LocalDate date, int year, Sort sort);
}
