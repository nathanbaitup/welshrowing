package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.Anthropometry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnthropometryRepo extends JpaRepository<Anthropometry, Long> {
}
