/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uva.jobportal.dao;

import com.uva.jobportal.domain.Candidate;
import com.uva.jobportal.domain.Company;
import org.apache.struts2.components.ElseIf;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Administrator
 */
public class LoginDAOImpl implements LoginDAO {

    @Override
    public Candidate findCandidate(String userName, String passWord) {
        Candidate candidate;
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Criteria criteria = s.createCriteria(Candidate.class)
                .add(Restrictions.eq("userName", userName))
                .add(Restrictions.eq("passWord", passWord));
        candidate = (Candidate) criteria.uniqueResult();
        s.close();
        sf.close();
        return candidate;
    }

    @Override
    public Company findCompany(String cif, String passWord) {
        Company company;
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Criteria criteria = s.createCriteria(Company.class)
                .add(Restrictions.eq("cif", cif))
                .add(Restrictions.eq("passWord", passWord));
        company = (Company) criteria.uniqueResult();
        s.close();
        sf.close();
        return company;
    }

    @Override
    public void updateLastLogin(Candidate candidate, Company company) {
        if (company == null) {
            try {
                SessionFactory sf = new Configuration().configure().buildSessionFactory();
                Session s = sf.openSession();
                s.beginTransaction();
                s.update(candidate);
                s.getTransaction().commit();
                s.close();
                sf.close();
            } catch (HibernateException e) {
            }
        }
        if (candidate == null) {
            try {
                SessionFactory sf = new Configuration().configure().buildSessionFactory();
                Session s = sf.openSession();
                s.beginTransaction();
                s.update(company);
                s.getTransaction().commit();
                s.close();
                sf.close();
            } catch (HibernateException e) {
            }
        }
    }
}
