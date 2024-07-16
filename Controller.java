package com.webservice.RESTAPI.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webservice.RESTAPI.model.RestUser;

@RestController
@RequestMapping("/users")
public class Controller {
	
	Map<String,RestUser> allUsers=new HashMap<>();	
	@GetMapping
	public Collection<RestUser> getMethod(){
		return allUsers.values();
	}
	@PostMapping
	public String postMethod(@RequestBody RestUser userInput) {
		
		RestUser addValue = new RestUser();
		addValue.setUserid(userInput.getUserid());
		addValue.setName(userInput.getName());
		addValue.setEmail(userInput.getEmail());
		
		allUsers.put(userInput.getUserid(), addValue);
		
		
		return"User added Successfully";
	} 
	
	@PutMapping(path="/{userid}")
	public String putMethod(@PathVariable String userid,@RequestBody RestUser userInput) {
		
		if(allUsers.containsKey(userid)) {
			RestUser restUser = new RestUser();
			restUser.setUserid(userInput.getUserid());
			restUser.setName(userInput.getName());
			restUser.setEmail(userInput.getEmail());
			
			allUsers.put(userid, restUser);
			
			return "Details updated Successfully for user_id: "+userid;
		}
		else {
			return "User Not found";
		}
		
	}
	@DeleteMapping(path="/{userid}")
	public String deleteMethod(@PathVariable String userid) {
		if(allUsers.containsKey(userid)) {
			allUsers.remove(userid);
		}
		else {
			return "User Doesn't Exsits";
		}
		return"User deleted Successfully";
	}
}
