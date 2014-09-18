/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uva.jobportal.dao;

import com.uva.jobportal.domain.Inscription;
import java.util.List;
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
public class InscriptionDAOImpl implements InscriptionDAO {
    @Override
    public Inscription findById(int id){
        Inscription inscription = null;
        try {
            SessionFactory sf = new Configuration().configure().buildSessionFactory();
            Session s = sf.openSession();
            inscription = (Inscription) s.get(Inscription.class, id);
            s.close();
            sf.close();
        } catch (HibernateException e) {
        }
        return inscription;
    }
    
    @Override
    public boolean updateInscriptionStatus(Inscription inscription) {

        try {
            SessionFactory sf = new Configuration().configure().buildSessionFactory();
            Session s = sf.openSession();
            s.beginTransaction();
            s.update(inscription);
            s.getTransaction().commit();
            s.close();
            sf.close();
        } catch (HibernateException e) {
            return false;
        }
        return true;
    }
    @Override
    public void addInscription(Inscription inscription) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        s.save(inscription);
        s.getTransaction().commit();
        s.close();
        sf.close();
    }

    @Override
    public List<Inscription> listInscriptionByCandidateId(int id) {
        List<Inscription> inscription;
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Criteria criteria = s.createCriteria(Inscription.class)
                .add(Restrictions.eq("candidateId", id));
        inscription = (List<Inscription>) criteria.list();
        s.close();
        sf.close();
        return inscription;
    }
    @Override
    public List<Inscription> listInscriptionByJobId(int id){
        List<Inscription> inscription;
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Criteria criteria = s.createCriteria(Inscription.class)
                .add(Restrictions.eq("jobId", id));
        inscription = (List<Inscription>) criteria.list();
        s.close();
        sf.close();
        return inscription;
    }
    @Override
    public boolean findByCandidateIdAndJobId(int candidateId, int jobId) {
        try {
            Inscription inscription;
            SessionFactory sf = new Configuration().configure().buildSessionFactory();
            Session s = sf.openSession();
            s.beginTransaction();
            Criteria criteria = s.createCriteria(Inscription.class)
                    .add(Restrictions.eq("candidateId", candidateId))
                    .add(Restrictions.eq("jobId", jobId));
            inscription = (Inscription) criteria.uniqueResult();
            s.getTransaction().commit();
            s.close();
            sf.close();
            if (inscription.getId() == null) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }
}