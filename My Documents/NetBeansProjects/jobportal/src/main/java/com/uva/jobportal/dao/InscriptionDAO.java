/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uva.jobportal.dao;

import com.uva.jobportal.domain.Inscription;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface InscriptionDAO {
    public Inscription findById(int id);
    public boolean updateInscriptionStatus(Inscription inscription);
    public void addInscription(Inscription inscription);
    public List<Inscription> listInscriptionByCandidateId(int id);
    public List<Inscription> listInscriptionByJobId (int id);
    public boolean findByCandidateIdAndJobId(int candidateId, int jobId);
}
