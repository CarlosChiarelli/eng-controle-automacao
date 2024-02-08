package com.unicamp.mc322.lab04;

import java.util.Date;

import com.unicamp.mc322.lab04.days.Days;
import com.unicamp.mc322.lab04.days.ServiceDay;

public class Runner {

        public static void main(String[] args) {
                String MSG_REG = "Try registry ";
                String MSG_SCH = "Success schedule ";
                Days[] days = new Days[] { Days.MONDAY, Days.FRIDAY, Days.WEDNESDAY };
                Days[] daysSunday = new Days[] { Days.MONDAY, Days.FRIDAY, Days.SUNDAY };

                // test create user and health center
                System.out.print("\n\n\nTEST CREATE USERS AND HEALTH CENTERS\n\n\n");
                User jose = new User("Jose da Silva", "123.456.789-01", new Date(1960, 12, 03), new Position(10, 30));
                User maria = new User("Maria Assuncao", "321.654.987-10", new Date(1999, 4, 11),
                                new Position(-43, 101));

                HealthCenter sousasHc = new HealthCenter("SOUSAS", new Position(0, 20), 5, days);
                HealthCenter bgHc = new HealthCenter("BARAO GERALDO", new Position(-20, 40), 2);

                System.out.println(jose);
                System.out.println("Age: " + jose.getAge());
                System.out.println(maria);
                System.out.println("Age: " + maria.getAge());

                // test days
                System.out.print("\n\n\nTEST USE DAYS\n\n\n");
                Days sexta = Days.FRIDAY;
                Days segunda = Days.MONDAY;
                ServiceDay svcDay = new ServiceDay();

                System.out.println(svcDay.early(sexta, segunda));
                System.out.println(svcDay.early(segunda, sexta));

                // test app
                System.out.print("\n\n\nTEST APP REGISTRY\n\n\n");
                CovidVaccine app = new CovidVaccine();
                app.setAgeMin(50);

                // test registry user
                System.out.println(MSG_REG + "jose: " + app.registerUser("Jose da Silva", "123.456.789-01",
                                new Date(1960, 12, 03), new Position(10, 30)));
                System.out.println(MSG_REG + "jose: " + app.registerUser("Jose da Silva", "123.456.789-01",
                                new Date(1960, 12, 03), new Position(10, 30)));
                System.out.println(MSG_REG + "maria: " + app.registerUser("Maria Assuncao", "321.654.987-10",
                                new Date(1999, 4, 11), new Position(-43, 101)));
                app.printInfoUsers();

                // test registry health center
                System.out.println(MSG_REG + "SOUSAS: " + app.registerHc("SOUSAS", new Position(0, 20), 5, days));
                System.out.println(MSG_REG + "SOUSAS: " + app.registerHc("SOUSAS", new Position(0, 20), 5, days));
                System.out.println(MSG_REG + "BARAO GERALDO: "
                                + app.registerHc("BARAO GERALDO", new Position(-20, 40), 2));
                app.printInfoHealthCenters();

                // test schedule
                System.out.print("\n\n\nTEST APP SCHEDULE\n\n\n");
                String scheduleJoseSousas = app.schedule("123.456.789-01", "SOUSAS");
                System.out.println(MSG_SCH + "Jose: \n" + scheduleJoseSousas);
                System.out.println(MSG_SCH + "Jose (TRY 2): \n" + app.schedule("123.456.789-01", "SOUSAS"));

                String scheduleMariaBarao = app.schedule("321.654.987-10", "BARAO GERALDO");
                System.out.println(MSG_SCH + "Maria: \n" + scheduleMariaBarao);

                System.out.println(MSG_SCH + "Invalid cpf: \n" + app.schedule("666", "SOUSAS"));
                System.out.println(MSG_SCH + "Invalid health center: \n"
                                + app.schedule("123.456.789-01", "health no exist"));

                System.out.print("");
                app.printInfoHealthCenters();

                // add any users
                System.out.print("\n\n\nTEST REGISTRY AND SCHEDULE ANY USERS\n\n\n");
                User[] userArray = createDataUser();
                for (User user : userArray) {
                        System.out.println(MSG_REG + user.getName() + " : " + app.registerUser(user.getName(),
                                        user.getCPF(), user.getBirthday(), user.getAddress()));
                        System.out.println(MSG_SCH + " : \n" + user.getName() + "\n"
                                        + app.schedule(user.getCPF(), "SOUSAS"));
                }

                System.out.print("\n\n\nFAILED SCHEDULE ANY USERS (ALL SCHEDULED)\n\n\n");
                for (User user : userArray) {
                        System.out.println(MSG_SCH + "" + user.getName() + "\n"
                                        + app.schedule(user.getCPF(), "BARAO GERALDO"));
                }

                System.out.print("\n\n\n");
                app.printInfoHealthCenters();

                // test print all registeres users
                app.printRegisteredUsers();

                // test early schedule
                System.out.print("\n\n\nTEST SCHEDULE EARLY\n\n\n");
                System.out.println(MSG_REG + "UNIMED: " + app.registerHc("UNIMED", new Position(0, 30), 2, daysSunday));
                System.out.println(MSG_REG + "carlos: " + app.registerUser("Carlos Chiarelli", "449.074.467-82",
                                new Date(1950, 02, 17), new Position(10, 30)));
                System.out.println(MSG_SCH + " Carlos: \n" + app.scheduleEarly("449.074.467-82"));

                app.printRegisteredUsers();
                app.printInfoHealthCenters();

                // test near schedule
                System.out.print("\n\n\nTEST SCHEDULE NEAR\n\n\n");
                System.out.println(MSG_REG + "Felipe: " + app.registerUser("Felipe", "222.222.467-82",
                                new Date(1950, 02, 17), new Position(-19, 40)));
                System.out.println(MSG_REG + "Marcela: " + app.registerUser("Marcela", "111.111.467-82",
                                new Date(1950, 02, 17), new Position(1, 30)));
                System.out.println(MSG_SCH + " Felipe: \n" + app.scheduleNear("222.222.467-82"));
                System.out.println(MSG_SCH + " Marcela: \n" + app.scheduleNear("111.111.467-82"));

                app.printRegisteredUsers();
                app.printInfoHealthCenters();
        }

        private static User[] createDataUser() {
                int SIZE = 10;
                User[] userArray = new User[SIZE];
                String nameAdd;
                String cpfAdd;
                for (int i = 0; i < SIZE; i++) {
                        nameAdd = "User " + i;
                        cpfAdd = "" + i + '-' + i + '-' + i;
                        User userAdd = new User(nameAdd, cpfAdd, new Date(1960, 12, 03), new Position(10, 30));
                        userArray[i] = userAdd;
                }
                return userArray;
        }
}