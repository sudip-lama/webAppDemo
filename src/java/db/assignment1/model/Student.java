/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.model;

/**
 *
 * @author SUDIP
 */
public class Student {
    private int Id;
    private String name;
    private String dept;
    private int totalCredit;
    //
    private int cno;
    private String title;
    public Student() {
    }

    public int getCno() {
        return cno;
    }

    public void setCno(int cno) {
        this.cno = cno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Student(int Id, String name, int totalCredit) {
        this.Id = Id;
        this.name = name;
        this.totalCredit = totalCredit;
    }

    public Student(int Id, String name) {
        this.Id = Id;
        this.name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public int getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(int totalCredit) {
        this.totalCredit = totalCredit;
    }
   
    
}
