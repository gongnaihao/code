package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.EmployeeRequest;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.service.DepService;
import com.example.demo.service.EmpService;
@Controller
public class DepController {
	@Autowired
    private DepService depService;
	@Autowired
	 private EmpService empService;


	@RequestMapping(value = "/dep/deplist", method = RequestMethod.GET)
	  public String depList(Model model,Long did, Employee employee) {

		List<Department> deplist=depService.searchdepAll(did);


		 model.addAttribute("deplist", deplist);
		 List<Department> clist=depService.searchabc(did);


		 model.addAttribute("clist", clist);
		 model.addAttribute("did", did);

	    return "dep/deplist";

	  }
	  /**
	   * ユーザー情報詳細画面を表示
	   * @param id 表示するユーザーID
	   * @param model Model
	   * @return ユーザー情報詳細画面
	   */
	  @GetMapping("/dep/{did}")
	  public String tohensyu(@PathVariable Long did,String name,
			  Employee emp,
			  Department dep,Model model,@PageableDefault(size = 5, sort = {"id"}) Pageable pageable) {

		  	Page<Employee> emplist =empService.seachByname(did, pageable);
	  		Department depname=depService.findById(did,name);
		    model.addAttribute("emplist", emplist);
		    model.addAttribute("depname", depname.getDpt_name());
			model.addAttribute("emp", new EmployeeRequest());
			model.addAttribute("dep", new Department());
			Page<Employee> zaizhi =empService.seachzaizhi(did,pageable);
			List<Employee> tuizhi =empService.seachtuizhi(did);
			 model.addAttribute("zaizhi", zaizhi);
			 model.addAttribute("tuizhi",tuizhi);
		  return "dep/depresult";
	  }
	    //編集画面
	    //
	    //
	  @RequestMapping("/emp/{eid}")
	    public String toUpdate(@PathVariable Long eid, Model model) {
	    	Employee emp = empService.findById(eid);
	    	EmployeeRequest empRequest = new EmployeeRequest();
	    	empRequest.setEid(emp.getEid());
	    	empRequest.setAddress(emp.getAddress());
	        empRequest.setMoney(emp.getMoney());
	        empRequest.setCustomer(emp.getCustomer());
	        model.addAttribute("empData", empRequest);

	      return "dep/hensyu";
	    }
	    @RequestMapping(value = "/dep/hensyu", method = RequestMethod.POST)
	    public String update( @ModelAttribute EmployeeRequest empRequest,Model model,Long eid) {
	    	empService.empupdate(empRequest);
	    	Employee emp = empService.findById(eid);
	    	Long did=emp.getDep_id();
		       String url="redirect: "+did;
	    	return url;
	    }

	    //员工删除
	    @RequestMapping("/dep/{eid}")
	    public String deleteEmployee(@PathVariable("eid") Long eid,RedirectAttributes attr){
	    	EmployeeRequest empRequest = new EmployeeRequest();
	    	Employee emp = empService.findById(eid);
	    	empRequest.setEid(emp.getEid());
	    	empRequest.setDep_id(emp.getDep_id());
	    	empService.empdelete(empRequest);
	    	Long did=emp.getDep_id();
	       String url="redirect: "+did;
	        return url;
	    }
	    @RequestMapping(value = "depresult", method = RequestMethod.POST)
	    public String delete(@ModelAttribute EmployeeRequest emp,
	    		Department dep,Model model, Long dep_id) {

	        return "deplist";

	    }













	    @RequestMapping(value = "/dep/emplogin", method = RequestMethod.GET)
	    public String displayAdd( @ModelAttribute EmployeeRequest empRequest,Long eid,Long dep_id,Model model) {
	    	List<Employee> emplist=empService.findBaocun(eid);
	    	List<Long> idlist=empService.findempid(dep_id);
			 model.addAttribute("list", emplist);
			 model.addAttribute("idlist", idlist);
	        return "dep/emplogin";
	    }

/*
	新建
*/


	    @RequestMapping(value = "/dep/create", method = RequestMethod.POST)

	    public String create( @ModelAttribute EmployeeRequest empRequest,
	    		HttpServletRequest request, HttpServletResponse response,
	    		Long[] depid,String[] ename,String[] eaddress,Integer[] emoney,
	    		String[] ecustomer,
	    		Model model) {


	    	 createemp(empRequest, request, response, depid, ename, eaddress, emoney, ecustomer, model);
	    	 empService.empshanchu();
	    	return "redirect:/dep/deplist";
	}
	    @ResponseBody
	    public String createemp(@Validated @ModelAttribute EmployeeRequest empRequest,
	    		HttpServletRequest request, HttpServletResponse response,
	    		Long[] depid,String[] ename,String[] eaddress,Integer[] emoney,
	    		String[] ecustomer,
	    		Model model) {

	    	List <EmployeeRequest> emp = new ArrayList<EmployeeRequest>();
	    	for(int i=0;i<depid.length;i++) {

	    		empRequest.setDep_id(depid[i]);
	    		empRequest.setEmp_name(ename[i]);
	    		empRequest.setAddress(eaddress[i]);
	    		empRequest.setMoney(emoney[i]);
	    		empRequest.setCustomer(ecustomer[i]);
	    		emp.add(empRequest);
    		empService.create(empRequest);
	    	}
	    	return "";
	    }

/*
	保存
*/
	    @RequestMapping(value = "/dep/baocun", method = RequestMethod.POST)

	    public String baocun( @ModelAttribute


	    		EmployeeRequest empRequest,
	    		Long[] depid,String[] ename,String[] eaddress,Integer[] emoney,
	    		String[] ecustomer,
	    		Model model) {
    HttpServletRequest requesta = null;
    HttpServletResponse response= null;
	    	 empbaocun(empRequest, requesta, response, depid, ename, eaddress, emoney, ecustomer, model);

	    	return "redirect:/dep/emplogin";
	}
	    @ResponseBody
	    public String empbaocun(@ModelAttribute EmployeeRequest empRequest,
	    		HttpServletRequest requesta, HttpServletResponse response,
	    		Long[] depid,String[] ename,String[] eaddress,Integer[] emoney,
	    		String[] ecustomer,
	    		Model model) {
	    	empService.empshanchu();
	    	List <EmployeeRequest> emp = new ArrayList<EmployeeRequest>();
	    	for(int i=0;i<depid.length;i++) {

	    		empRequest.setDep_id(depid[i]);
	    		empRequest.setEmp_name(ename[i]);
	    		empRequest.setAddress(eaddress[i]);
	    		empRequest.setMoney(emoney[i]);
	    		empRequest.setCustomer(ecustomer[i]);
	    		emp.add(empRequest);
    		empService.baocun(empRequest);
	    	}
	    	return "abc";
	    }
}



























