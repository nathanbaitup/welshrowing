package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.SessionRPE;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRPERepoJPA extends JpaRepository<SessionRPE, Long> {
    /**
     * Finds a sessionRPE stored in the database by its id.
     *
     * @param id - the id of the session object.
     * @return returns the sessionRPE by the id searched for.
     */
    SessionRPE findBySessionRPEID(Long id);

}
