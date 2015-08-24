package com.vrv.dao;

import java.util.Date;


/**
 * Loginfo entity. @author MyEclipse Persistence Tools
 */

public class Loginfo  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String optype;
     private Date opdatetime;
     private String startSysTime;
     private String endSysTime;


    // Constructors

    /** default constructor */
    public Loginfo() {
    }

	/** minimal constructor */
    public Loginfo(String optype, Date opdatetime) {
        this.optype = optype;
        this.opdatetime = opdatetime;
    }
    
    /** full constructor */
    public Loginfo(String optype, Date opdatetime, String startSysTime, String endSysTime) {
        this.optype = optype;
        this.opdatetime = opdatetime;
        this.startSysTime = startSysTime;
        this.endSysTime = endSysTime;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getOptype() {
        return this.optype;
    }
    
    public void setOptype(String optype) {
        this.optype = optype;
    }

    public Date getOpdatetime() {
        return this.opdatetime;
    }
    
    public void setOpdatetime(Date opdatetime) {
        this.opdatetime = opdatetime;
    }

    public String getStartSysTime() {
        return this.startSysTime;
    }
    
    public void setStartSysTime(String startSysTime) {
        this.startSysTime = startSysTime;
    }

    public String getEndSysTime() {
        return this.endSysTime;
    }
    
    public void setEndSysTime(String endSysTime) {
        this.endSysTime = endSysTime;
    }
   








}