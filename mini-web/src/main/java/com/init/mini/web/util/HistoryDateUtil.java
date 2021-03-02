package com.init.mini.web.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HistoryDateUtil {

    public final static List<String> hisDateList;

    static {
        hisDateList = new ArrayList<>();
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        int month = localDate.getMonth().getValue();
        for (int i=1; i<=12; i++) {
            hisDateList.add(new StringBuilder().append(year-1).append("-").append(i).toString());
        }
        for (int i=1; i<=month; i++) {
            hisDateList.add(new StringBuilder().append(year).append("-").append(i).toString());
        }
    }

}
