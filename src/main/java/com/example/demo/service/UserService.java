package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

/**
 * ユーザー情報 Service
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {

    /**
     * ユーザー情報 Repository
     */
    @Autowired
    UserRepository userRepository;

    /**
     * ユーザー情報 全検索
     * @return 検索結果
     */
    public List<User> searchAll() {
        return userRepository.findAll();
    }

    /**
     * ユーザー情報新規登録
     * @param user ユーザー情報
     */
    public void create(UserRequest userRequest) {
        userRepository.save(CreateUser(userRequest));
    }

    /**
     * ユーザーTBLエンティティの生成
     * @param userRequest ユーザー情報リクエストデータ
     * @return ユーザーTBLエンティティ
     */
    private User CreateUser(UserRequest userRequest) {

        User user = new User();
        user.setUserid(userRequest.getUserid());
        user.setName(userRequest.getName());
        user.setPassword(userRequest.getPassword());
        user.setAddress(userRequest.getAddress());
        user.setPhone(userRequest.getPhone());
        user.setDay(userRequest.getDay());


        return user;
    }
    public User findById(Long id) {
        return userRepository.findById(id).get();
      }

    public void update(UserUpdateRequest userUpdateRequest) {
        User user = findById(userUpdateRequest.getId());
        user.setAddress(userUpdateRequest.getAddress());
        user.setName(userUpdateRequest.getName());
        user.setPhone(userUpdateRequest.getPhone());
        user.setUserid(userUpdateRequest.getUserid());
        userRepository.save(user);
      }
    public void delete(Long id) {
        User user = findById(id);
        userRepository.delete(user);
      }
    public List<User> withUsernameQuery(String name) {
		return userRepository.withUsernameQuery(name);
	}

	/**
	 * 自定义的一个根据password来查用户的方法
	 */
	public List<User> withUseridQuery(String userid){
		return userRepository.withUseridQuery(userid);
	}

	/**
	 * 根据id删除一个User
	 * @param name
	 * @return
	 */
	public List<User> withPhoneQuery(String phone){
		return userRepository.withPhoneQuery(phone);
	}
}