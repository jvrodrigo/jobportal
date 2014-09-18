/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uva.jobportal.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.uva.jobportal.dao.JobDAOImpl;
import com.uva.jobportal.dao.ProfileDAOImpl;
import com.uva.jobportal.domain.Job;
import com.uva.jobportal.domain.Profile;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Administrator
 */
public class SearchAction extends ActionSupport {
    
    Profile profile;
    String seachField, flashMessage, training, experience, languages;
    List<Profile> listProfile;
    ProfileDAOImpl profileDAO;
    List<Job> listJob;
    JobDAOImpl jobDAO;
    public List<String> listTraining, listExperience, listLanguages;
    
    /**
     * 
     */
    public SearchAction() {
        listTraining = new ArrayList<>();
        listExperience = new ArrayList<>();
        listLanguages = new ArrayList<>();
        listTraining.add("");
        listTraining.add("Bachillerato");
        listTraining.add("Grado Medio");
        listTraining.add("Grado Superior");
        listTraining.add("Licenciado");
        listExperience.add("");
        listExperience.add("Sin experiencia");
        listExperience.add("Menos de un año");
        listExperience.add("1 año");
        listExperience.add("2 años");
        listExperience.add("3 años o más");
        listLanguages.add("Castellano");
        listLanguages.add("Inglés");
        listLanguages.add("Francés");
        listLanguages.add("Alemán");
    }
    
    public String searchProfileByText() throws Exception {
        
        profileDAO = new ProfileDAOImpl();
        setListProfile(profileDAO.searchProfileByText(getSearchField()));
        if (getListProfile().isEmpty()) {
            setFlashMessage("Se han encontrado " + getListProfile().size() + " candidatos");
            return ERROR;
        } else {
            setFlashMessage("Se han encontrado " + getListProfile().size() + " candidatos con este perfil: " + getSearchField());
            return SUCCESS;
        }
    }
        public void validateSearchProfileByText() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        if (request.getMethod().equals("POST")) {
            if (getSearchField().length() == 0) {
                addFieldError("searchField", "Introduce alguna palabra para buscar");
            }
        }
    }
    public void validateSearchJobByText() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        if (request.getMethod().equals("POST")) {
            if (getSearchField().length() == 0) {
                addFieldError("searchField", "Introduce alguna palabra para buscar");
            }
        }
    }
    
    public String searchJobByText() throws Exception {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        if (request.getMethod().equals("POST")) {
            try {
                jobDAO = new JobDAOImpl();
                setListJob(jobDAO.searchJobByText(getSearchField()));
                setFlashMessage("Se han encontrado " + getListJob().size() + " trabajos con la palabra " + getSearchField());
                return SUCCESS;
            } catch (Exception e) {
                setFlashMessage("Opppsss, ha ocurrido un error");
                return ERROR;
            }
        } else {
            return "get";
        }
    }
    
    public String avancedSearchJob() throws Exception {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        if (request.getMethod().equals("POST")) {
            jobDAO = new JobDAOImpl();
            setListJob(jobDAO.searchJobByAll(getSearchField(), getTraining(), getExperience(), getLanguages()));
            setFlashMessage("Se han encontrado " + getListJob().size() + " trabajos con la palabra " + getSearchField()
                    + " Formación: " + getTraining() + " Experiencia: " + getExperience());
            return SUCCESS;
        } else {
            return "get";
        }
    }
    
    public String getFlashMessage() {
        return flashMessage;
    }
    
    public void setFlashMessage(String flashMessage) {
        this.flashMessage = flashMessage;
    }
    
    public List<Profile> getListProfile() {
        return listProfile;
    }
    
    public void setListProfile(List<Profile> listProfile) {
        this.listProfile = listProfile;
    }
    
    public List<Job> getListJob() {
        return listJob;
    }
    
    public void setListJob(List<Job> listJob) {
        this.listJob = listJob;
    }
    
    public String getSearchField() {
        return seachField;
    }
    
    public void setSearchField(String searchField) {
        this.seachField = searchField;
    }
    
    public String getTraining() {
        return training;
    }
    
    public void setTraining(String training) {
        this.training = training;
    }
    
    public String getExperience() {
        return experience;
    }
    
    public void setExperience(String experience) {
        this.experience = experience;
    }
    
    public String getLanguages() {
        return languages;
    }
    
    public void setLanguages(String languages) {
        this.languages = languages;
    }
    
    public List<String> getListTraining() {
        return listTraining;
    }
    
    public void setListTraining(List<String> listTraining) {
        this.listTraining = listTraining;
    }
    
    public List<String> getListExperience() {
        return listExperience;
    }
    
    public void setListExperience(List<String> listExperience) {
        this.listExperience = listExperience;
    }
    
    public List<String> getListLanguages() {
        return listLanguages;
    }
    
    public void setListLanguages(List<String> listLanguages) {
        this.listLanguages = listLanguages;
    }
}
