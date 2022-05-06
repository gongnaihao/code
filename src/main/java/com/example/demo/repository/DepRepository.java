package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Department;
@Repository
public interface DepRepository extends JpaRepository<Department,Long> {


	@Query(value="select d.dpt_id,d.dpt_name,sum(e.money) as all_money,e.state, "
			+ "count(e.id) as number,e.dep_id,d.aug_money"
			+ " from sampledb.employee e "
			+ " left join sampledb.department d on e.dep_id = d.dpt_id where e.state=0  GROUP BY d.dpt_id ORDER BY d.dpt_id ", nativeQuery=true)
	List<Department> finddepAll(Long did);






	@Query(value="select d.dpt_id,d.dpt_name,sum(e.money) as all_money,e.state, "
			+ "count(e.id) as number,e.dep_id,d.aug_money,count(e.state) as aug_money "
			+ " from sampledb.employee e "
			+ " left join sampledb.department d on e.dep_id = d.dpt_id ", nativeQuery=true)
	List<Department> findabc(Long did);

















	@Query(value="select dpt_name,dpt_id from department "
			+ " where dpt_id = %1%", nativeQuery=true )

	String findBydptname (String name);
}
