/**
 * 
 * Copyright (c) 2014 Nitesh Morajkar
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE. 
 * 
 */
package com.nitesh.timetogo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author Nitesh Morajkar
 * 
 * TimeToGo class converts date(YYYY-mm-dd)  to 'time to go' i.e 10 days to go format 
 */
public class TimeToGo {
	
	/**
	 * 
	 * @param targetDate - Provide the date you want to convert into 'time to go' format
	 * @return String - Returns date in 'time to go' format
	 */
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
			str += "to go";
		}
		if(arg[0].equals("current")) {
			str = "Today";
		}
		return str;
	}
}
