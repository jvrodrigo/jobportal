/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uva.jobportal.dao;

import com.uva.jobportal.domain.Candidate;
import com.uva.jobportal.domain.Profile;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CandidateDAOImpl implements CandidateDAO {

    @Override
    public Candidate editCandidate(Candidate candidate) {

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
        return candidate;
    }

    @Override
    public Candidate deleteCandidate(int id) {
        Candidate candidate = null;
        try {
            SessionFactory sf = new Configuration().configure().buildSessionFactory();
            Session s = sf.openSession();
            s.beginTransaction();
            candidate = (Candidate) s.get(Candidate.class, id);
            s.delete(candidate);
            s.getTransaction().commit();
            s.close();
            sf.close();
        } catch (HibernateException e) {
        }
        return candidate;
    }

    @Override
    public List<Candidate> listCandidate() {
        try {
            List<Candidate> candidates;
            SessionFactory sf = new Configuration().configure().buildSessionFactory();
            Session s = sf.openSession();
            Criteria criteria = s.createCriteria(Candidate.class);
            candidates = (List<Candidate>) criteria.list();
            s.close();
            sf.close();
            return candidates;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addCandidate(Candidate candidate) {
        try {
            SessionFactory sf = new Configuration().configure().buildSessionFactory();
            Session s = sf.openSession();
            Profile profile = new Profile();
            profile.setCreated(new Date());
            profile.setModified(new Date());
            s.beginTransaction();
            s.save(candidate);
            profile.setCandidateId(candidate.getId());// Get candidate id saved
            s.save(profile);
            s.getTransaction().commit();
            s.close();
            sf.close();
            return true;

        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public Candidate findById(int id) {
        Candidate candidate = null;
        try {
            SessionFactory sf = new Configuration().configure().buildSessionFactory();
            Session s = sf.openSession();
            candidate = (Candidate) s.get(Candidate.class, id);
            s.close();
            sf.close();
        } catch (HibernateException e) {
        }
        return candidate;
    }

    @Override
    public boolean existCandidateByUserName(String string) {
        try {
            Candidate candidate;
            SessionFactory sf = new Configuration().configure().buildSessionFactory();
            Session s = sf.openSession();
            candidate = (Candidate) s.get(Candidate.class, string);
            s.close();
            sf.close();
            if (candidate == null) {
                return true;
            } else {
                return false;
            }
        } catch (HibernateException e) {
            return false;
        }
    }

}
