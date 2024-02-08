package com.unicamp.mc322.lab04;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingDouble;

import com.unicamp.mc322.lab04.days.Days;
import com.unicamp.mc322.lab04.days.ServiceDay;

public class CovidVaccine {

	private int ageVaccine;
	private ArrayList<User> usersList;
	private ArrayList<HealthCenter> hcList;
	private ArrayList<ScheduleVaccine> scheduleList;

	public CovidVaccine() {
		this.ageVaccine = 100;
		this.usersList = new ArrayList<User>();
		this.hcList = new ArrayList<HealthCenter>();
		this.scheduleList = new ArrayList<ScheduleVaccine>();
	}

	public CovidVaccine(int ageVaccine) {
		this.ageVaccine = ageVaccine;
		this.usersList = new ArrayList<User>();
		this.hcList = new ArrayList<HealthCenter>();
		this.scheduleList = new ArrayList<ScheduleVaccine>();
	}

	public void setAgeMin(int age) {
		ageVaccine = age;
	}

	private int getAgeVaccine() {
		return ageVaccine;
	}

	public String schedule(String userCpf, String nameHc) {
		String receipt = "";
		if (!validationOkSchedule(userCpf, nameHc))
			return receipt;

		int idxHc = getIdxHc(nameHc);
		int idxUser = getIdxUser(userCpf);
		receipt = hcList.get(idxHc).scheduleVac();

		// success schedule
		if (!receipt.equals(""))
			receipt = addScheduleSuccess(receipt, userCpf, idxUser, idxHc);

		return receipt;
	}

	public String scheduleNear(String userCpf) {
		String receipt = "";

		if (!validationOkSchedule(userCpf))
			return receipt;

		List<Double> distanceList = getDistancesHc(getPositionUser(userCpf));

		for (int i = 0; i < distanceList.size() && receipt.equals(""); i++) {
			int minIdx = IntStream.range(0, distanceList.size()).boxed().min(comparingDouble(distanceList::get)).get();
			receipt = schedule(userCpf, hcList.get(minIdx).getName());
		}

		return receipt;
	}

	public String scheduleEarly(String userCpf) {
		String receipt = "";

		if (!validationOkSchedule(userCpf))
			return receipt;

		int idxHc = getIdxHcEarlyDay();

		return schedule(userCpf, hcList.get(idxHc).getName());
	}

	private int getIdxHcEarlyDay() {
		int idxHc = 0;
		Days dayEarly, temporary;
		ServiceDay svcDay = new ServiceDay();

		// initialized any value
		dayEarly = Days.SUNDAY;

		// get index early day
		for (int i = 0; i < hcList.size(); i++) {
			temporary = hcList.get(i).getEarlyWorkDay();
			if (i == 0) {
				// if first
				dayEarly = temporary;
				idxHc = i;
			} else if (!svcDay.early(dayEarly, temporary)) {
				// if no is early day
				dayEarly = temporary;
				idxHc = i;
			}
		}
		return idxHc;
	}

	private List<Double> getDistancesHc(Position userPoint) {
		List<Double> distanceList = new ArrayList<Double>();

		// get index early day
		for (int i = 0; i < hcList.size(); i++) {
			Position hcPoint = hcList.get(i).getAddress();
			distanceList.add(userPoint.getDistance(hcPoint));
		}
		return distanceList;
	}

	private String addScheduleSuccess(String receipt, String userCpf, int idxUser, int idxHc) {
		receipt = "CPF User: " + userCpf + "\n" + receipt;
		ScheduleVaccine scheduleAdd = new ScheduleVaccine(usersList.get(idxUser), hcList.get(idxHc));
		scheduleList.add(scheduleAdd);
		return receipt;
	}

	private boolean validationOkSchedule(String userCpf, String nameHc) {
		boolean success = false;
		if (!(existUser(userCpf) && existHc(nameHc))) {
			System.err.println("ERROR: User or Health Center no registry.");
			return success;
		}
		if (getAgeUser(userCpf) < getAgeVaccine()) {
			System.err.println("ERROR: Underage age.");
			return success;
		}
		if (scheduledUser(userCpf)) {
			System.err.println("ERROR: Scheduled user.");
			return success;
		}
		success = true;
		return success;
	}

	private boolean validationOkSchedule(String userCpf) {
		boolean success = false;
		if (!existUser(userCpf)) {
			System.err.println("ERROR: User or Health Center no registry.");
			return success;
		}
		if (getAgeUser(userCpf) < getAgeVaccine()) {
			System.err.println("ERROR: Underage age.");
			return success;
		}
		if (scheduledUser(userCpf)) {
			System.err.println("ERROR: Scheduled user.");
			return success;
		}
		success = true;
		return success;
	}

	public boolean registerUser(String name, String cpf, Date birthday, Position position) {
		boolean success = false;
		User user = new User(name, cpf, birthday, position);
		if (!existUser(user.getCPF())) {
			usersList.add(user);
			success = true;
		}
		return success;
	}

	private boolean existUser(String userCpf) {
		boolean exist = false;
		for (User userList : usersList)
			if (userList.getCPF() == userCpf)
				exist = true;
		return exist;
	}

	private boolean scheduledUser(String userCpf) {
		boolean scheduled = false;
		for (ScheduleVaccine scd : scheduleList)
			if (scd.getUserCpf() == userCpf)
				scheduled = true;
		return scheduled;
	}

	public void printInfoUsers() {
		for (User user : usersList)
			System.out.println(user);
	}

	private int getAgeUser(String userCpf) {
		int age = 0;
		for (User userList : usersList)
			if (userList.getCPF() == userCpf)
				age = userList.getAge();
		return age;
	}

	private Position getPositionUser(String userCpf) {
		Position point = new Position(0, 0);
		for (User userList : usersList)
			if (userList.getCPF() == userCpf)
				point = userList.getAddress();
		return point;
	}

	public boolean registerHc(String name, Position address, int maxVacDay) {
		boolean success = false;
		HealthCenter hc = new HealthCenter(name, address, maxVacDay);

		if (!existHc(hc.getName())) {
			hcList.add(hc);
			success = true;
		}
		return success;
	}

	public boolean registerHc(String name, Position address, int maxVacDay, Days[] daysArray) {
		boolean success = false;
		HealthCenter hc = new HealthCenter(name, address, maxVacDay, daysArray);

		if (!existHc(hc.getName())) {
			hcList.add(hc);
			success = true;
		}
		return success;
	}

	private boolean existHc(String nameHc) {
		boolean exist = false;
		for (HealthCenter hc : hcList)
			if (hc.getName() == nameHc)
				exist = true;
		return exist;
	}

	private int getIdxHc(String nameHc) {
		for (int i = 0; i < hcList.size(); i++)
			if (hcList.get(i).getName() == nameHc)
				return i;
		return -1;
	}

	private int getIdxUser(String cpf) {
		for (int i = 0; i < usersList.size(); i++)
			if (usersList.get(i).getCPF() == cpf)
				return i;
		return -1;
	}

	public void printInfoHealthCenters() {
		System.out.print("\n-----------------\n");
		System.out.print("Info Health Centers:\n\n");
		for (HealthCenter healthCenter : hcList)
			System.out.println(healthCenter);
		System.out.print("-----------------\n\n\n");
	}

	public void printRegisteredUsers() {
		System.out.print("\n-----------------\n");
		System.out.print("Registered Users:\n\n");
		for (User user : usersList) {
			System.out.print(user);
			System.out.print("\n");
		}
		System.out.print("-----------------\n\n\n");
	}
}
