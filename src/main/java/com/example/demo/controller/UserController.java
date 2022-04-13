package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

/**
 * ユーザー情報 Controller
 */
@Controller
public class UserController {

    /**
     * ユーザー情報 Service
     */
    @Autowired
    private UserService userService;
    /**
     * ユーザー情報一覧画面を表示
     * @param model Model
     * @return ユーザー情報一覧画面
     */
    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public String displayList(Model model) {
        List<User> userlist = userService.searchAll();
        model.addAttribute("userlist", userlist);
        model.addAttribute("user", new User());
        return "user/list";
    }


    /**
     * ユーザー新規登録画面を表示
     * @param model Model
     * @return ユーザー情報一覧画面
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    public String displayAdd(Model model) {
        model.addAttribute("userRequest", new UserRequest());
        return "user/login";
    }




    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String create(@Validated @ModelAttribute UserRequest userRequest,BindingResult br, Model model) {
        // ユーザー情報の登録
    	if(br.hasErrors()) {
    		return "user/login";
    	}else{
        userService.create(userRequest);
        return "redirect:/user/list";
}
    }

    /**
     * @param userRequest リクエストデータ
     * @param model Model
     * @return ユーザー情報一覧画面
     */

    @GetMapping("/user/{id}")
    public String toUpdate(@PathVariable Long id, Model model) {
      User user = userService.findById(id);
      UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
      userUpdateRequest.setId(user.getId());
      userUpdateRequest.setUserid(user.getUserid());
      userUpdateRequest.setName(user.getName());
      userUpdateRequest.setPhone(user.getPhone());
      userUpdateRequest.setAddress(user.getAddress());
      model.addAttribute("userData", userUpdateRequest);
      return "user/update";
    }

    /**
     * ユーザー更新
     * @param userRequest リクエストデータ
     * @param model Model
     * @return ユーザー情報詳細画面
     */
    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public String update( @ModelAttribute UserUpdateRequest userUpdateRequest,Model model) {


      // ユーザー情報の更新
      userService.update(userUpdateRequest);
      return "redirect:/user/list";
    }
    /**
     * ユーザー削除
     * @param userRequest リクエストデータ
     * @param model Model
     * @return ユーザー情報詳細画面
     */
    @GetMapping("/user/{id}/delete")
    public String delete(@PathVariable Long id, Model model) {
      // ユーザー情報の削除
      userService.delete(id);
      return "redirect:/user/list";
    }

    @RequestMapping(value = "/user/result", method = RequestMethod.POST)
	public String showbyname(@ModelAttribute User user, Model model) {
//    	String name = user.getName();
//		List<User> u = userService.withUsernameQuery(name);
//		model.addAttribute("name", u);
//
//		String phone = user.getName();
//		List<User> p = userService.withPhoneQuery(phone);
//		model.addAttribute("name", p);
//		String userid = user.getUserid();
//		List<User> uid = userService.withUseridQuery(userid);
//		model.addAttribute("name", uid);


//
    	List<User> userlist = new ArrayList<>();
//    	 if (user.getName() != null && !user.getName().equals("")) {
//            userlist.addAll( userService.withUsernameQuery(user.getName()));
//
//         }
//    	 if (user.getUserid() != null && !user.getUserid().equals("")) {
//             userlist.addAll( userService.withUseridQuery(user.getUserid()));
//          }
//    	 if (user.getPhone() != null && !user.getPhone().equals("")) {
//             userlist.addAll( userService.withPhoneQuery(user.getPhone()));
//          }
//    	 for(int i=0;i<=userlist.size();i++) {
//    		 if (!userlist.get(i).equals(userlist.get(0))  ) {
//    			 userlist.remove(i);
//    		 }
//    	 }
    	  userlist.addAll( userService.withAllQuery(user.getName(),  user.getUserid(),user.getPhone()));
    	 model.addAttribute("name", userlist);

		return "user/result";

		}



  }