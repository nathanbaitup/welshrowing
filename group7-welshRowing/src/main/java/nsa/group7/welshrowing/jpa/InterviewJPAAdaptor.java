package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.Interview;
import nsa.group7.welshrowing.domain.InterviewAuditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InterviewJPAAdaptor implements InterviewAuditor {

    private final InterviewRepoJPA interviewRepoJPA;

    @Autowired
    public InterviewJPAAdaptor(InterviewRepoJPA anInterviewRepoJPA){ interviewRepoJPA = anInterviewRepoJPA; }


    /**
     * Uses the interview auditor interface to access the JPA repository and automatically saves the interview object within the database.
     *
     * @param anInterview - the interview object to save
     */
    @Override
    public void saveInterview(Interview anInterview) {
        interviewRepoJPA.save(anInterview);
    }
}



