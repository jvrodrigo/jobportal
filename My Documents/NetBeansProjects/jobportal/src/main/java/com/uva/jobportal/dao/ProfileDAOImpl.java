/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uva.jobportal.dao;

import com.uva.jobportal.domain.Profile;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Administrator
 */
public class ProfileDAOImpl implements ProfileDAO {

    @Override
    public List<Profile> searchProfileByText(String text) {
        List<Profile> profiles;
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        Criterion criterionCurriculum = Restrictions.like("curriculum", "%"+text+"%");
        Criterion criterionTraining = Restrictions.like("training", "%" + text + "%");
        Criterion criterionExperience = Restrictions.like("experience", "%" + text + "%");
        Criterion criterionLanguages = Restrictions.like("languages", "%" + text + "%");
        Criteria criteria = s.createCriteria(Profile.class)
                .add(Restrictions.or(criterionCurriculum,criterionTraining, criterionExperience, criterionLanguages));
        profiles = (List<Profile>) criteria.list();
        s.getTransaction().commit();
        s.close();
        sf.close();
        return profiles;
    }

    @Override
    public Profile viewProfile(int id) {
        Profile profile = null;
        try {
            SessionFactory sf = new Configuration().configure().buildSessionFactory();
            Session s = sf.openSession();
            profile = (Profile) s.get(Profile.class, id);
            s.getTransaction().commit();
            s.close();
            sf.close();
        } catch (HibernateException e) {
        }
        return profile;
    }

    @Override
    public Profile editProfile(Profile profile) {
        try {
            SessionFactory sf = new Configuration().configure().buildSessionFactory();
            Session s = sf.openSession();
            s.beginTransaction();
            s.update(profile);
            s.getTransaction().commit();
            s.close();
            sf.close();
        } catch (HibernateException e) {
        }
        return profile;
    }

}
