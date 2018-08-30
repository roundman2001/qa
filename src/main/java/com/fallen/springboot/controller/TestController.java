package com.fallen.springboot.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fallen.springboot.dao.DocDao;
import com.fallen.springboot.dao.WarningDao;
import com.fallen.springboot.models.TDoc;
import com.fallen.springboot.models.TWarning;

import lombok.extern.slf4j.Slf4j; 
 
@Slf4j
@RestController
public class TestController {
	
	@Autowired
    private WarningDao warningDao;
	
	@Autowired
    private DocDao docDao;

    @GetMapping("/helloworld")
    public String helloworld() { 
    	return "helloworld"; 
    }
    
    @RequestMapping(value = "/search",method = RequestMethod.GET)  
    public Object search(HttpServletRequest request) {
 
    	log.info("search 画面"); 
    	
    	return new ModelAndView("search");  
    }  
    
    @RequestMapping(value = "/dosearch",method = RequestMethod.GET)  
    public Object dosearch(HttpServletRequest request) {  
    	
    	String keyword = request.getParameter("keyword");
    	
    	log.info("search param:" +keyword);  

		if (keyword.isEmpty())
		{ 
			return new ModelAndView("search");
		}		
    	
    	Map<String, Object> model = new HashMap<String, Object>(); 

		model.put("keyword", keyword); 
		
		String sql =" select * from t_doc where MATCH(`title`,body) AGAINST('"+keyword+"' IN BOOLEAN MODE )";

    	List<TDoc> listDoc = (List<TDoc>) docDao.findBySql(sql);
    	List<TDoc> newResultList = new ArrayList<>();
    	
    	String[] keywordArray = keyword.split(" ");
    	
    	for (TDoc tDoc : listDoc) {
			
    		tDoc.setTitle(changeKeyWordStyle(tDoc.getTitle(),keywordArray));
    		tDoc.setBody(changeKeyWordStyle(tDoc.getBody(),keywordArray));
    		
    		newResultList.add(tDoc);
		}
    	
    	model.put("resultList", newResultList);
    	model.put("resultCount", newResultList.size()); 

		return new ModelAndView("searchresult", model);
    }  
    
    String changeKeyWordStyle(String wholeWord,String[] keywordArray)
    {      	
    	String resultWord =wholeWord;
    	for (int i = 0; i < keywordArray.length; i++) {
    		
    		String keyword = keywordArray[i].replaceAll("-", "").replaceAll("\\*", "").replaceAll("\\+", "");
    		
    		if (!keyword.isEmpty())
    			resultWord = resultWord.replaceAll(keyword, "<font style='color:red;background-color: yellow;'>"+keyword+"</font>");
		} 
    	
    	return resultWord;
    }
    
    @RequestMapping(value = "/dosearch2",method = RequestMethod.GET)  
    public Object dosearch2(HttpServletRequest request) {  
    	
    	String keyword = request.getParameter("keyword"); 
    	
    	Map<String, Object> model = new HashMap<String, Object>(); 

		model.put("keyword", keyword); 
		
		String sql =" select * from t_doc where MATCH(`title`,body) AGAINST(:keyword IN BOOLEAN MODE )";

    	List<TDoc> listDoc = (List<TDoc>) docDao.findBySql(sql, model); 

		return listDoc;
    }  
     
	@PostMapping("/getcontent")
	@ResponseBody
    public Object getContent(HttpServletRequest request, @RequestBody Map<String, Object> reqMap) {

    	log.info("warning 画面");

    	String id = reqMap.get("id").toString();
    	
    	TDoc doc = docDao.getOne(Integer.parseInt(id));
    	
        return doc; 
    } 
	
	@PostMapping("/savecontent")
	@ResponseBody
    public Object saveContent(HttpServletRequest request, @RequestBody Map<String, Object> reqMap) {

    	log.info("saveContent ");

    	String content = reqMap.get("content").toString();
    	String title = reqMap.get("title").toString();
    	
    	TDoc doc = new TDoc();
    	
    	if (reqMap.containsKey("id"))
    	{
    		String id = reqMap.get("id").toString();
    		doc = docDao.getOne(Integer.parseInt(id));
    	} 
    	
    	doc.setBody(content);
    	doc.setTitle(title);
    	
    	Timestamp ts = new Timestamp(new Date().getTime());
    	 
    	doc.setCreateTime(ts);
    	
    	docDao.save(doc);
    	
        return "success"; 
    } 
     
	@RequestMapping(value = "/warning",method = RequestMethod.GET)  
    public Object warning() {

    	log.info("warning 画面");

    	
    	List<TWarning> listWarning = (List<TWarning>) warningDao.findAll();
    	
    	TWarning warning = new TWarning(); 
    	warning.setCreateTime(null);
    	warning.setLastUpdate(null); 
    	warning.setStatus("0");
    	warning.setWarningContent("515151sadasd");
    	warningDao.save(warning);
    	
        return listWarning; 
    }  
	
	@RequestMapping(value = "/warning2",method = RequestMethod.GET)  
    public Object warning2() {

    	log.info("warning2 画面");

    	String sql ="select * from t_warning";
    	
    	List<TWarning> listWarning = (List<TWarning>) warningDao.findBySql(sql); 
    	
        return listWarning; 
    }  
	
	
  @RequestMapping(value = "/login",method = RequestMethod.GET)  
    public ModelAndView login() {

    	log.info("login 画面");
    	
    	return   new ModelAndView("login");
    }  
    
    @RequestMapping(value = "/ui-elements",method = RequestMethod.GET)  
    public ModelAndView elements() {

    	log.info("ui-elements 画面");
    	
    	return   new ModelAndView("ui-elements");
    }  
    
    @RequestMapping(value = "/chart",method = RequestMethod.GET)  
    public ModelAndView chart() {

    	log.info("chart 画面");
    	
    	return   new ModelAndView("chart");
    }  
    
    @RequestMapping(value = "/morris-chart",method = RequestMethod.GET)  
    public ModelAndView morrischart() {

    	log.info("morris-chart 画面");
    	
    	return   new ModelAndView("morris-chart");
    }  
    
    @RequestMapping(value = "/tab-panel",method = RequestMethod.GET)  
    public ModelAndView tabpanel() {

    	log.info("tab-panel 画面");
    	
    	return   new ModelAndView("tab-panel");
    }  
    
    @RequestMapping(value = "/table",method = RequestMethod.GET)  
    public ModelAndView table() {

    	log.info("table 画面");
    	
    	return   new ModelAndView("table");
    }  
    
    @RequestMapping(value = "/form")  
    public ModelAndView form() {

    	log.info("form 画面");
    	
    	return   new ModelAndView("form");
    }  
    
    @RequestMapping(value = "/empty",method = RequestMethod.GET)  
    public ModelAndView empty() {

    	log.info("empty 画面");
    	
    	return   new ModelAndView("empty");
    }  
    
     
}