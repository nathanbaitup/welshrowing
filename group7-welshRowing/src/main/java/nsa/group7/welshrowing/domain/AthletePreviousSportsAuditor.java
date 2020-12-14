package nsa.group7.welshrowing.domain;

/**
 * Provides a set of methods for managing Athlete Previous Sports objects.
 */
public interface AthletePreviousSportsAuditor {
    /**
     * Method that saves all information of an athletesPreviousSports object.
     *
     * @param athletePreviousSports - the athletePreviousSports object to save.
     */
    void savePreviousSportsData(AthletePreviousSports athletePreviousSports);
}
