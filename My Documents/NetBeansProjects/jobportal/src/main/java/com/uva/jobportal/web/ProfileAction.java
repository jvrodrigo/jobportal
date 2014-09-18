package com.uva.jobportal.web;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.uva.jobportal.dao.ProfileDAOImpl;
import com.uva.jobportal.domain.Profile;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

public class ProfileAction extends ActionSupport implements ModelDriven<Profile> {

    private static final long serialVersionUID = 1L;
    String flashMessage;
    String name, surName, curriculum, category, location, experience, training, languages;
    int candidateId;
    public List<String> listCategory, listLocation, listTraining, listExperience, listLanguages;
    private List<Profile> listProfile;
    ProfileDAOImpl profileDAO;
    Profile profile;

    public boolean authProfile(int id) {
        Map session = ActionContext.getContext().getSession();
        try {
            profileDAO = new ProfileDAOImpl();
            profile = profileDAO.viewProfile(id);
            return session.get("id").equals(profile.getCandidateId()) && session.get("role").equals("candidate");
        } catch (Exception e) {
            return false;
        }
    }

    public String view() throws Exception {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        try {
            profileDAO = new ProfileDAOImpl();
            profile = profileDAO.viewProfile(Integer.parseInt(request.getParameter("id")));
            return SUCCESS;
        } catch (NumberFormatException e) {
            setFlashMessage("Opppsss, ha ocurrido algún error");
            return ERROR;
        }
    }

    public String edit() throws Exception {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        try {
            if (authProfile(Integer.parseInt(request.getParameter("id")))) {
                if (request.getMethod().equals("POST")) {
                    profileDAO = new ProfileDAOImpl();
                    profile = profileDAO.viewProfile(Integer.parseInt(request.getParameter("id")));
                    profile.setName(getName());
                    profile.setSurName(getSurName());
                    profile.setCurriculum(getCurriculum());
                    profile.setCategory(getCategory());
                    profile.setLocation(getLocation());
                    profile.setExperience(getExperience());
                    profile.setTraining(getTraining());
                    profile.setLanguages(getLanguages());
                    profile.setModified(new Date());
                    profile = profileDAO.editProfile(profile);
                    setFlashMessage("Ok, el perfil ha sido editado");
                    return "post";
                } else {
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

                    profileDAO = new ProfileDAOImpl();
                    profile = profileDAO.viewProfile(Integer.parseInt(request.getParameter("id")));
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

    public String getFlashMessage() {
        return flashMessage;
    }

    public void setFlashMessage(String flashMessage) {
        this.flashMessage = flashMessage;
    }

    public int getCandidateId() {
        return this.candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public String getSurName() {
        return this.surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getCurriculum() {
        return this.curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
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
    public Profile getModel() {
        return profile;
    }
}
