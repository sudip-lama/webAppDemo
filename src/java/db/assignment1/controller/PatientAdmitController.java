/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.controller;

import db.assignment1.entity.Admit;
import db.assignment1.entity.Diagnosis;
import db.assignment1.entity.Disease;
import db.assignment1.entity.Doctor;
import db.assignment1.entity.Patient;
import db.assignment1.entity.PatientAdmit;
import db.assignment1.entity.Room;
import db.assignment1.service.AdmitService;
import db.assignment1.service.DiseaseService;
import db.assignment1.service.PatientService;
import db.assignment1.service.RoomService;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author SUDIP
 */
@Component
@ManagedBean
@RequestScoped
public class PatientAdmitController {

    @Autowired
    private AdmitService admitService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private DiseaseService diseaseService;

    private Admit patientAdmit;
    private List<Admit> patientAdmitList;
    private DataModel<Admit> patientAdmitDataModel;
    private List<SelectItem> roomSelectItemType;
    private List<Room> roomList;
    private List<SelectItem> diseaseSelectItemType;
    private List<Disease> diseaseList;
    private List<Patient> patientList;
    private DataModel<Patient> patientDataModel;
    private String message;
    private boolean edit = false;

    public AdmitService getAdmitService() {
        return admitService;
    }

    public void setAdmitService(AdmitService admitService) {
        this.admitService = admitService;
    }

