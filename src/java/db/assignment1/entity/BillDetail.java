/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.entity;

import java.util.Calendar;
import java.util.Date;
import org.springframework.scheduling.config.IntervalTask;

/**
 *
 * @author SUDIP
 */
public class BillDetail {
    //This object can be Admit/PatientVisit/PatientMedicine/PatientDiagnosis
    
    private Object billObject;
    private Date transactionDate;
    private String description;
    private double price;

    public BillDetail() {
    }

    public BillDetail(Object billObject) {
        setBillObject(billObject);
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Object getBillObject() {
        return billObject;
    }

    
    public void setBillObject(Object billObject) {
        this.billObject = billObject;
        if(billObject instanceof PatientMedication)
        {
            PatientMedication pm=(PatientMedication) billObject;
            this.transactionDate=pm.getPurchaseDate();
            this.description=pm.getMedicineName()+"[ "+pm.getMedicineQuantity()+" * "
                    +pm.getPrice()+" ]";
            this.price=pm.getPrice()*pm.getMedicineQuantity();
        }
        if(billObject instanceof Admit)
        {
            Admit admit=(Admit) billObject;
            this.transactionDate=admit.getAdmitDate();
            int dayDiff=(int) (Calendar.getInstance().getTime().getTime()-admit.getAdmitDate().getTime())
                    /(1000*60*60*24)+1;
            this.description=admit.getRoomNumber()+"[ "+admit.getRoomPrice()+" * "
                    +dayDiff+" ]";
            this.price=admit.getRoomPrice()*dayDiff;
        }
        if(billObject instanceof PatientVisit)
        {
            PatientVisit pv=(PatientVisit) billObject;
            this.transactionDate=pv.getVistDate();
            this.description="Consulted by "+pv.getDoctorName()+"[ "+pv.getPrice()+" ]";
            this.price=pv.getPrice();
        }
        if(billObject instanceof PatientDiagnosis)
        {
            PatientDiagnosis pd=(PatientDiagnosis) billObject;
            this.transactionDate=pd.getDiagnosisDate();
            this.description=pd.getDiagnosisName()+"[ "+pd.getDiagnosisPrice()+" ]";
            this.price=pd.getDiagnosisPrice();
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
