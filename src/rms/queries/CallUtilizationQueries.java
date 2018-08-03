package rms.queries;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CallUtilizationQueries {
	public static void main(String[] args) {
		
	}
	public Double callDailyUtilizationByResourceId(int resourceId, Date date)
    {		
        return new UtilizationQueries().dailyUtilizationByResourceId(resourceId, formatDate(date));		
    }
	
	public Double callWeeklyUtilizationByResourceId(int resourceId, Date startDate)
	{
        Date endDate = addWeek(startDate);
		return new UtilizationQueries().weeklyUtilizationByResourceId(resourceId, formatDate(startDate), formatDate(endDate));
	}
	
	public Double callMonthlyUtilizationByResourceId(int resourceId, Date startDate)
	{
		Date endDate = addMonth(startDate);
		return new UtilizationQueries().monthlyUtilizationByResourceId(resourceId, formatDate(startDate), formatDate(endDate));
	}
	
	public Double callDailyUtilizationByResourceTypeId(int resourceTypeId, Date date)
	{
		return new UtilizationQueries().dailyUtilizationByResourceTypeId(resourceTypeId, formatDate(date));	
	}
	
	public Double callWeeklyUtilizationByResourceTypeId(int resourceTypeId, Date startDate)
	{
		
		Date endDate = addWeek(startDate);
		return new UtilizationQueries().weeklyUtilizationByResourceTypeId(resourceTypeId, formatDate(startDate), formatDate(endDate));
	}
	
	public Double callMonthlyUtilizationByResourceTypeId(int resourceTypeId, Date startDate)
	{
		Date endDate = addMonth(startDate);
		return new UtilizationQueries().monthlyUtilizationByResourceTypeId(resourceTypeId, formatDate(startDate), formatDate(endDate));
	}
	
	public Double callDailyUtilizationForAllResources(Date date) {
		
		return new UtilizationQueries().dailyUtilizationForAllResources(formatDate(date));
		
	}
	
	public Double callWeeklyUtilizationForAllResources(Date startDate) {
		Date endDate = addWeek(startDate);
		return new UtilizationQueries().weeklyUtilizationForAllResources(formatDate(startDate), formatDate(endDate));
	}
	
	public Double callMonthlyUtilizationForAllResources(Date startDate) {
		Date endDate = addMonth(startDate);
		return new UtilizationQueries().monthlyUtilizationForAllResources(formatDate(startDate), formatDate(endDate));
	}
	
	public String formatDate(Date date) {
		SimpleDateFormat ddmmyyyyy = new SimpleDateFormat("dd-MM-yyyy");

		return ddmmyyyyy.format(date) +" 00:00";
	}
	
	public Date addMonth(Date startDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.add(Calendar.MONTH, 1);

		return calendar.getTime();
	}
	
	public Date addWeek(Date startDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.add(Calendar.DAY_OF_YEAR, 7);

		return calendar.getTime();
	}

}
