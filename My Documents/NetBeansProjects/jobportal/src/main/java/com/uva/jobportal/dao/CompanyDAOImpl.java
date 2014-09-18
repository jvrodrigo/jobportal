/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uva.jobportal.dao;

import com.uva.jobportal.domain.Company;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CompanyDAOImpl implements CompanyDAO {

    @Override
    public Company findById(int id) {
        Company company = null;
        try {
            SessionFactory sf = new Configuration().configure().buildSessionFactory();
            Session s = sf.openSession();
            company = (Company) s.get(Company.class, id);
            s.close();
            sf.close();
        } catch (HibernateException e) {
        }
        return company;
    }

    @Override
    public List<Company> listCompany() {

        try {
            List<Company> listCompany;
            SessionFactory sf = new Configuration().configure().buildSessionFactory();
            Session s = sf.openSession();
            Criteria criteria = s.createCriteria(Company.class);
            listCompany = (List<Company>) criteria.list();
            s.close();
            sf.close();
            return listCompany;
        } catch (HibernateException e) {
            return null;
        }
    }

    @Override
    public boolean addCompany(Company company) {
        try {
            SessionFactory sf = new Configuration().configure().buildSessionFactory();
            Session s = sf.openSession();
            s.beginTransaction();
            s.save(company);
            s.getTransaction().commit();
            s.close();
            sf.close();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public Company deleteCompany(int id) {
        try {
            Company company;
            SessionFactory sf = new Configuration().configure().buildSessionFactory();
            Session s = sf.openSession();
            s.beginTransaction();
            company = (Company) s.get(Company.class, id);
            s.delete(company);
            s.getTransaction().commit();
            s.close();
            sf.close();
            return company;
        } catch (HibernateException e) {
            return null;
        }
    }

    @Override
    public Company editCompany(Company company) {
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
        return company;
    }
}
