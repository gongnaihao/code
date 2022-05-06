package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeeRequest;
import com.example.demo.entity.Employee;
import com.example.demo.repository.DepRepository;
import com.example.demo.repository.EmpRepository;

@Service
public class EmpService {

	@Autowired
	 EmpRepository empRepository;
	@Autowired
	 DepRepository dep;
	 public List<Employee> searchdepAll() {
	        return empRepository.findAll();
	    }
	 public Employee findById(Long eid) {
	        return empRepository.findById(eid).get();
	      }

	 public Page<Employee> seachByname(Long did,Pageable pageable){
		 return empRepository.findBydptname(did,pageable);

	 }
	 public Page<Employee> seachzaizhi(Long did,Pageable pageable){
		 return empRepository.findzaizhi(did,pageable);

	 }
	 public List<Employee> seachtuizhi(Long did){
		 return empRepository.findtuizhi(did);

	 }
	 public String findByName(String name){
		 return empRepository.findByName(name);

	 }

	   //SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
	   Date currentTime = new Date();


	   public void empupdate(EmployeeRequest empRequest) {


	       Employee emp = findById(empRequest.getEid());
	      emp.setEid(empRequest.getEid());
	      emp.setAddress(empRequest.getAddress());
	      emp.setMoney(empRequest.getMoney());
	      emp.setCustomer(empRequest.getCustomer());
	      emp.setUpdateday(currentTime);
	      empRepository.save(emp);
	      }
	   public void empdelete(EmployeeRequest empRequest) {
	        Employee emp = findById(empRequest.getEid());
	        emp.setEid(empRequest.getEid());
	        emp.setState(1);
	        empRepository.save(emp);
	      }












	   //员工登录
	   //dasdasd/dskfsdkf
	   //sdlkfsdklmfsd
	   public void create(EmployeeRequest empRequest) {
	        empRepository.save(Createemp(empRequest));
	    }

	   private Employee Createemp(EmployeeRequest empRequest) {


	        Employee emp = new Employee();
	        emp.setDep_id(empRequest.getDep_id());
	        emp.setEmp_name(empRequest.getEmp_name());
	        emp.setAddress(empRequest.getAddress());
	        emp.setMoney(empRequest.getMoney());
	        emp.setCustomer(empRequest.getCustomer());
	        emp.setState(0);
	        emp.setUpdateday(currentTime);


	        return emp;
	    }
	   public void baocun(EmployeeRequest empRequest) {
	        empRepository.save(Empbaocun(empRequest));
	    }
	   private Employee Empbaocun(EmployeeRequest empRequest) {


	        Employee emp = new Employee();
	        emp.setDep_id(empRequest.getDep_id());
	        emp.setEmp_name(empRequest.getEmp_name());
	        emp.setAddress(empRequest.getAddress());
	        emp.setMoney(empRequest.getMoney());
	        emp.setCustomer(empRequest.getCustomer());
	        emp.setState(2);
	        emp.setUpdateday(currentTime);
	        return emp;
	    }
	   public List<Employee> findBaocun(Long eid){
			 return empRepository.baocun(eid);

		 }
	   public void empshanchu() {

	        empRepository.shanchu();
	      }
	   public List<Long> findempid(Long dep_id){
			 return empRepository.finddepid(dep_id);

		 }


 }
