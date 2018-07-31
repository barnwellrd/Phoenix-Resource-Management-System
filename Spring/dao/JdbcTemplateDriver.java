package dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;



public class JdbcTemplateDriver {
    ApplicationContext context;
    JdbcTemplate jtemp;
    public JdbcTemplateDriver(){
        context = new ClassPathXmlApplicationContext("spring-dao.xml");
        jtemp = (JdbcTemplate)context.getBean("jt");
    }
    
    public void testResources(){
    	ResourcesJdbcTemplate resJTemp = new ResourcesJdbcTemplate();
    	resJTemp.setJtemp(jtemp);
//    	List<Resources> resourcesList;
//    	
//    	Resources res = (Resources) context.getBean("resource");
//    	
//    	System.out.println("Before Insert");
//    	
//    	resJTemp.insertResource(res);
//    	
//    	
//    	resourcesList = resJTemp.getAllResources();
//    	
//    	
//    	for(Resources resource:resourcesList){
//    		System.out.println(resource.toString());
//    	}
//    	res.setResourceName("Majde's Room");
//    	
//    	System.out.println("Before update");
//    	resJTemp.updateResource(res);
//    	
//    	resourcesList = resJTemp.getAllResources();
//    	
//    	
//    	for(Resources resource:resourcesList){
//    		System.out.println(resource.toString());
//    	}
//
//    	System.out.println("Before Delete");
//    	resJTemp.deleteResource(2001);
//    	
//    	resourcesList = resJTemp.getAllResources();
//    	
//    	for(Resources resource:resourcesList){
//    		System.out.println(resource.toString());
//    	}
    	
    	System.out.println("Searching for ID");
    	Resources foundResource = resJTemp.searchResource(1001);
    	System.out.println(foundResource.toString());
    	
    }
    
    
    
    public static void main(String args[]){
    		new JdbcTemplateDriver().testResources();
    	
        
    }
    
    
}
