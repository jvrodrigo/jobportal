/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uva.jobportal.web;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.uva.jobportal.dao.CompanyDAOImpl;
import com.uva.jobportal.dao.JobDAOImpl;
import com.uva.jobportal.domain.Company;
import com.uva.jobportal.domain.Job;
import java.security.MessageDigest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Administrator
 */
public class CompanyAction extends ActionSupport implements ModelDriven<Company> {

    private static final long serialVersionUID = 1L;
    String flashMessage;
    String name, passWord, passWordConf, cif, email;
    int id;
    Company company;
    private CompanyDAOImpl companyDAO;
    private List<Company> listCompany;
    //Job job;
    private JobDAOImpl jobDAO;
    private List<Job> listJob;

    public void validateAdd() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        if (request.getMethod().equals("POST")) {
            if (getName().length() == 0) {
                addFieldError("name", "Por favor, introduzca el nombre de la empresa");
            }
            if (getCif().length() == 0) {
                addFieldError("cif", "Por favor, introduzca el C.I.F. de la empresa");
            }
            if (getEmail().length() < 3 || !getEmail().contains("@")) {
                addFieldError("email", "Por favor, introduzca un email valido");
            }
            if (getPassWord().length() == 0) {
                addFieldError("passWord", "Por favor, introduzca una contraseña");
            }
            if (getPassWordConf().length() == 0) {
                addFieldError("passWordConf", "Por favor, repita la contraseña");
            }
            if (!getPassWordConf().equals(getPassWord())) {
                addFieldError("passWordConf", "Las contraseñas no coinciden");
                addFieldError("passWord", "Las contraseñas no coinciden");
            }
        }
    }

    public void validateEdit() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        if (request.getMethod().equals("POST")) {
            if (getEmail().length() < 3 || !getEmail().contains("@")) {
                addFieldError("email", "Por favor, introduzca un email valido");
            }
            if (getPassWord().length() == 0) {
                addFieldError("passWord", "Por favor, introduzca una contraseña");
            }
            if (!getPassWordConf().equals(getPassWord())) {
                addFieldError("passWordConf", "Las contraseñas no coinciden");
                addFieldError("passWord", "Las contraseñas no coinciden");
            }
            companyDAO = new CompanyDAOImpl();
            company = companyDAO.findById(getId());
        }
        
    }

    public boolean authCompany(int id) {
        Map session = ActionContext.getContext().getSession();
        try {
            return session.get("logged_in").equals(true)
                    && session.get("role").equals("company")
                    && session.get("id").equals(id);
        } catch (Exception e) {
            return false;
        }
    }

    public String edit() throws Exception {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        if (authCompany(Integer.parseInt(request.getParameter("id")))) {
            if (request.getMethod().equals("POST")) {
                companyDAO = new CompanyDAOImpl();
                company = companyDAO.findById(Integer.parseInt(request.getParameter("id")));
                company.setEmail(getEmail());
                company.setPassWord(MD5Hashing(getPassWord()));
                company.setModified(new Date());
                company = companyDAO.editCompany(company);
                setFlashMessage("Ok, la empresa " + company.getName() + " ha sido editada");
                return "post";
            } else {
                companyDAO = new CompanyDAOImpl();
                company = companyDAO.findById(Integer.parseInt(request.getParameter("id")));
                return "get";
            }
        } else {
            setFlashMessage("No tienes acceso a este contenido");
            return ERROR;
        }
    }

    public String delete() throws Exception {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        if (authCompany(Integer.parseInt(request.getParameter("id")))) {
            companyDAO = new CompanyDAOImpl();
            company = companyDAO.deleteCompany(Integer.parseInt(request.getParameter("id")));
            setFlashMessage("Ok, la empresa " + company.getName() + " ha sido eliminada");
            return SUCCESS;
        } else {
            setFlashMessage("No tienes acceso a este contenido");
            return ERROR;
        }
    }

    public String view() throws Exception {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        /*if (authCompany(Integer.parseInt(request.getParameter("id")))) {*/
        companyDAO = new CompanyDAOImpl();
        jobDAO = new JobDAOImpl();
        company = companyDAO.findById(Integer.parseInt(request.getParameter("id")));
        listJob = jobDAO.listJobByCompanyId(Integer.parseInt(request.getParameter("id")));
        return SUCCESS;
        /*} else {
         setFlashMessage("No tienes acceso a este contenido");
         return ERROR;
         }*/
    }

    public String add() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        if (request.getMethod().equals("POST")) {
            try {
                company = new Company();
                company.setName(getName());
                company.setPassWord(MD5Hashing(getPassWord()));
                company.setEmail(getEmail());
                company.setCif(getCif());
                company.setCreated(new Date());
                company.setModified(new Date());
                companyDAO = new CompanyDAOImpl();

                if (companyDAO.addCompany(company)) {
                    setFlashMessage("OK la empresa " + getName() + " ha sido guardada");
                    return "post";
                } else {
                    setFlashMessage("Error, la empresa no se ha guardado");
                    return ERROR;
                }
            } catch (Exception ex) {
                setFlashMessage("Error, la empresa no se ha guardado");
                return ERROR;
            }
        } else {
            return "get";
        }
    }

    public String list() throws Exception {

        companyDAO = new CompanyDAOImpl();
        listCompany = companyDAO.listCompany();
        setListCompany(listCompany);
        return SUCCESS;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public List<Job> getListJob() {
        return this.listJob;
    }

    public void setListJob(List<Job> listJob) {
        this.listJob = listJob;
    }

    public List<Company> getListCompany() {
        return this.listCompany;
    }

    public void setListCompany(List<Company> listCompany) {
        this.listCompany = listCompany;
    }

    public String getFlashMessage() {
        return flashMessage;
    }

    public void setFlashMessage(String flashMessage) {
        this.flashMessage = flashMessage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPassWordConf() {
        return passWordConf;
    }

    public void setPassWordConf(String passWordConf) {
        this.passWordConf = passWordConf;
    }

    public String getCif() {
        return cif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    @Override
    public Company getModel() {
        return company;
    }
        private String MD5Hashing(String passWord) throws Exception {
    	//String password = "123456";

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(passWord.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        /*StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        System.out.println("Digest(in hex format):: " + sb.toString());*/

        //convert the byte to hex format method 2
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        //System.out.println("Digest(in hex format):: " + hexString.toString());
        return hexString.toString();
    }
}
