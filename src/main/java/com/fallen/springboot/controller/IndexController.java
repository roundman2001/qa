package com.fallen.springboot.controller;
 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/")
public class IndexController {  
    
    @RequestMapping(value = "/",method = RequestMethod.GET)  
    public ModelAndView index() {

    	log.info("index 画面");
    	
    	return   new ModelAndView("search");
    }  
  
    @GetMapping("/helloworld3")
    public String helloworld3() {
        return "helloworld3";
    }
}