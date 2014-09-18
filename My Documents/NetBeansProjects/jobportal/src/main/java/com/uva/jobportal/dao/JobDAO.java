/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uva.jobportal.dao;

import com.uva.jobportal.domain.Inscription;
import com.uva.jobportal.domain.Job;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface JobDAO {

    public List<Job> searchJobByText(String text);

    public List<Job> searchJobByAll(String text, String training, String experience, String languages);

    public List<Job> listJobByInscription(List<Inscription> inscription);

    public Job findById(int id);

    public List<Job> listJobByCompanyId(int id);

    public List<Job> listJob();

    public void addJob(Job job);

    public Job deleteJob(int id);

    public Job editJob(Job job);
}
