/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uva.jobportal.web;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.uva.jobportal.dao.InscriptionDAOImpl;
import com.uva.jobportal.dao.JobDAOImpl;
import com.uva.jobportal.domain.Job;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Administrator
 */
public class JobAction extends ActionSupport implements ModelDriven<Job> {

    private static final long serialVersionUID = 1L;
    String flashMessage;
    String companyName, title, description, category, location, experience, training, languages;
    int companyId, id;
    boolean isInscribed;
    Job job;
    JobDAOImpl jobDAO;
    private List<Job> listJob;
    public List<String> listCategory, listLocation, listTraining, listExperience, listLanguages;

    public JobAction() {
        listCategory = new ArrayList<>();
        listLocation = new ArrayList<>();
        listTraining = new ArrayList<>();
        listExperience = new ArrayList<>();
        listLanguages = new ArrayList<>();
        
        listLocation.add("Todas las provincias");
        listLocation.add("A Coruña");
        listLocation.add("Álava/Araba");
        listLocation.add("Albacete");
        listLocation.add("Alicante");
        listLocation.add("Almería");
        listLocation.add("Asturias");
        listLocation.add("Ávila");
        listLocation.add("Badajoz");
        listLocation.add("Barcelona");
        listLocation.add("Burgos");
        listLocation.add("Cáceres");
        listLocation.add("Cádiz");
        listLocation.add("Cantabria");
        listLocation.add("Castellón");
        listLocation.add("Ceuta");
        listLocation.add("Ciudad Real");
        listLocation.add("Córdoba");
        listLocation.add("Cuenca");
        listLocation.add("Girona");
        listLocation.add("Granada");
        listLocation.add("Guadalajara");
        listLocation.add("Guipúzcoa/Gipuzkoa");
        listLocation.add("Huelva");
        listLocation.add("Huesca");
        listLocation.add("Illes Balears");
        listLocation.add("Jaén");
        listLocation.add("La Rioja");
        listLocation.add("Las Palmas");
        listLocation.add("León");
        listLocation.add("Lleida");
        listLocation.add("Lugo");
        listLocation.add("Madrid");
        listLocation.add("Málaga");
        listLocation.add("Melilla");
        listLocation.add("Murcia");
        listLocation.add("Navarra");
        listLocation.add("Ourense");
        listLocation.add("Palencia");
        listLocation.add("Pontevedra");
        listLocation.add("Salamanca");
        listLocation.add("Santa Cruz de Tenerife");
        listLocation.add("Segovia");
        listLocation.add("Soria");
        listLocation.add("Tarragona");
        listLocation.add("Teruel");
        listLocation.add("Toledo");
        listLocation.add("Valencia/València");
        listLocation.add("Valladolid");
        listLocation.add("Vizcaya/Bizkaia");
        listLocation.add("Zamora");
        listLocation.add("Zaragoza");
        
        listCategory.add("Todas");
        listCategory.add("Administración Pública");
        listCategory.add("Administración de Empresas");
        listCategory.add("Atención a clientes");
        listCategory.add("Calidad, producción e I+D");
        listCategory.add("Comercias y ventas");
        listCategory.add("Compras, logística y almacén");
        listCategory.add("Diseño y artes gráficas");
        listCategory.add("Educación y formación");
        listCategory.add("Finanzas y banca");
        listCategory.add("Informática y telecomunicaciones");
        listCategory.add("Ingenieros y técnicos");
        listCategory.add("Inmobiliario y construcción");
        listCategory.add("Legal");
        listCategory.add("Marketing y comunicación");
        listCategory.add("Profesionales, artes y oficios");
        listCategory.add("Recursos humanos");
        listCategory.add("Sanidad y salud");
        listCategory.add("Turismo y restauración");
        listCategory.add("Ventas al detalle");
        listCategory.add("Otros");
        
        listTraining.add("Sin estudios");
        listTraining.add("ESO");
        listTraining.add("Bachillerato");
        listTraining.add("Grado Medio");
        listTraining.add("Grado Superior");
        listTraining.add("Diplomado");
        listTraining.add("Licenciado");
        
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

    public void validateAdd() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        if (request.getMethod().equals("POST")) {
            if (getTitle().length() == 0) {
                addFieldError("title", "Por favor, introduzca el título de la oferta de trabajo");
            }
            if (getDescription().length() == 0) {
                addFieldError("description", "Por favor, introduzca una descripción");
            }
            if (getCategory().length() == 0) {
                addFieldError("category", "Por favor, seleccione alguna categoría");
            }
            if (getLocation().length() == 0) {
                addFieldError("location", "Por favor, seleccione alguna ubicación");
            }
            if (getTraining().length() == 0) {
                addFieldError("training", "Por favor, seleccione la formación");
            }
            if (getLanguages().length() == 0) {
                addFieldError("languages", "Por favor, seleccione algún idioma");
            }
        }
    }

