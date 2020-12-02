package nsa.group7.welshrowing.domain;

import java.util.Optional;

public interface SessionRPEAuditor {
    /**
     * Method that saves all information of an session object.
     *
     * @param aSession - the session object to save
     */
    public void saveSession(SessionRPE aSession);

    Optional<SessionRPE> findBySessionRPEID(Long id);
}
