package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "department")
public class Department implements Serializable {
	@Id
    @Column(name = "dpt_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long did;
	 @Column(name = "dpt_name")
	    private String dpt_name;

	 @Column(name = "all_money")
	    private int all_money;



	 @Column(name = "number")
	    private int number;


	 @Column(name = "aug_money")
	    private int aug_money;




















}
