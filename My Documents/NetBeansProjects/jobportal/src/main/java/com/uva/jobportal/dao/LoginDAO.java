/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uva.jobportal.dao;

import com.uva.jobportal.domain.Candidate;
import com.uva.jobportal.domain.Company;

/**
 *
 * @author Administrator
 */
public interface LoginDAO {
    public Candidate findCandidate(String userName, String passWord);
    public Company findCompany(String cif, String passWord);
    public void updateLastLogin(Candidate candidate, Company company);
}
