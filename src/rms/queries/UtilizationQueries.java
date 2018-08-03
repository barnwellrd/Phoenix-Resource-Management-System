package rms.queries;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import rms.mapper.BookingsMapper;
import rms.model.Bookings;

public class UtilizationQueries
{
	private ApplicationContext context;
	private JdbcTemplate jtemp;
	
	public UtilizationQueries()
	{
        this.context = new ClassPathXmlApplicationContext("spring-dao.xml");
        this.jtemp = (JdbcTemplate)context.getBean("jt");
	}
	public static void main(String args[]) {
//		System.out.println(new UtilizationQueries().dailyUtilizationByResourceId(1002, "28-07-2018 00:00"));
//		System.out.println(new UtilizationQueries().weeklyUtilizationByResourceId(1002, "22-07-2018 00:00", "29-07-2018 00:00"));
//		System.out.println(new UtilizationQueries().monthlyUtilizationByResourceId(1002, "22-07-2018 00:00", "29-07-2018 00:00"));
//		System.out.println(new UtilizationQueries().dailyUtilizationByResourceTypeId(1002, "28-07-2018 00:00"));
//		System.out.println(new UtilizationQueries().weeklyUtilizationByResourceTypeId(1002, "22-07-2018 00:00", "29-07-2018 00:00"));
//		System.out.println(new UtilizationQueries().monthlyUtilizationByResourceTypeId(1002, "22-07-2018 00:00", "29-07-2018 00:00"));
		//System.out.println(new UtilizationQueries().dailyUtilizationForAllResources("28-07-2018 00:00"));
		//System.out.println(new UtilizationQueries().weeklyUtilizationForAllResources("22-07-2018 00:00","29-07-2018 00:00"));
		System.out.println(new UtilizationQueries().monthlyUtilizationForAllResources("22-07-2018 00:00","29-07-2018 00:00"));
	}
	public Double dailyUtilizationByResourceId(int resourceId, String date)
    {
        return jtemp.queryForObject("SELECT SUM(EXTRACT(hour from (BOOKED_END_TIME - BOOKED_START_TIME)))/8 FROM bookings" + 
                " WHERE resource_id = ? AND TRUNC(booked_start_time) = TO_DATE(?,'dd-MM-yyyy HH24:MI')" + 
                " GROUP BY TRUNC(booked_start_time)", 
                Double.class,
                resourceId,
                date);
		

    }
	
	public Double weeklyUtilizationByResourceId(int resourceId, String startDate, String endDate)
	{

		return jtemp.queryForObject("select sum(extract(hour from (BOOKED_END_TIME-BOOKED_START_TIME)))/40 from bookings " + 
				"where resource_id=? AND trunc(booked_start_time) BETWEEN TO_DATE(?,'dd-MM-yyyy HH24:MI') AND " + 
				"  TO_DATE(?,'dd-MM-yyyy HH24:MI') " + 
				"  group by resource_id",
				Double.class,
				resourceId,
				startDate,
				endDate);
	}
	
	public Double monthlyUtilizationByResourceId(int resourceId, String startDate, String endDate)
	{
		return jtemp.queryForObject("select sum(extract(hour from (BOOKED_END_TIME-BOOKED_START_TIME)))/160 from bookings\r\n" + 
				"where resource_id=? AND trunc(booked_start_time) BETWEEN TO_DATE(?,'dd-MM-yyyy HH24:MI') AND\r\n" + 
				"  TO_DATE(?,'dd-MM-yyyy HH24:MI')\r\n" + 
				"  group by resource_id", 
				Double.class,
				resourceId,
				startDate,
				endDate);
	}
	
	public Double dailyUtilizationByResourceTypeId(int resourceTypeId, String date)
	{
		return jtemp.queryForObject("select sum(extract(hour from (BOOKED_END_TIME-BOOKED_START_TIME)))/(8*(SELECT COUNT(*) FROM RESOURCES WHERE resource_type_id = ?)) from bookings inner join resources using (resource_id)\r\n" + 
				"where resource_type_id=? AND trunc(booked_start_time) = TO_DATE(?,'dd-MM-yyyy HH24:MI')\r\n" + 
				"group by resource_type_id", 
				Double.class,
				resourceTypeId,
				resourceTypeId,
				date);		
	}
	
	public Double weeklyUtilizationByResourceTypeId(int resourceTypeId, String startDate, String endDate)
	{
		return jtemp.queryForObject("select sum(extract(hour from (BOOKED_END_TIME-BOOKED_START_TIME)))/(40*(SELECT COUNT(*) FROM RESOURCES WHERE resource_type_id = ?)) from bookings inner join resources using (resource_id)\r\n" + 
				"where resource_type_id=? AND trunc(booked_start_time) BETWEEN TO_DATE(?,'dd-MM-yyyy HH24:MI') AND\r\n" + 
				"  TO_DATE(?,'dd-MM-yyyy HH24:MI') group by resource_type_id", 
				Double.class,
				resourceTypeId,
				resourceTypeId,
				startDate,
				endDate);
	}
	
	public Double monthlyUtilizationByResourceTypeId(int resourceTypeId, String startDate, String endDate)
	{
		return jtemp.queryForObject("select sum(extract(hour from (BOOKED_END_TIME-BOOKED_START_TIME)))/(160*(SELECT COUNT(*) FROM RESOURCES WHERE resource_type_id = ?)) from bookings inner join resources using (resource_id)\r\n" + 
				"where resource_type_id=? AND trunc(booked_start_time) BETWEEN TO_DATE(?,'dd-MM-yyyy HH24:MI') AND\r\n" + 
				"  TO_DATE(?,'dd-MM-yyyy HH24:MI') group by resource_type_id",
				Double.class,
				resourceTypeId,
				resourceTypeId,
				startDate,
				endDate);
	}
	
	public Double dailyUtilizationForAllResources(String date) {
		return jtemp.queryForObject("select sum((extract(hour from (BOOKED_END_TIME-BOOKED_START_TIME)))/(8*(SELECT COUNT(*) FROM RESOURCES))) from bookings\r\n" + 
				"where trunc(booked_start_time) = TO_DATE(?,'dd-MM-yyyy HH24:MI')",
				Double.class,
				date);
	}
	
	public Double weeklyUtilizationForAllResources(String startDate, String endDate) {
		return jtemp.queryForObject("select sum((extract(hour from (BOOKED_END_TIME-BOOKED_START_TIME)))/(40*(SELECT COUNT(*) FROM RESOURCES))) from bookings\r\n" + 
				"where trunc(booked_start_time) BETWEEN TO_DATE(?,'dd-MM-yyyy HH24:MI') AND TO_DATE(?,'dd-MM-yyyy HH24:MI')",
				Double.class,
				startDate,
				endDate);
	}
	
	public Double monthlyUtilizationForAllResources(String startDate, String endDate) {
		return jtemp.queryForObject("select sum((extract(hour from (BOOKED_END_TIME-BOOKED_START_TIME)))/(160*(SELECT COUNT(*) FROM RESOURCES))) from bookings\r\n" + 
				"where trunc(booked_start_time) BETWEEN TO_DATE(?,'dd-MM-yyyy HH24:MI') AND TO_DATE(?,'dd-MM-yyyy HH24:MI')",
				Double.class,
				startDate,
				endDate);
	}

}

