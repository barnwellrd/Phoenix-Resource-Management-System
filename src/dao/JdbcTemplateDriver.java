package dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import model.Resources;




public class JdbcTemplateDriver {
    ApplicationContext context;
    JdbcTemplate jtemp;
    public JdbcTemplateDriver(){
        context = new ClassPathXmlApplicationContext("spring-dao.xml");
        jtemp = (JdbcTemplate)context.getBean("jt");
    }
    
    public void testResources(){
    	ResourcesJdbcTemplate resJTemp = new ResourcesJdbcTemplate();
    	List<Resources> resourcesList;
    	Resources res = (Resources) context.getBean("resource");
    	
    	System.out.println("Before Insert");
    	resJTemp.insert(res);
    	resourcesList = resJTemp.getAll();

    	for(Resources resource:resourcesList){
    		System.out.println(resource.toString());
    	}
    	res.setResourceName("Majde's Room");
    	
    	System.out.println("Before update");
    	if (resJTemp.update(res) <= 0){
    		System.out.println("id doesn't exist");
    		res.setResourceId(2011);
    		resJTemp.update(res);
    	}
    	
    	resourcesList = resJTemp.getAll();
    	
    	
    	for(Resources resource:resourcesList){
    		System.out.println(resource.toString());
    	}

    	System.out.println("Before Delete");
    	if(resJTemp.delete(2001) <= 0){
    		System.out.println("resource doesn't exit");
    		resJTemp.delete(2011);
    	}
    	
    	resourcesList = resJTemp.getAll();
    	
    	for(Resources resource:resourcesList){
    		System.out.println(resource.toString());
    	}
    	
    	System.out.println("Searching for ID");
    	Resources foundResource = resJTemp.search(1001);
    	System.out.println(foundResource.toString());
    	
    	try{
    	Resources fResource = resJTemp.search(1);
    	}catch (EmptyResultDataAccessException e){
    		System.out.println("doesn't exist");
    	}
    }
    
    
    
    public static void main(String args[]){
    		new JdbcTemplateDriver().testResources();
    	
        
    }
    
    
}
