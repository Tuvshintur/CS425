package mum.edu.cs.cs425.etdm.service;

import mum.edu.cs.cs425.etdm.model.Athlete;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface AthleteService {
    public abstract Iterable<Athlete> getAllAthletes();
    public abstract Page<Athlete> getAllAthletesPaged(int pageNo);
    public abstract Athlete saveAthlete(Athlete athlete);
    public abstract Page<Athlete> getAthletesByFullName(int pageNo, String searchString);
    public abstract Optional<Athlete> getAthleteBySSN(String ssnNumber);
    public abstract void deleteAthleteById(long athleteId);
    public abstract Page<Athlete> getAllEliteAthletesPaged(int pageNo);
    public abstract int countEliteAthletes();
}
