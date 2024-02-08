package com.unicamp.mc322.lab04;

import com.unicamp.mc322.lab04.days.Days;
import com.unicamp.mc322.lab04.days.ServiceDay;

public class HealthCenter {

	private final int MAX_WORK_DAYS = 3;
	private String name;
	private Position address;
	private int maxVacDay;
	private int[] amountVacDay; // amount scheduled Vaccine per day
	private Days[] workDaysArray; // array SORTED asc days

	public HealthCenter(String name, Position address, int maxVacDay, Days[] daysArray) {
		ServiceDay svcDay = new ServiceDay();
		this.name = name;
		this.address = address;
		this.maxVacDay = maxVacDay;
		this.workDaysArray = svcDay.sortDaysArray(daysArray);
		this.amountVacDay = new int[daysArray.length];
		for (int i = 0; i < daysArray.length; i++)
			this.amountVacDay[i] = 0;
	}

	public HealthCenter(String name, Position address, int maxVacDay) {
		Days[] days = new Days[] { Days.MONDAY, Days.TUESDAY, Days.WEDNESDAY };
		this.name = name;
		this.address = address;
		this.maxVacDay = maxVacDay;
		this.workDaysArray = days;
		this.amountVacDay = new int[MAX_WORK_DAYS];
		for (int i = 0; i < MAX_WORK_DAYS; i++)
			this.amountVacDay[i] = 0;
	}

	public String getName() {
		return name;
	}

	public Position getAddress() {
		return address;
	}

	public int getmaxVacDay() {
		return maxVacDay;
	}

	public String scheduleVac() {
		int idxDay = getIdxEarlyDayAvailable();
		String receipt = executeScheduleVac(idxDay);
		return receipt;
	}

	private String executeScheduleVac(int idxDay) {
		String receipt = "";
		ServiceDay svcDay = new ServiceDay();
		if (idxDay != -1) {
			amountVacDay[idxDay] += 1;
			receipt += "Name Health Center: " + name + "\n";
			receipt += "Scheduled day: " + svcDay.getString(workDaysArray[idxDay]) + "\n";
		}
		return receipt;
	}

	private int getIdxEarlyDayAvailable() {
		int idx = -1;
		for (int i = 0; i < workDaysArray.length && idx == -1; i++)
			if (amountVacDay[i] < maxVacDay)
				idx = i;
		return idx;
	}

	protected Days getEarlyWorkDay() {
		return workDaysArray[getIdxEarlyDayAvailable()];
	}

	private String getWorkDaysSchedules() {
		ServiceDay svcDay = new ServiceDay();
		String wordDaysSched = "";
		int available;
		String workDay;

		for (int i = 0; i < workDaysArray.length; i++) {
			available = maxVacDay - amountVacDay[i];
			workDay = svcDay.getString(workDaysArray[i]);
			wordDaysSched += workDay + " (" + available + ") | ";
		}
		return wordDaysSched;
	}

	@Override
	public String toString() {
		String out = "Name: " + name + "\n";
		out += "Address: " + address + "\n";
		out += "Max Attendance Day: " + maxVacDay + "\n";
		out += "Work Days (available appointments): " + getWorkDaysSchedules() + "\n";

		return out;
	}

}
