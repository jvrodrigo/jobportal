/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uva.jobportal.dao;

import com.uva.jobportal.domain.Company;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface CompanyDAO {

    public Company findById(int id);

    public List<Company> listCompany();

    public boolean addCompany(Company company);

    public Company deleteCompany(int id);

    public Company editCompany(Company company);
}
