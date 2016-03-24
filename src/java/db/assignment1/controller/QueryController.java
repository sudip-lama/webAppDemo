/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.controller;

import db.assignment1.db.StudentServices;
import db.assignment1.model.Student;
import db.assignment1.service.MessageService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author SUDIP
 */
@Component
@ManagedBean
@ViewScoped
public class QueryController {
    @Autowired
    private MessageService messageService;
    private String title="Database !!!";
    private StudentServices studentService =new StudentServices();
    private List<Student> students;
    private String departmentName="CS";
    private String deptName="CS";
    private String message;
    private String studentName;
    private boolean courseByStudent=false;
    private boolean studentByDepartment=false;
    private boolean totalCreditDisplay=false;
    private String demoTitle="Testing Title";
    private Student student;
    private double salary=12012;
    public String searchStudentByDepartment()
    {
        courseByStudent=false;
        
        totalCreditDisplay=false;
        students=studentService.getStudentsByDepartment(departmentName);
        if(students==null||students.size()==0)
        {
            message="No Record Found of "+departmentName;
            studentByDepartment=false;
        }
        else
        {
            message=departmentName+" students are:";
            studentByDepartment=true;
        }
        return null;
    }
    //This function used for searching course name
    //this student has passed with A, B
    public String searchCourseByStudentName()
    {
        
        studentByDepartment=false;
        totalCreditDisplay=false;
        students=studentService.getCourseTakenByStudent(studentName);
        if(students==null||students.size()==0)
        {
            message="No Courses are  Found where "+studentName+" passed with A or B";
            courseByStudent=false;
        }
        else
        {
            message=studentName+" passed with A or B courses are:"; 
            courseByStudent=true;
        }
        return null;
    }
    //Displaying the total credit of student by department;
    public String searchStudentWithTotalByDepartment()
    {
       courseByStudent=false;
        studentByDepartment=false;
        
        students=studentService.getStudentWithTotalCreditByDepartment(deptName);
        if(students==null||students.size()==0)
        {
            message="No Record Found of "+departmentName;
            totalCreditDisplay=false;
        }
        else
        {
            totalCreditDisplay=true;
            message=departmentName+" students total credits are:";
        }
        return null;
    }
    
    public String newTestingFunction()
    {
        message=student.getName();
        return null;
    }
    public StudentServices getStudentService() {
        return studentService;
    }

    public void setStudentService(StudentServices studentService) {
        this.studentService = studentService;
    }
    
    public List<Student> getStudents() {
        if(students==null)
        {
            students=new ArrayList<>();
        }
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public boolean isCourseByStudent() {
        return courseByStudent;
    }

    public void setCourseByStudent(boolean courseByStudent) {
        this.courseByStudent = courseByStudent;
    }

    public boolean isStudentByDepartment() {
        return studentByDepartment;
    }

    public void setStudentByDepartment(boolean studentByDepartment) {
        this.studentByDepartment = studentByDepartment;
    }

    public Student getStudent() {
        if(this.student==null)
        {
            this.student=new Student();
        }
        return student;
    }

    public void setStudent(Student student) {
        
        this.student = student;
    }

    public boolean isTotalCreditDisplay() {
        return totalCreditDisplay;
    }

    public void setTotalCreditDisplay(boolean totalCreditDisplay) {
        this.totalCreditDisplay = totalCreditDisplay;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDemoTitle() {
        return demoTitle;
    }

    public void setDemoTitle(String demoTitle) {
        this.demoTitle = demoTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public double getAnualSalary()
    {
        return salary*12;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public MessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }
}
