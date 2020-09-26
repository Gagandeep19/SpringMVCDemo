package com.springLearning.springmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;	
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springLearning.springmvc.dao.AlienDao;
import com.springLearning.springmvc.model.*;

@Controller
public class HomeController {

	@Autowired
	private AlienDao dao;
	
	
	@RequestMapping("/")
	public String home() {
		System.out.println("Home page requested");
		return "index";
	}
	
	
	@ModelAttribute     
	public void modelData(Model m) {
		
		m.addAttribute("user", "gagan");

	}
	
	@GetMapping("getAliens")
	public String getAliens(Model m)
	{
		
		m.addAttribute("result",dao.getAliens() );
		
		return "showAliens";
	}
	
	@GetMapping("getAlien")     
	public String getAlien(@RequestParam int aid, Model m) {
		
		m.addAttribute("result",dao.getAlien(aid) );
		
		return "showAliens";
	}
	
	@RequestMapping("addAlien")     
	public String addAlien(@ModelAttribute("result") Alien a) {
		
		dao.addAlien(a);
		
		return "showAliens";
	}
	
	
	
	/*// This is old just to print the result data on the screen in the prvious session
	@RequestMapping("addAlien")     
	public String addAlien(@ModelAttribute("a1") Alien a) {
		
		return "Result";
	}
	*/
	
	
	/*
	  
	 
	 
	 * ***********************************************************************************************************************
	 * 		Below is the  method of using ModelMap using RequestPAram and CLass  - Type 1 - ModelAndView              *
	 * ***********************************************************************************************************************
	
	@RequestMapping("addAlien")     
	public String addAlien(@RequestParam("aid") int aid , @RequestParam("aname") String aname, ModelMap m) {
		
		Alien a = new Alien();
		a.setAid(aid);
		a.setAname(aname);
		
		m.addAttribute("alien", a);
		return "Result";
	}
	*/
	
	
	

	@RequestMapping("add")     
	public String add(@RequestParam("num1") int i , @RequestParam("num2") int j, ModelMap m) {
		
		int k = i+j;
		m.addAttribute("result", k);
		
		return "Result";
	}
	
	
	
	/*
	  
	 
	 
	 * *******************************************************************************************
	 * 				Below is the basic method of processing  - Type 2 - ModelAndView              *
	 * *******************************************************************************************
	  
	@RequestMapping("add")     TYPE 3 - ModelAndView
	public ModelAndView add(@RequestParam("num1") int i , @RequestParam("num2") int j) {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Result");
		
		int k = i+j;
		mv.addObject("result", k);
		
		return mv;
	}
	
	*/
	
	/*
	 
	 
	  
	 * *******************************************************************************************
	 * 				Below is the basic method of processing  - Type 2 - HTTPSESSION              *
	 * *******************************************************************************************
	 
	@RequestMapping("add") 
	public String add(@RequestParam("num1") int i , @RequestParam("num2") int j, HttpSession session) {
		
		int k = i+j;
		
		session.setAttribute("result", k);
		
		return"Result.jsp";
	}
	*/
	
	/* 
	 
	  
	 * *****************************************************************************
	 * 				Below is the basic method of processing  - Type 1              *
	 * *****************************************************************************
	 
	@RequestMapping("add")
	public String add(HttpServletRequest req,HttpServletResponse res) {
		int i = Integer.parseInt(req.getParameter("num1"));
		int j = Integer.parseInt(req.getParameter("num2"));
		
		int k = i+j;
		
		HttpSession session = req.getSession();
		
		session.setAttribute("result", k);
		
		return"Result.jsp";
	}
	
	*/
	
	
	
	
	
}
