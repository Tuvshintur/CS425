package mum.edu.cs.cs425.etdm.service.impl;

import mum.edu.cs.cs425.etdm.model.Athlete;
import mum.edu.cs.cs425.etdm.repository.AthleteRepository;
import mum.edu.cs.cs425.etdm.service.AthleteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AthleteServiceImpl implements AthleteService {

    private AthleteRepository athleteRepository;

    public AthleteServiceImpl(AthleteRepository athleteRepository) {
        this.athleteRepository = athleteRepository;
    }

    @Override
    public Iterable<Athlete> getAllAthletes() {
        return athleteRepository.findAll(Sort.by("fullName"));
    }

    @Override
    public Page<Athlete> getAllAthletesPaged(int pageNo) {
        return athleteRepository.findAll(PageRequest.of(pageNo, 3, Sort.by("fullName")));
    }

    @Override
    public Athlete saveAthlete(Athlete athlete) {
        return athleteRepository.save(athlete);
    }

    @Override
    public Page<Athlete> getAthletesByFullName(int pageNo, String searchString) {
        return athleteRepository.findAthletesByFullNameStartsWith(searchString, PageRequest.of(pageNo, 3,Sort.by("fullName")));
    }

    @Override
    public Optional<Athlete> getAthleteBySSN(String ssnNumber) {
        return athleteRepository.findAthleteBySsn(ssnNumber);
    }

    @Override
    public void deleteAthleteById(long athleteId) {
        athleteRepository.deleteById(athleteId);
    }

    @Override
    public Page<Athlete> getAllEliteAthletesPaged(int pageNo) {
        return athleteRepository.findAthletesByDateOfRegistrationBeforeAndTotalNumberOfMedalsWonGreaterThan(LocalDate.now().minusYears(5), 3, PageRequest.of(pageNo, 3, Sort.by(Sort.Direction.DESC, "monthlySalary" )));
    }

    @Override
    public int countEliteAthletes() {
        return athleteRepository.countAthletesByDateOfRegistrationBeforeAndTotalNumberOfMedalsWonGreaterThan(LocalDate.now().minusYears(5), 3, Sort.by(Sort.Direction.DESC, "monthlySalary" ));
    }
}