    public void validateEdit() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        if (request.getMethod().equals("POST")) {
            if (getTitle().length() == 0) {
                addFieldError("title", "Por favor, introduzca el título de la oferta de trabajo");
            }
            if (getDescription().length() == 0) {
                addFieldError("description", "Por favor, introduzca una descripción");
            }
            if (getCategory().length() == 0) {
                addFieldError("category", "Por favor, seleccione alguna categoría");
            }
            if (getLocation().length() == 0) {
                addFieldError("location", "Por favor, seleccione alguna ubicación");
            }
            if (getTraining().length() == 0) {
                addFieldError("training", "Por favor, seleccione la formación");
            }
            if (getLanguages().length() == 0) {
                addFieldError("languages", "Por favor, seleccione algún idioma");
            }
            jobDAO = new JobDAOImpl();
            job = jobDAO.findById(getId());
        }
    }

    public boolean authCompany(int id) {
        Map session = ActionContext.getContext().getSession();
        try {
            jobDAO = new JobDAOImpl();
            job = jobDAO.findById(id);
            return session.get("id").equals(job.getCompanyId()) && session.get("role").equals("company");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean authListJobByCompanyId(int id) {
        Map session = ActionContext.getContext().getSession();
        try {
            return session.get("id").equals(id) && session.get("role").equals("company");
        } catch (Exception e) {
            return false;
        }
    }

    public String add() {
        Map session = ActionContext.getContext().getSession();
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        try {
            if (session.get("role").equals("company")) {
                if (request.getMethod().equals("POST")) {
                    try {
                        job = new Job();
                        job.setCompanyId(getCompanyId());
                        job.setCompanyName(getCompanyName());
                        job.setTitle(getTitle());
                        job.setDescription(getDescription());
                        job.setLocation(getLocation());
                        job.setCategory(getCategory());
                        job.setExperience(experience);
                        job.setTraining(training);
                        job.setLanguages(languages);
                        job.setCreated(new Date());
                        job.setModified(new Date());
                        jobDAO = new JobDAOImpl();
                        jobDAO.addJob(job);
                        setFlashMessage("OK el trabajo " + getTitle() + " ha sido guardado");
                        return "post";
                    } catch (Exception ex) {
                        setFlashMessage("Opppsss, ha ocurrido algún error");
                        return ERROR;
                    }
                } else {
                    return "get";
                }
            } else {
                setFlashMessage("No tienes acceso a este contenido");
                return ERROR;
            }
        } catch (Exception e) {
            setFlashMessage("No tienes acceso a este contenido");
            return ERROR;
        }
    }

    public String edit() throws Exception {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        try {
            if (authCompany(Integer.parseInt(request.getParameter("id")))) {
                if (request.getMethod().equals("POST")) {

                    jobDAO = new JobDAOImpl();//
                    job = jobDAO.findById(getId());
                    job.setTitle(getTitle());
                    job.setDescription(getDescription());
                    job.setExperience(experience);
                    job.setTraining(training);
                    job.setLanguages(languages);
                    job.setModified(new Date());
                    job = jobDAO.editJob(job);
                    setFlashMessage("Ok, el trabajo " + job.getTitle() + " ha sido editado");
                    return "post";
                } else {
                    jobDAO = new JobDAOImpl();
                    job = jobDAO.findById(Integer.parseInt(request.getParameter("id")));
                    return "get";
                }
            } else {
                setFlashMessage("No tienes acceso a este contenido");
                return ERROR;
            }
        } catch (NumberFormatException ex) {
            setFlashMessage("No tienes acceso a este contenido");
            return ERROR;
        }
    }

    public String delete() throws Exception {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        try {
            if (authCompany(Integer.parseInt(request.getParameter("id")))) {
                jobDAO = new JobDAOImpl();
                job = jobDAO.deleteJob(Integer.parseInt(request.getParameter("id")));
                setFlashMessage("Ok, el trabajo " + job.getTitle() + " ha sido eliminado");
                return SUCCESS;
            } else {
                setFlashMessage("No tienes acceso a este contenido");
                return ERROR;
            }
        } catch (NumberFormatException e) {
            setFlashMessage("No tienes acceso a este contenido");
            return ERROR;
        }
    }

    public String view() throws Exception {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        try {
            Map session = ActionContext.getContext().getSession();
            jobDAO = new JobDAOImpl();
            job = jobDAO.findById(Integer.parseInt(request.getParameter("id")));
            if (session.get("id") != null) {
                InscriptionDAOImpl inscriptionDAO = new InscriptionDAOImpl();
                isInscribed = inscriptionDAO.findByCandidateIdAndJobId(Integer.parseInt(session.get("id").toString()), job.getId()); // Comprueba si el usuario está inscrito
            }
            setFlashMessage(getFlashMessage());
            return SUCCESS;
        } catch (Exception e) {
            setFlashMessage("Opppsss, ha ocurrido algún error");
            return ERROR;
        }
    }

    public String list() throws Exception {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        jobDAO = new JobDAOImpl();
        if (request.getParameter("id") != null) {
            if (authListJobByCompanyId(Integer.parseInt(request.getParameter("id")))) {
                setListJob(jobDAO.listJobByCompanyId(Integer.parseInt(request.getParameter("id"))));
            } else {
                setFlashMessage("No tienes acceso a este contenido");
                return ERROR;
            }
        } else {
            listJob = jobDAO.listJob();
        }
        setListJob(listJob);
        return SUCCESS;
    }

    public List<Job> getListJob() {
        return this.listJob;
    }

    public void setListJob(List<Job> listJob) {
        this.listJob = listJob;
    }

    public String getFlashMessage() {
        return flashMessage;
    }

    public void setFlashMessage(String flashMessage) {
        this.flashMessage = flashMessage;
    }

    public boolean getIsInscribed() {
        return isInscribed;
    }

    public void setIsInscribed(boolean isInscribed) {
        this.isInscribed = isInscribed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTraining() {
        return this.training;
    }

    public void setTraining(String training) {
        this.training = training;
    }

    public String getExperience() {
        return this.experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getLanguages() {
        return this.languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public List<String> getListLocation() {
        return listLocation;
    }

    public void setListLocation(List<String> listLocation) {
        this.listLocation = listLocation;
    }

    public List<String> getListCategory() {
        return listCategory;
    }

    public void setListCategory(List<String> listCategory) {
        this.listCategory = listCategory;
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

    @Override
    public Job getModel() {
        return job;
    }
}
