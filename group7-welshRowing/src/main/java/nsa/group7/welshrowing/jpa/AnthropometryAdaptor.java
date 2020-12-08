package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.Anthropometry;
import nsa.group7.welshrowing.domain.AnthropometryAuditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnthropometryAdaptor implements AnthropometryAuditor {

    private final AnthropometryRepo anthropometryRepo;

    @Autowired
    public AnthropometryAdaptor(AnthropometryRepo anthropometryRepo){
        this.anthropometryRepo = anthropometryRepo;
    }

    @Override
    public void saveAnthropometricData(Anthropometry anthropometry) {
        anthropometryRepo.save(anthropometry);
    }
}
