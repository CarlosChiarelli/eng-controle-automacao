package com.unicamp.mc322.lab04;

public class ScheduleVaccine {
    private User user;
    private HealthCenter hc;

    public ScheduleVaccine(User user, HealthCenter hc) {
        this.user = user;
        this.hc = hc;
    }

    public String getUserCpf() {
        return user.getCPF();
    }
}
