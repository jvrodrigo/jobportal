/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uva.jobportal.web;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.uva.jobportal.dao.CandidateDAOImpl;
import com.uva.jobportal.dao.CandidateDAO;
import com.uva.jobportal.domain.Candidate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import java.security.MessageDigest;

/**
 *
 * @author Administrator
 */
public class CandidateAction extends ActionSupport implements ModelDriven<Candidate> {

    private static final long serialVersionUID = 1L;
    String flashMessage;
    String userName, passWord, passWordConf, email;
    int id;
    Candidate candidate;
    private List<Candidate> listCandidate;
    private CandidateDAO candidateDAO;

    public void validateAdd() {
        //candidateDAO = new CandidateDAOImpl();// Para buscar en la base de datos que no coincidan el nombre de usuario
        //if(candidateDAO.existCandidateByUserName("userName")) addFieldError("userName","El nombre de usuario ya existe");
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        if (request.getMethod().equals("POST")) {
            if (getUserName().length() == 0) {
                addFieldError("userName", "Por favor, introduzca el nombre de usuario");
            }
            if (getEmail().length() == 0 || !getEmail().contains("@")) {
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
            if (getPassWordConf().length() == 0) {
                addFieldError("passWordConf", "Por favor, repita la contraseña");
            }
            if (!getPassWordConf().equals(getPassWord())) {
                addFieldError("passWordConf", "Las contraseñas no coinciden");
                addFieldError("passWord", "Las contraseñas no coinciden");
            }
            candidateDAO = new CandidateDAOImpl();
            candidate = candidateDAO.findById(getId());
            setUserName(ActionContext.getContext().getSession().get("userName").toString());
        }

    }

    public boolean authCandidate(int id) {
        Map session = ActionContext.getContext().getSession();
        try {
            return session.get("logged_in").equals(true)
                    && session.get("role").equals("candidate")
                    && session.get("id").equals(id);
        } catch (Exception e) {
            return false;
        }
    }

    public String view() throws Exception {

        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        if (authCandidate(Integer.parseInt(request.getParameter("id")))) {
            candidateDAO = new CandidateDAOImpl();
            candidate = candidateDAO.findById(Integer.parseInt(request.getParameter("id")));
            return SUCCESS;
        } else {
            setFlashMessage("No tienes acceso a este contenido");
            return ERROR;
        }
    }

    public String edit() throws Exception {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        if (authCandidate(Integer.parseInt(request.getParameter("id")))) {
            if (request.getMethod().equals("POST")) {
                candidateDAO = new CandidateDAOImpl();
                candidate = candidateDAO.findById(Integer.parseInt(request.getParameter("id")));
                candidate.setEmail(getEmail());
                candidate.setPassWord(MD5Hashing(getPassWord()));
                candidate.setModified(new Date());
                candidate = candidateDAO.editCandidate(candidate);
                setFlashMessage("Ok, el candidato " + candidate.getUserName() + " ha sido editado");
                return "post";
            } else {
                candidateDAO = new CandidateDAOImpl();
                candidate = candidateDAO.findById(Integer.parseInt(request.getParameter("id")));
                return "get";
            }
        } else {
            setFlashMessage("No tienes acceso a este contenido");
            return ERROR;
        }
    }

    public String delete() throws Exception {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        if (authCandidate(Integer.parseInt(request.getParameter("id")))) {
            candidateDAO = new CandidateDAOImpl();
            candidate = candidateDAO.deleteCandidate(Integer.parseInt(request.getParameter("id")));
            setFlashMessage("Ok, el candidato " + candidate.getUserName() + " ha sido eliminado");
            return SUCCESS;
        } else {
            setFlashMessage("No tienes acceso a este contenido");
            return ERROR;
        }
    }

    public String list() throws Exception {
        candidateDAO = new CandidateDAOImpl();
        listCandidate = candidateDAO.listCandidate();
        setListCandidate(listCandidate);
        return SUCCESS;
    }

    public String add() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        if (request.getMethod().equals("POST")) {
            try {
                candidate = new Candidate();
                candidate.setUserName(getUserName());
                candidate.setPassWord(MD5Hashing(getPassWord())); // Codifica la contraseña en MD5
                candidate.setEmail(getEmail());
                candidate.setCreated(new Date());
                candidate.setModified(new Date());
                candidateDAO = new CandidateDAOImpl();
                if (candidateDAO.addCandidate(candidate)) {
                    setFlashMessage("Ok, el candidato " + getUserName() + " ha sido guardado");
                    return "post";
                } else {
                    setFlashMessage("Error, el candidato no ha sido guardado");
                    return ERROR;
                }
            } catch (Exception ex) {
                setFlashMessage("Error, el candidato no ha sido guardado");
                return ERROR;
            }
        } else {
            return "get";
        }
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public List<Candidate> getListCandidate() {
        return this.listCandidate;
    }

    public void setListCandidate(List<Candidate> listCandidate) {
        this.listCandidate = listCandidate;
    }

    public String getFlashMessage() {
        return flashMessage;
    }

    public void setFlashMessage(String flashMessage) {
        this.flashMessage = flashMessage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Candidate getModel() {
        return candidate;
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
