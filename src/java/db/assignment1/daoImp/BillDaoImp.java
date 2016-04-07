/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.daoImp;

import db.assignment1.dao.BillDao;
import db.assignment1.entity.Admit;
import db.assignment1.entity.Bill;
import db.assignment1.entity.BillDetail;
import db.assignment1.entity.PatientDiagnosis;
import db.assignment1.entity.PatientMedication;
import db.assignment1.entity.PatientVisit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author maha
 */
@Repository
public class BillDaoImp implements BillDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

   

    @Override
    public Bill getAllBillAvailableByPatientId(int patientId) {
        Bill bill=new Bill();
        
        //Getting all patient_Medicine bill
        String sql = "SELECT PATIENT_MEDICINE_id, PURCHASE_DATE, MEDICINE_QUANTITY,"
                + " PATIENT_MEDICINE.MEDICINE_id, "
                + " MEDICINE_NAME, PRICE "
                + " FROM PATIENT_MEDICINE, MEDICINE_DETAIL "
                + " WHERE PATIENT_MEDICINE.MEDICINE_ID = MEDICINE_DETAIL.MEDICINE_id "
                + " AND  PAYMENT_STAUS =0 AND PATIENT_ID= ?";
        
        bill.setPatientMedicationList( jdbcTemplate.query(sql,new Object[]{patientId},
                new RowMapper<PatientMedication>() {

                    @Override
                    public PatientMedication mapRow(ResultSet rs, int rowNum) throws SQLException {
                        PatientMedication patientMedication = new PatientMedication(rs.getInt("PATIENT_MEDICINE_id"),
                                rs.getDate("PURCHASE_DATE"),rs.getInt("MEDICINE_QUANTITY"),
                                rs.getInt("MEDICINE_id"),rs.getString("MEDICINE_NAME"),
                                rs.getDouble("PRICE"));
                        return patientMedication;
                    }
                }));
        
        //Getting all patient_admit bill
        sql = "SELECT ADMIT_ID, ADMIT_DATE, ADMIT_DETAIL.ROOM_ID ,"
                + " ROOM_NUMBER , ROOM_PRICE"
                + " FROM ADMIT_DETAIL, ROOM_DETAIL "
                + " WHERE ADMIT_DETAIL.ROOM_ID = ROOM_DETAIL.ROOM_ID "
                + " AND  PAYMENT_STAUS =0 AND PATIENT_ID= ?";
        
        bill.setAdmitList(jdbcTemplate.query(sql,new Object[]{patientId},
                new RowMapper<Admit>() {

                    @Override
                    public Admit mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Admit admit = new Admit(rs.getInt("ADMIT_ID"),
                                rs.getDate("ADMIT_DATE"),rs.getInt("ROOM_ID"),
                                rs.getString("ROOM_NUMBER"),
                                rs.getDouble("ROOM_PRICE"));
                        return admit;
                    }
                }));
        
        //Getting all PATIENT_VISIT bill
        sql = "SELECT PATIENT_VISIT_ID, VISIT_PRICE, VISIT_DATE,"
                + " PATIENT_VISIT.DOCTOR_ID, DOCTOR_NAME "
                + " FROM PATIENT_VISIT, DOCTOR_DETAIL "
                + " WHERE PATIENT_VISIT.DOCTOR_ID=DOCTOR_DETAIL.DOCTOR_ID "
                + " AND  PAYMENT_STAUS =0 AND PATIENT_ID= ?";
        
        bill.setPatientVisitList(jdbcTemplate.query(sql,new Object[]{patientId},
                new RowMapper<PatientVisit>() {

                    @Override
                    public PatientVisit mapRow(ResultSet rs, int rowNum) throws SQLException {
                        PatientVisit patientVisit = new PatientVisit(rs.getInt("PATIENT_VISIT_ID")
                                ,rs.getDouble("VISIT_PRICE"),
                                rs.getDate("VISIT_DATE"),rs.getInt("DOCTOR_ID"),
                                rs.getString("DOCTOR_NAME"));
                        return patientVisit;
                    }
                }));
        //Getting all PATIENT_DIAGNOSIS bill
        sql = "SELECT PATIENT_DIAGNOSIS_ID, DIAGNOSIS_DATE, PATIENT_DIAGNOSIS.DIAGNOSIS_ID ,"
                + " DIAGNOSIS_NAME , DIAGNOSIS_PRICE "
                + " FROM PATIENT_DIAGNOSIS, DIAGNOSIS_DETAIL "
                + " WHERE PATIENT_DIAGNOSIS.DIAGNOSIS_ID = DIAGNOSIS_DETAIL.DIAGNOSIS_ID "
                + " AND  PAYMENT_STAUS =0 AND PATIENT_ID= ?";
        
        bill.setPatientDiagnosisList(jdbcTemplate.query(sql,new Object[]{patientId},
                new RowMapper<PatientDiagnosis>() {

                    @Override
                    public PatientDiagnosis mapRow(ResultSet rs, int rowNum) throws SQLException {
                        PatientDiagnosis patientDiagnosis = new PatientDiagnosis(
                                rs.getInt("PATIENT_DIAGNOSIS_ID")
                                ,rs.getDate("DIAGNOSIS_DATE"),rs.getInt("DIAGNOSIS_ID")
                                ,rs.getString("DIAGNOSIS_NAME"),rs.getDouble("DIAGNOSIS_PRICE")
                                );
                        return patientDiagnosis;
                    }
                }));
        return bill;
    }

    @Override
    public boolean payBill(List<BillDetail> billDetailList) {
        
        //update Patient
       for(BillDetail bd:billDetailList)
       {
           if(bd.getBillObject() instanceof PatientMedication)
           {
               PatientMedication pm=(PatientMedication)bd.getBillObject();
               if(!payPatientMedicine(pm.getId()))
                   return false;
               
           }
           if(bd.getBillObject() instanceof PatientDiagnosis)
           {
               PatientDiagnosis pd=(PatientDiagnosis)bd.getBillObject();
               if(!payPatientDiagnosis(pd.getId()))
                   return false;
               
           }
            if(bd.getBillObject() instanceof Admit)
           {
               Admit ad=(Admit)bd.getBillObject();
               if(!payAdmitDetail(ad.getId(),ad.getRoomId()))
                   return false;
               
           }
            if(bd.getBillObject() instanceof PatientVisit)
           {
               PatientVisit pv=(PatientVisit)bd.getBillObject();
               if(!payPatientVisit(pv.getId()))
                   return false;
               
           }
       }
       
        return true;
    }
    
    public boolean payPatientMedicine(int patientMedicationId)
    {
        String sql = "UPDATE  PATIENT_MEDICINE "
                + " SET PAYED_DATE = ? ,"
                + " PAYMENT_STAUS = 1  "
                + " WHERE PATIENT_MEDICINE_id = ? ";
        Object [] params=new Object[]{Calendar.getInstance().getTime(),
            patientMedicationId};
         int [] types=new int[]{Types.DATE,Types.INTEGER};
     return jdbcTemplate.update(sql, params, types)>0?true:false; 
    
    }
     public boolean payAdmitDetail(int admitId,int roomId)
    {
        String sql = "UPDATE  ADMIT_DETAIL "
                + " SET DISCHARGE_DATE = ? ,"
                + " PAYMENT_STAUS = 1  "
                + " WHERE ADMIT_ID = ? ";
        Object [] params=new Object[]{Calendar.getInstance().getTime(),
            admitId};
         int [] types=new int[]{Types.DATE,Types.INTEGER};
     if( jdbcTemplate.update(sql, params, types)<=0)
         return false;
     
        sql = "UPDATE  ROOM_DETAIL "
                + " SET ROOM_AVAILABLE=0"
                + " WHERE ROOM_ID = ? ";
            params=new Object[]{roomId};
          types=new int[]{Types.INTEGER};
     if( jdbcTemplate.update(sql, params, types)<=0)
         return false;
     
     return true;
    }
public boolean payPatientVisit(int patientVisitId)
    {
        String sql = "UPDATE  PATIENT_VISIT "
                + " SET PAYED_DATE = ? ,"
                + " PAYMENT_STAUS = 1  "
                + " WHERE PATIENT_VISIT_ID = ? ";
        Object [] params=new Object[]{Calendar.getInstance().getTime(),
            patientVisitId};
         int [] types=new int[]{Types.DATE,Types.INTEGER};
     return jdbcTemplate.update(sql, params, types)>0?true:false; 
    
    }
public boolean payPatientDiagnosis(int patientDiagnosisId)
    {
        String sql = "UPDATE  PATIENT_DIAGNOSIS "
                + " SET PAYED_DATE = ? ,"
                + " PAYMENT_STAUS = 1  "
                + " WHERE PATIENT_DIAGNOSIS_ID = ? ";
        Object [] params=new Object[]{Calendar.getInstance().getTime(),
            patientDiagnosisId};
         int [] types=new int[]{Types.DATE,Types.INTEGER};
     return jdbcTemplate.update(sql, params, types)>0?true:false; 
    
    }
}
