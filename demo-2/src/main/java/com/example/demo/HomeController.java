package com.example.demo;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.example.demo.model.Alien; 
import com.example.demo.dao.*;

//use restcontroller if dont want to use response body every time
@Controller
public class HomeController {
	
	@Autowired
	AlienRepo repo;
	
	
	@RequestMapping("/home")
	public 	String home() {
		System.out.println("hi my name is nitesh ");
		Alien a1 = new Alien();
		return "home.jsp";

	}
	//when sending raw data use annotaion requestbody
	
	//to add alien via postman
	@PostMapping("/alien")
	public Alien addAliens(Alien alien)
	{
		repo.save(alien);
		return alien;
	}
	
	
	//save alien data 
	@RequestMapping("/addAlien")
	public String addAlien(Alien alien)
	{
		repo.save(alien);
		return "home.jsp";
	}
	
	
	
	@RequestMapping("/getAlien")
	public ModelAndView getAlienss(@RequestParam int aid)
	{
		ModelAndView mv= new ModelAndView("nitesh.jsp");
		Alien alien= repo.findById(aid).orElse(new Alien());
		mv.addObject(alien);
		return mv;
	}
	//jpql
	
	//this is used as rest , rest url
	@GetMapping(path="/aliens")
	@ResponseBody
	public List<Alien> getAliens()
	{
		
		
		return repo.findAll();
		
	}
	
	
	@RequestMapping("/alien/{aid}")
	@ResponseBody
	public Optional<Alien> getAlien(@PathVariable("aid") int aid)
	{
		
		
		return repo.findById(aid);
		
	}
	//delete
	@DeleteMapping("/alien/{aid}")
	public String deleteAlien(@PathVariable int aid) {
		
		Alien a= repo.getOne(aid);
		repo.delete(a);
		return "deleted";
		
	}
	
	//update 
	@PutMapping("/alien")
	public Alien saveOrUpdateAliens(Alien alien)
	{
		repo.save(alien);
		return alien;
	}
	
	
	
	
	 
	
}
