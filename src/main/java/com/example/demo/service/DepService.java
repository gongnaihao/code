package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Department;
import com.example.demo.repository.DepRepository;

@Service
public class DepService {

	@Autowired
	 DepRepository depRepository;

	 	public List<Department> searchdepAll(Long did) {
	        return depRepository.finddepAll(did);
	    }
	    public Department findById(Long did,String name) {

	        return depRepository.findById(did).get();

	      }
	    public List<Department> searchabc(Long did) {
	        return depRepository.findabc(did);
	    }



//	    public void update(DepUpdate depUpdate) {
//	        Department dep = findById(depUpdate.getId());
//	          dep.setId(depUpdate.getId());
//		      dep.setDpt_id(depUpdate.getDpt_id());
//		      dep.setDpt_name(depUpdate.getDpt_name());
//		      dep.setAll_money(depUpdate.getAll_money());;
//		      dep.setNumber(depUpdate.getNumber());
//		      dep.setAug_money(depUpdate.getAug_money());
//		      depRepository.save(dep);
//	      }





}
