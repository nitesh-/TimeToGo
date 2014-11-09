package com.nitesh.timetogo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeToGo {
	
	public static String getTimeLeft(String targetDate) {
		//String targetDate = "2014-11-11";
		String targetDateArray[] = targetDate.split("-");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String todayDate = sdf.format(new Date());
		Date todayObj = null;
		try {
			todayObj = sdf.parse(todayDate);
		} catch(Exception e) {}
  		Date targetDateObj = null;
  		try {
  			targetDateObj = sdf.parse(targetDate);
  		} catch(Exception e) {}

  		if (todayObj.compareTo(targetDateObj) < 0) {
			Calendar cal = Calendar.getInstance();

			int currentMonth = cal.get(Calendar.MONTH)+1;
			int targetMonth = Integer.parseInt(targetDateArray[1]);
			
			int currentDay = cal.get(Calendar.DAY_OF_MONTH);
			int targetDay = Integer.parseInt(targetDateArray[2]);

			int totalDaysCurrentMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			
			int daysLeft =  totalDaysCurrentMonth - currentDay;


			int monthsLeft = targetMonth - currentMonth;
			String[] op = null;
			if(currentMonth == targetMonth) {
				if(targetDay != currentDay) {
					daysLeft = targetDay-currentDay;
					op = new String []{Integer.toString(daysLeft), ""};
				} else {
					op = new String []{"current", "current"};
				}
			} else if(currentMonth == targetMonth-1) {
				daysLeft = daysLeft+targetDay;

				if(daysLeft > 30) {
					int m = daysLeft%30;
					op = new String []{Integer.toString(m), "1"};
				} else {
					op = new String []{Integer.toString(daysLeft), ""};
				}
				
				
			} else {
				op = new String []{"", Integer.toString(monthsLeft)};
			}
			
			
			return formatStr(op);
		} else if(todayObj.compareTo(targetDateObj) == 0) {
			return "Today";
		} else {
			return "";
		}
	}

	private static String formatStr(String[] arg) {
		String str = "";
		if(!arg[1].equals("")) {
			String postFix = (arg[1].equals("1")) ? "month" : "months";
			str += arg[1] + " " + postFix + " ";	
		}

		if(!arg[0].equals("")) {
			String postFix = (arg[0].equals("1")) ? "day" : "days";
			str += arg[0] + " " + postFix + " ";
		}
		
		if(!str.equals("")) {
			str += "left";
		}
		if(arg[0].equals("current")) {
			str = "Today";
		}
		return str;
	}
}
