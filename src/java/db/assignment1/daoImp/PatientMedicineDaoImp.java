/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.daoImp;

import db.assignment1.dao.PatientMedicineDao;
import db.assignment1.entity.Admit;
import db.assignment1.entity.Medicine;
import db.assignment1.entity.PatientMedication;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SUDIP
 */
@Repository
public class PatientMedicineDaoImp implements PatientMedicineDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int createPatientMedicineRecord(List<PatientMedication> patientMedicationList) {

        String sql = "INSERT INTO PATIENT_MEDICINE ( "
                + " PURCHASE_DATE, PATIENT_ID , MEDICINE_ID,"
                + " MEDICINE_QUANTITY , PAYMENT_STAUS ) "
                + "VALUES ( ? , ? , ? , ? , 0) ";
        for (PatientMedication pm : patientMedicationList) {
            Object[] params = new Object[]{pm.getPurchaseDate(),
                pm.getPatientId(), pm.getMedicineId(), pm.getMedicineQuantity()};
            int[] types = new int[]{Types.DATE, Types.INTEGER, Types.INTEGER, Types.INTEGER};
            if (jdbcTemplate.update(sql, params, types) == 0) {
                return 0;
            }
        }
        return 1;
    }

    @Override
    public List<PatientMedication> getAllUnpaidPatientMedicine(Integer patientId) {
        List<PatientMedication> patientMedicationList = new ArrayList<>();
        String sql = "SELECT * FROM PATIENT_MEDICINE WHERE PAYMENT_STAUS = 0 "
                + " and PATIENT_ID = ?";

        patientMedicationList = jdbcTemplate.query(sql, new Object[]{patientId},
                new RowMapper<PatientMedication>() {

                    @Override
                    public PatientMedication mapRow(ResultSet rs, int rowNum) throws SQLException {
                        PatientMedication patientMedication = new PatientMedication(rs.getInt("PATIENT_MEDICINE_id"),
                                rs.getDate("PURCHASE_DATE"),
                                rs.getInt("MEDICINE_QUANTITY"),
                                rs.getInt("PATIENT_ID"), rs.getInt("MEDICINE_ID"));
                        return patientMedication;
                    }
                });
        return patientMedicationList;

    }

    @Override
    public PatientMedication getPatientMedicineById(Integer patientMedicationId) {
        List<PatientMedication> patientMedicationList = new ArrayList<>();
        String sql = "SELECT * FROM PATIENT_MEDICINE WHERE PATIENT_MEDICINE_id = ? ";

        patientMedicationList = jdbcTemplate.query(sql, new Object[]{patientMedicationId},
                new RowMapper<PatientMedication>() {

                    @Override
                    public PatientMedication mapRow(ResultSet rs, int rowNum) throws SQLException {
                        PatientMedication patientMedication = new PatientMedication(rs.getInt("PATIENT_MEDICINE_id"),
                                rs.getDate("PURCHASE_DATE"),
                                rs.getInt("MEDICINE_QUANTITY"),
                                rs.getInt("PATIENT_ID"), rs.getInt("MEDICINE_ID"));
                        return patientMedication;
                    }
                });
        if (patientMedicationList.isEmpty()) {
            return null;
        } else {
            return patientMedicationList.get(0);
        }
    }
    /*
     @Override
     public int update(PatientMedication patientMedication) {
     String sql = "UPDATE  ADMIT_DETAIL "
     + " SET ADMIT_DATE = ? ,"
     + " PATIENT_ID = ? , "
     + " ROOM_ID = ? ,"
     + " DISEASE_ID = ? "
     + " WHERE ADMIT_ID = ? ";
     Object [] params=new Object[]{admit.getAdmitDate(),admit.getPatientId(),
     admit.getRoomId(),admit.getDiseaseId(),admit.getId()};
     int [] types=new int[]{Types.DATE,Types.INTEGER,Types.INTEGER, Types.INTEGER,Types.INTEGER};
     return jdbcTemplate.update(sql, params, types);
     }
     */

    @Override
    public List<PatientMedication> getTopPatientMedicine() {
        List<PatientMedication> patientMedicationList = new ArrayList<>();
        String sql = "SELECT MEDICINE_ID, SUM( MEDICINE_QUANTITY ) AS TOTAT_QUANTITY"
                + " FROM PATIENT_MEDICINE "
                + " GROUP BY MEDICINE_ID ";

        patientMedicationList = jdbcTemplate.query(sql,
                new RowMapper<PatientMedication>() {

                    @Override
                    public PatientMedication mapRow(ResultSet rs, int rowNum) throws SQLException {
                        PatientMedication patientMedication = new PatientMedication(
                                rs.getInt("TOTAT_QUANTITY"),rs.getInt("MEDICINE_ID")
                                );
                        return patientMedication;
                    }
                });
        return patientMedicationList;
    }

}
