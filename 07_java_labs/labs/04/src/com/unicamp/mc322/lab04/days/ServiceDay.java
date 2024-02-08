package com.unicamp.mc322.lab04.days;

public class ServiceDay {

    private int getIndex(Days dayArg) {
        int day;
        switch (dayArg) {
            case SUNDAY:
                day = 1;
                break;
            case MONDAY:
                day = 2;
                break;
            case TUESDAY:
                day = 3;
                break;
            case WEDNESDAY:
                day = 4;
                break;
            case THURSDAY:
                day = 5;
                break;
            case FRIDAY:
                day = 6;
                break;
            default:
                day = 7;
                break;
        }
        return day;
    }

    public String getString(Days dayArg) {
        String day;
        switch (dayArg) {
            case SUNDAY:
                day = "SUNDAY";
                break;
            case MONDAY:
                day = "MONDAY";
                break;
            case TUESDAY:
                day = "TUESDAY";
                break;
            case WEDNESDAY:
                day = "WEDNESDAY";
                break;
            case THURSDAY:
                day = "THURSDAY";
                break;
            case FRIDAY:
                day = "FRIDAY";
                break;
            default:
                day = "SATURDAY";
                break;
        }
        return day;
    }

    public boolean early(Days dayBefore, Days dayAfter) {
        return getIndex(dayBefore) < getIndex(dayAfter);
    }

    public int getIdxEarlyDay(Days[] daysArray) {
        int idx = 0;
        if (daysArray.length == 1)
            return 1;
        for (int i = 0; i < (daysArray.length - 1); i++)
            if (!early(daysArray[i], daysArray[i + 1]))
                idx = i + 1;
        return idx;
    }

    public Days[] sortDaysArray(Days[] daysArray) {
        Days temporary;
        for (int i = 0; i < daysArray.length; i++) {
            for (int j = 0; j < daysArray.length - i - 1; j++) {
                if (!early(daysArray[j], daysArray[j + 1])) {
                    temporary = daysArray[j + 1];
                    daysArray[j + 1] = daysArray[j];
                    daysArray[j] = temporary;
                }
            }
        }
        return daysArray;
    }
}
