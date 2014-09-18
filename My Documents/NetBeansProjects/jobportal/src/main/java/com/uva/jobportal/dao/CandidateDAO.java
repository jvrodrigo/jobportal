package com.uva.jobportal.dao;

import com.uva.jobportal.domain.Candidate;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface CandidateDAO {
    public Candidate findById(int id);
    public List <Candidate> listCandidate();
    public boolean addCandidate(Candidate candidate);
    public Candidate deleteCandidate(int id);
    public Candidate editCandidate(Candidate candidate); 
    public boolean existCandidateByUserName(String string);
}
