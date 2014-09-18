/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uva.jobportal.dao;

import com.uva.jobportal.domain.Inscription;
import com.uva.jobportal.domain.Job;
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
public class JobDAOImpl implements JobDAO {
    @Override
    public List<Job> searchJobByAll(String text, String training, String experience, String languages) {
        try {
            List<Job> jobs;
            SessionFactory sf = new Configuration().configure().buildSessionFactory();
            Session s = sf.openSession();
            s.beginTransaction();
            
            Criterion criterionTitle = Restrictions.like("title", "%" + text + "%");
            Criterion criterionDescription = Restrictions.like("description", "%" + text + "%");
            Criterion criterionTitleOrDescription = Restrictions.or(criterionTitle,criterionDescription);
            Criterion criterionTraining = Restrictions.like("training", "%" + training + "%");
            Criterion criterionExperience = Restrictions.like("experience", "%" + experience + "%");
            Criterion criterionLanguages = Restrictions.like("languages", "%" + languages + "%");
            Criteria criteria = s.createCriteria(Job.class)
                    .add(Restrictions.and(criterionTitleOrDescription, criterionTraining,criterionExperience,criterionLanguages));
            jobs = (List<Job>) criteria.list();
            s.getTransaction().commit();
            s.close();
            sf.close();
            return jobs;
        } catch (HibernateException e) {
            return null;
        }
    }
    @Override
    public List<Job> searchJobByText(String text) {
        try {
            List<Job> jobs;
            SessionFactory sf = new Configuration().configure().buildSessionFactory();
            Session s = sf.openSession();
            s.beginTransaction();
            Criterion criterionTitle = Restrictions.like("title", "%" + text + "%");
            Criterion criterionLocation = Restrictions.like("location", "%" + text + "%");
            Criterion criterionDescription = Restrictions.like("description", "%" + text + "%");
            Criterion criterionTraining = Restrictions.like("training", "%" + text + "%");
            Criterion criterionExperience = Restrictions.like("experience", "%" + text + "%");
            Criterion criterionLanguages = Restrictions.like("languages", "%" + text + "%");
            Criteria criteria = s.createCriteria(Job.class)
                    .add(Restrictions.or(criterionTitle, criterionLocation, criterionDescription,criterionTraining,criterionExperience,criterionLanguages));
            
            jobs = (List<Job>) criteria.list();
            s.getTransaction().commit();
            s.close();
            sf.close();
            return jobs;
        } catch (HibernateException e) {
            return null;
        }
    }
    

    @Override
    public List<Job> listJobByCompanyId(int id) {
        try {
            List<Job> job = null;
            SessionFactory sf = new Configuration().configure().buildSessionFactory();
            Session s = sf.openSession();
            Criteria criteria = s.createCriteria(Job.class)
                    .add(Restrictions.eq("companyId", id));
            job = (List<Job>) criteria.list();
            s.close();
            sf.close();
            return job;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Job> listJobByInscription(List<Inscription> inscription) {
        try {
            List<Job> job = null;
            SessionFactory sf = new Configuration().configure().buildSessionFactory();
            Session s = sf.openSession();
            Criteria criteria = s.createCriteria(Job.class)
                    .add(Restrictions.eq("jobId", inscription.getClass()));
            job = (List<Job>) criteria.list();
            s.close();
            sf.close();
            return job;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Job> listJob() {

        try {
            List<Job> job;
            SessionFactory sf = new Configuration().configure().buildSessionFactory();
            Session s = sf.openSession();
            Criteria criteria = s.createCriteria(Job.class);
            job = (List<Job>) criteria.list();
            s.close();
            sf.close();
            return job;
        } catch (HibernateException e) {
            return null;
        }
    }

    @Override
    public Job findById(int id) {
        Job job = null;
        try {
            SessionFactory sf = new Configuration().configure().buildSessionFactory();
            Session s = sf.openSession();
            job = (Job) s.get(Job.class, id);
            s.close();
            sf.close();
        } catch (HibernateException e) {
        }
        return job;
    }

    @Override
    public void addJob(Job job) {
        try {
            SessionFactory sf = new Configuration().configure().buildSessionFactory();
            Session s = sf.openSession();
            s.beginTransaction();
            s.save(job);
            s.getTransaction().commit();
            s.close();
            sf.close();

        } catch (HibernateException e) {
        }
    }

    @Override
    public Job deleteJob(int id) {
        try {
            Job job;
            SessionFactory sf = new Configuration().configure().buildSessionFactory();
            Session s = sf.openSession();
            s.beginTransaction();
            job = (Job) s.get(Job.class, id);
            s.delete(job);
            s.getTransaction().commit();
            s.close();
            sf.close();
            return job;
        } catch (HibernateException e) {
            return null;
        }
    }

    @Override
    public Job editJob(Job job) {
        try {
            SessionFactory sf = new Configuration().configure().buildSessionFactory();
            Session s = sf.openSession();
            s.beginTransaction();
            s.update(job);
            s.getTransaction().commit();
            s.close();
            sf.close();
        } catch (HibernateException e) {
        }
        return job;
    }
}
