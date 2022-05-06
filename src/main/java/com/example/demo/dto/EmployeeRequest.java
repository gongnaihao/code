package com.example.demo.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;


@Data
public class EmployeeRequest  implements Serializable {
	  private Long eid;

		 private String emp_name;

		 private String address;

		 private int money;

		    /**
		     * 電話番号
		     */

		  private String customer;

		  private Long dep_id;
		  private int state;
		  private Date updateday;




}
