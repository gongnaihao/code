package com.example.demo.dto;


import java.io.Serializable;

import lombok.Data;

@Data
public class DepartmentRequest implements Serializable {


	    private Long did;
	    private String dpt_name;
	    private int all_money;
	    private int number;
	    private int aug_money;

}
