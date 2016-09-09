package com.realdolmen.web.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Named;

@Named
public class DateUtilBean implements Serializable {

	Calendar c = Calendar.getInstance();

	public String getFullDate(Date date) {
		return getDayOfWeek(date) + " " + getDayOfMonh(date) + " " + getMonth(date) + " " + getYear(date);
	}

	public String getDayOfWeek(Date date) {
		c.setTime(date);
		int day = c.get(Calendar.DAY_OF_WEEK);
		String dayOfWeek;

		switch (day) {
		case 1:
			dayOfWeek = "sun";
			break;
		case 2:
			dayOfWeek = "mon";
			break;
		case 3:
			dayOfWeek = "tue";
			break;
		case 4:
			dayOfWeek = "wed";
			break;
		case 5:
			dayOfWeek = "thu";
			break;
		case 6:
			dayOfWeek = "fri";
			break;
		case 7:
			dayOfWeek = "sat";
			break;
		default:
			dayOfWeek = "unknown";
		}
		return dayOfWeek;
	}

	public String getTime(Date date) {
		SimpleDateFormat printFormat = new SimpleDateFormat("HH:mm:ss");
		return printFormat.format(date);
	}

	public int getDayOfMonh(Date date) {
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	public String getMonth(Date date) {
		c.setTime(date);
		int i = c.get(Calendar.MONTH);
		String month;

		switch (i) {
		case 0:
			month = "jan";
			break;
		case 1:
			month = "feb";
			break;
		case 2:
			month = "mar";
			break;
		case 3:
			month = "apr";
			break;
		case 4:
			month = "may";
			break;
		case 5:
			month = "jun";
			break;
		case 6:
			month = "jul";
			break;
		case 7:
			month = "aug";
			break;
		case 8:
			month = "sep";
			break;
		case 9:
			month = "oct";
			break;
		case 10:
			month = "nov";
			break;
		case 11:
			month = "dec";
			break;
		default:
			month = "";

		}
		return month;
	}

	public int getYear(Date date) {
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

}
