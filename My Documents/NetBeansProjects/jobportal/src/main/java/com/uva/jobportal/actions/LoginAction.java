/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uva.jobportal.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.uva.jobportal.dao.LoginDAOImpl;
import com.uva.jobportal.domain.Candidate;
import com.uva.jobportal.domain.Company;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class LoginAction extends ActionSupport {

    private String flashMessage, candidatePassWord, companyPassWord, userName, cif;
    Candidate candidate;
    Company company;

    public void validateLogInCandidate() {
        if (getUserName().length() == 0) {
            addFieldError("userName", "Por favor, introduzca el nombre de usuario");
        }
        if (getCandidatePassWord().length() == 0) {
            addFieldError("candidatePassWord", "Por favor, introduzca la contraseña");
        }
    }

    public void validateLogInCompany() {
        if (getCif().length() == 0) {
            addFieldError("cif", "Por favor, introduzca el C.I.F.");
        }
        if (getCompanyPassWord().length() == 0) {
            addFieldError("companyPassWord", "Por favor, introduzca la contraseña");
        }
    }

    public String logInCandidate() throws Exception {
        LoginDAOImpl loginDAO = new LoginDAOImpl();
        Map session = ActionContext.getContext().getSession();
        candidate = loginDAO.findCandidate(getUserName(), MD5Hashing(getCandidatePassWord()));
        if (candidate == null) {
            addFieldError("userName", "Nombre de usuario o contraseña no valida");
            addFieldError("candidatePassWord", "Por favor introduzca los datos de nuevo");
            setFlashMessage("Error login");
            setUserName(getUserName()); // Rellenamos el campo userName
            return ERROR;
        } else {
            setFlashMessage("Bienvenido " + candidate.getUserName());
            session.put("context", new Date());
            session.put("logged_in", true);
            session.put("role", "candidate");
            session.put("id", candidate.getId());
            session.put("userName", candidate.getUserName());
            candidate.setLastLogin(new Date());
            loginDAO.updateLastLogin(candidate, null);
            return SUCCESS;
        }
    }

    public String logInCompany() throws Exception {
        LoginDAOImpl loginDAO = new LoginDAOImpl();
        Map session = ActionContext.getContext().getSession();
        company = (Company) loginDAO.findCompany(getCif(), MD5Hashing(getCompanyPassWord()));
        if (company == null) {
            addFieldError("cif", "C.I.F. o contraseña no valida");
            addFieldError("companyPassWord", "Por favor introduzca los datos de nuevo");
            setFlashMessage("Error login");
            return ERROR;
        } else {
            setFlashMessage("Bienvenido " + company.getName());
            session.put("context", new Date());
            session.put("logged_in", true);
            session.put("role", "company");
            session.put("id", company.getId());
            session.put("userName", company.getName());
            company.setLastLogin(new Date());
            loginDAO.updateLastLogin(null, company);
            return SUCCESS;
        }
    }

    public String logOut() throws Exception {
        Map session = ActionContext.getContext().getSession();
        session.remove("context");
        session.remove("logged_in");
        session.remove("role");
        session.remove("userName");
        session.clear();
        setFlashMessage("Ok, has cerrado la sesión");
        return SUCCESS;
    }

    public String getFlashMessage() {
        return flashMessage;
    }

    public void setFlashMessage(String flashMessage) {
        this.flashMessage = flashMessage;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCandidatePassWord() {
        return candidatePassWord;
    }

    public void setCandidatePassWord(String candidatePassWord) {
        this.candidatePassWord = candidatePassWord;
    }

    public String getCompanyPassWord() {
        return companyPassWord;
    }

    public void setCompanyPassWord(String companyPassWord) {
        this.companyPassWord = companyPassWord;
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
