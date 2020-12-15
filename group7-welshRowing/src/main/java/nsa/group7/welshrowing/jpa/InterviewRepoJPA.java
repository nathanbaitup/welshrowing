package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Provides a set of methods using the JPA Repository to communicate to the database.
 */
public interface InterviewRepoJPA extends JpaRepository<Interview, Long> {






}
