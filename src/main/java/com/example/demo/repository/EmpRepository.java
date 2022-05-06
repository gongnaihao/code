package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Employee;

@Repository
public interface EmpRepository extends JpaRepository<Employee,Long> {

	@Query(value="select e.id,e.emp_name,e.address,e.money,e.customer,e.dep_id,"
			+ "e.state,e.updateday,d.dpt_id,d.dpt_name"
			+ " from sampledb.employee e "
			+ " left join sampledb.department d on e.dep_id = d.dpt_id "
			+ " where d.dpt_id = ?1",
			countQuery = "select e.id,e.emp_name,e.address,e.money,e.customer,e.dep_id,"
					+ "e.state,e.updateday,d.dpt_id,d.dpt_name"
					+ " from sampledb.employee e "
					+ " left join sampledb.department d on e.dep_id = d.dpt_id "
					+ " where d.dpt_id = ?1",nativeQuery = true
			)

	Page<Employee> findBydptname (Long did,Pageable pageable);




	@Query(value="select e.id,e.emp_name,e.address,e.money,e.customer,e.dep_id,"
			+ "e.state,e.updateday,d.dpt_id,d.dpt_name"
			+ " from sampledb.employee e "
			+ " left join sampledb.department d on e.dep_id = d.dpt_id "
			+ " where e.state=0 and d.dpt_id = ?1",
			countQuery ="select e.id,e.emp_name,e.address,e.money,e.customer,e.dep_id,"
					+ "e.state,e.updateday,d.dpt_id,d.dpt_name"
					+ " from sampledb.employee e "
					+ " left join sampledb.department d on e.dep_id = d.dpt_id "
					+ " where e.state=0 and d.dpt_id = ?1", nativeQuery=true )

	Page<Employee> findzaizhi (Long did,Pageable pageable);
	@Query(value="select e.id,e.emp_name,e.address,e.money,e.customer,e.dep_id,"
			+ "e.state,e.updateday,d.dpt_id,d.dpt_name"
			+ " from sampledb.employee e "
			+ " left join sampledb.department d on e.dep_id = d.dpt_id "
			+ " where e.state=1 and d.dpt_id = ?1", nativeQuery=true )

	List<Employee> findtuizhi (Long did);

	@Query(value="select id,emp_name,address,money,customer,dep_id,"
			+ "state,updateday"
			+ " from sampledb.employee "
			+ " where state=2", nativeQuery=true )

	List<Employee> baocun (Long eid);

	@Transactional
	@Modifying
	@Query(value="delete from sampledb.employee e where e.state=2", nativeQuery=true )

	public void shanchu ();



	@Query(value="select dep_id "
			+ " from sampledb.employee "
			+ " where state=2", nativeQuery=true )

	List<Long> finddepid (Long dep_id);

















	@Query(value="select d.dpt_name,e.dep_id,d.dpt_id "
			+ " from sampledb.department d "
			+ " left join sampledb.employee e on e.dep_id = d.dpt_id"
			+ "  where d.dpt_id = ?1 ", nativeQuery=true )

	String findByName (String name);

}
