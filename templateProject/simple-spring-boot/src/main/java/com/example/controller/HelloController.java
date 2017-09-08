package com.example.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.UserRepository;
import com.example.model.User;

@Controller
@RequestMapping("/hello")
//@EnableAutoConfiguration
public class HelloController {

	@RequestMapping("/first")
	public String first(Model model) {
		model.addAttribute("hello", "world");
		return "first";
	}

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/user")
	@ResponseBody
	public User getUserById(Integer id) {
		User u = userRepository.getOne(id);
		System.out.println("userRepository: " + userRepository);
		System.out.println("id: " + id);
		return u;
	}

	@RequestMapping("/saveUser")
	@ResponseBody
	public void saveUser(String userName,String address,String sex) {
		User u = new User();
		u.setUserName("wan");
		u.setAddress("江西省");
		u.setBirthDay(new Date());
		u.setSex("男");
		userRepository.save(u);
	}

}
