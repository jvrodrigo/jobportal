/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uva.jobportal.web;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.uva.jobportal.dao.InscriptionDAOImpl;
import com.uva.jobportal.domain.Inscription;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Administrator
 */
public class InscriptionAction extends ActionSupport implements ModelDriven<Inscription> {

    private static final long serialVersionUID = 1L;
    String flashMessage,url;// candidateName, jobTitle;
    int candidateId, jobId;
    Inscription inscription;
    InscriptionDAOImpl inscriptionDAO;
    private List<Inscription> listInscription;

    public String add() throws Exception {
        inscription = new Inscription();
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        inscriptionDAO = new InscriptionDAOImpl();
        inscription.setCandidateId(Integer.parseInt(request.getParameter("candidateId")));
        inscription.setCandidateName(request.getParameter("candidateName"));
        inscription.setJobId(Integer.parseInt(request.getParameter("jobId")));
        inscription.setJobTitle(request.getParameter("jobTitle"));
        inscription.setCreated(new Date());
        inscription.setModified(new Date());
        inscription.setStatus("PENDIENTE");
        inscriptionDAO.addInscription(inscription);
        setFlashMessage("OK te has inscrito correctamente a la oferta de trabajo");
        return SUCCESS;
    }

    public String listByCandidateId() throws Exception {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        inscriptionDAO = new InscriptionDAOImpl();
        listInscription = inscriptionDAO.listInscriptionByCandidateId(Integer.parseInt(request.getParameter("id")));
        setListInscription(listInscription);
        return SUCCESS;
    }

    public String listByJobId() throws Exception {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        inscriptionDAO = new InscriptionDAOImpl();
        listInscription = inscriptionDAO.listInscriptionByJobId(Integer.parseInt(request.getParameter("id")));
        setListInscription(listInscription);
        return SUCCESS;
    }

    public String updateInscription() throws Exception {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        if (request.getMethod().equals("POST")) {
            inscriptionDAO = new InscriptionDAOImpl();
            inscription = inscriptionDAO.findById(Integer.parseInt(request.getParameter("id")));
            inscription.setStatus(request.getParameter("status"));
            if (inscriptionDAO.updateInscriptionStatus(inscription)) {
                url = Integer.toString(inscription.getJobId());
                setFlashMessage("OK has " + inscription.getStatus() + " al candidato " + inscription.getCandidateName() + " del trabajo " + inscription.getJobTitle());
                return SUCCESS;
            } else {
                return ERROR;
            }
        }
        return ERROR;
    }

    public List<Inscription> getListInscription() {
        return listInscription;
    }

    public void setListInscription(List<Inscription> listInscription) {
        this.listInscription = listInscription;
    }
    public String getUrl(){
        return url;
    }
    public String getFlashMessage() {
        return flashMessage;
    }

    public void setFlashMessage(String flashMessage) {
        this.flashMessage = flashMessage;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }
    /*
     public String getCandidateName(){
     return candidateName;
     }
     public String getJobTitle(){
     return jobTitle;
     }*/

    @Override
    public Inscription getModel() {
        return inscription;
    }
}
