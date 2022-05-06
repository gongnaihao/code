package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "employee")
public class Employee implements Serializable{
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eid;


	 @Column(name = "emp_name")
	 private String emp_name;

	 @Column(name = "address")
	 private String address;
	 @Column(name = "money")
	 private int money;

	    /**
	     * 電話番号
	     */
	 @Column(name = "customer")
	  private String customer;
	 @Column(name = "dep_id")
	  private Long dep_id;
	 @Column(name = "state")
	  private int state;
	 @Column(name = "updateday")
	  private Date updateday;















}
