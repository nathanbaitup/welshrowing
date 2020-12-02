package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.SessionRPE;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRPERepoJPA extends JpaRepository<SessionRPE, Long> {
    SessionRPE findBySessionRPEID(Long id);

}
