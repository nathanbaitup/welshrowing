package nsa.group7.welshrowing.domain;

import java.util.Optional;

/**
 * Provides a set of methods for managing and retrieving RPE Session Workouts objects.
 */
public interface SessionRPEAuditor {
    /**
     * Method that saves all information of an session object.
     *
     * @param aSession - the session object to save
     */
    public void saveSession(SessionRPE aSession);

    /**
     * Finds a sessionRPE stored in the database by its id.
     *
     * @param id - the id of the session object.
     * @return returns the sessionRPE by the id searched for.
     */
    Optional<SessionRPE> findBySessionRPEID(Long id);
}
