package nsa.group7.welshrowing.domain;

public interface AnthropometryAuditor {
    /**
     * Method that saves all information of an anthropometric object.
     *
     * @param anthropometry - the anthropometry object to save.
     */
    void saveAnthropometricData(Anthropometry anthropometry);
}
