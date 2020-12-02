package nsa.group7.welshrowing.jpa;
import nsa.group7.welshrowing.domain.SessionRPE;
import nsa.group7.welshrowing.domain.SessionRPEAuditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class SessionRPEJPAAdaptor implements SessionRPEAuditor {

    private final SessionRPERepoJPA sessionRPERepoJPA;

    /**
     * Injects the SessionRPERepoJPA interface to communicate with the database.
     *
     * @param aSessionRPERepoJPA - the variable depending on the SessionRPE repository to access the database.
     */
    @Autowired
    public SessionRPEJPAAdaptor(SessionRPERepoJPA aSessionRPERepoJPA) {
        sessionRPERepoJPA = aSessionRPERepoJPA;
    }

    /**
     * Uses the sessionRPE auditor interface to access the JPA repository and automatically saves the session object within the database.
     *
     * @param aSession - the athlete object to save
     */
    @Override
    public void saveSession(SessionRPE aSession) {
        sessionRPERepoJPA.save(aSession);
    }

    /**
     * Finds a sessionRPE stored in the database by its id.
     *
     * @param id - the id of the session object.
     * @return returns the sessionRPE by the id searched for.
     */
    @Override
    public Optional<SessionRPE> findBySessionRPEID(Long id) {
        return sessionRPERepoJPA.findById(id);
    }
}