    public PatientService getPatientService() {
        return patientService;
    }

    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }

    public RoomService getRoomService() {
        return roomService;
    }

    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }

    public DiseaseService getDiseaseService() {
        return diseaseService;
    }

    public void setDiseaseService(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    public Admit getPatientAdmit() {
        if (patientAdmit == null) {
            patientAdmit = new Admit();
        }
        return patientAdmit;
    }

    public String saveOrEdit() {
        //create Admit
        if (!validPatientAdmit()) {
            message = "Insert Valid Patient ID or Room ID or Disease ID or Admit Date";
            return null;
        }
        if (!edit) {
            if (!isAdmitDateValid()) {
                message = "Admit Date must be equal or less than current date";
                return null;
            }
            if(admitService.getAdmitByPatientId(patientAdmit.getPatient().getId())!=null)
            {
                 message = "Patient is already admitted in Hospital";
                return null;
            }
            if (admitService.createAdmitRecord(patientAdmit)) {
                roomList = null;
                roomSelectItemType = null;
                message = "Admit  Record Created";
                patientAdmit = null;
            } else {
                message = "Error creating Admit Record";
            }

        } else {
            if (admitService.updateAdmitRecord(patientAdmit)) {
                roomList = null;
                roomSelectItemType = null;
                message = " Admit Record Updated";
                patientAdmit = null;
            } else {

                message = "Error updating Admit Record";
            }

        }
        edit = false;
        patientAdmitList = null;
        patientAdmitDataModel = null;
        return null;

    }

    private boolean validPatientAdmit() {
        if (patientService.getPatientById(patientAdmit.getPatient().getId()) == null) {
            return false;
        }
        if (roomService.getRoomById(patientAdmit.getRoom().getId()) == null) {
            return false;
        }
        if (diseaseService.getDiseaseById(patientAdmit.getDisease().getId()) == null) {
            return true;
        }
        if (patientAdmit.getAdmitDate() == null) {
            return false;
        } else {
            return true;
        }
    }

    public String selectPatientAdmitForEdit() {
        try {
            patientAdmit = (Admit) patientAdmitDataModel.getRowData();
            patientAdmit.setPatient(patientService.getPatientById(patientAdmit.getPatientId()));
            patientAdmit.setRoom(roomService.getRoomById(patientAdmit.getRoomId()));
            patientAdmit.setDisease(diseaseService.getDiseaseById(patientAdmit.getDiseaseId()));
            //adding current room Detail
            //roomService.payRoom(patientAdmit.getRoomId());
            roomList.add(patientAdmit.getRoom());
            roomSelectItemType = null;
            //doctorList = null;
            edit = true;
            message = "";
        } catch (Exception e) {
            e.printStackTrace();
            message = "Patient Admit couldn't be retrieved.";
        }
        return null;
    }

    public String selectPatientForAdmit() {
        try {
            patientAdmit.setPatient((Patient) patientDataModel.getRowData());
            patientList = null;
            patientDataModel = null;
            roomList=null;
            roomSelectItemType = null;
            //edit = true;
            message = "";
        } catch (Exception e) {
            e.printStackTrace();
            message = "Patient couldn't be retrieved.";
        }
        return null;
    }

    public Patient findPatientById(int patientId) {
        return patientService.getPatientById(patientId);
    }

    public Room findRoomById(int roomId) {
        return roomService.getRoomById(roomId);
    }

    public Disease findDiseaseById(int diseaseId) {
        return diseaseService.getDiseaseById(diseaseId);
    }

    public boolean isAdmitDateValid() {

        if (patientAdmit.getAdmitDate().after(new Date())) {
            return false;
        }

        return true;
    }

    public String searchPatientByName() {
        patientList = patientService.getAllPatientByPatientName(patientAdmit.getPatient().getName());
        patientDataModel = null;
        //message=patientVisit.getPatient().getName()+" Clicked search " +patients.size();
        return null;

    }

    public void setPatientAdmit(Admit patientAdmit) {
        this.patientAdmit = patientAdmit;
    }

    public List<Admit> getPatientAdmitList() {
        if (patientAdmitList == null) {
            patientAdmitList = admitService.getAllCurrentPatientInHospital();
        }
        return patientAdmitList;
    }

    public void setPatientAdmitList(List<Admit> patientAdmitList) {
        this.patientAdmitList = patientAdmitList;
    }

    public DataModel<Admit> getPatientAdmitDataModel() {
        if (patientAdmitDataModel == null) {
            patientAdmitDataModel = new ListDataModel<>(getPatientAdmitList());
        }
        return patientAdmitDataModel;
    }

    public void setPatientAdmitDataModel(DataModel<Admit> patientAdmitDataModel) {
        this.patientAdmitDataModel = patientAdmitDataModel;
    }

    public List<SelectItem> getRoomSelectItemType() {
        if (roomSelectItemType == null) {
            roomSelectItemType = new ArrayList<>();
            SelectItem selectItem = new SelectItem(0, "Select");
            roomSelectItemType.add(selectItem);
            for (Room room : getRoomList()) {
                roomSelectItemType.add(new SelectItem(room.getId(),
                        room.getRoomNumber() + "(" + room.getRoomType() + ")"));
            }
        }
        return roomSelectItemType;
    }

    public void setRoomSelectItemType(List<SelectItem> roomSelectItemType) {
        this.roomSelectItemType = roomSelectItemType;
    }

    public List<Room> getRoomList() {
        if (roomList == null) {
            roomList = roomService.getAllRoomAvailable();
        }
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public List<SelectItem> getDiseaseSelectItemType() {
        if (diseaseSelectItemType == null) {
            diseaseSelectItemType = new ArrayList<>();
            SelectItem selectItem = new SelectItem(0, "Select");
            diseaseSelectItemType.add(selectItem);
            for (Disease disease : getDiseaseList()) {
                diseaseSelectItemType.add(new SelectItem(disease.getId(),
                        disease.getDiseaseName() + "(" + disease.getDiseaseType() + ")"));
            }
        }

        return diseaseSelectItemType;
    }

    public void setDiseaseSelectItemType(List<SelectItem> diseaseSelectItemType) {
        this.diseaseSelectItemType = diseaseSelectItemType;
    }

    public List<Disease> getDiseaseList() {
        if (diseaseList == null) {
            diseaseList = diseaseService.getAllDiseaseAvailable();
        }
        return diseaseList;
    }

    public void setDiseaseList(List<Disease> diseaseList) {
        this.diseaseList = diseaseList;
    }

    public List<Patient> getPatientList() {
        if (patientList == null) {
            patientList = new LinkedList<>();
        }
        return patientList;
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
    }

    public DataModel<Patient> getPatientDataModel() {
        if (patientDataModel == null) {
            patientDataModel = new ListDataModel<>(getPatientList());
        }
        return patientDataModel;
    }

    public void setPatientDataModel(DataModel<Patient> patientDataModel) {
        this.patientDataModel = patientDataModel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

}
