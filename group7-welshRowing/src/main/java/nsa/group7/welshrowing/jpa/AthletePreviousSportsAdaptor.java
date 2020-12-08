package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.AthletePreviousSportsAuditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AthletePreviousSportsAdaptor implements AthletePreviousSportsAuditor {

    private final AthletePreviousSportsRepo athletePreviousSportsRepo;

    @Autowired
    public AthletePreviousSportsAdaptor(AthletePreviousSportsRepo athletePreviousSportsRepo){
        this.athletePreviousSportsRepo = athletePreviousSportsRepo;
    }
}
