package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

/**
 * ユーザー情報 Repository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT id FROM User id WHERE userid LIKE %?1%")
	List<User> withUseridQuery(String userid);


	@Query("SELECT p FROM User p WHERE name LIKE %?1%")
	List<User> withUsernameQuery(String name);


	@Query("SELECT pho FROM User pho WHERE phone LIKE %?1%")
	List<User> withPhoneQuery(String phone);

	 @Query(value ="select u from User u where u.name like %?1% "
	 		+ "AND u.userid like %?2%"
	 		+ "AND u.phone like %?3%" )
	    List<User> findA(String name,String userid,
	           String phone);

}