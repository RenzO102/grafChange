package com.mycompany.myapp.web.rest.vm.parseJson;

import com.google.common.base.Strings;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.mycompany.myapp.web.rest.vm.parseJson.bean.Item;
import com.mycompany.myapp.web.rest.vm.parseJson.bean.MainTest;
import com.mycompany.myapp.web.rest.vm.parseJson.bean.MonthData;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.TreeMap;

public class Week {

    public static TreeMap<String, Integer> Week() throws Exception {
        List<Item> items = new Gson()
            .fromJson(new String(Files.readAllBytes(Paths.get("test2.json"))), new TypeToken<List<Item>>() {}.getType());

        TreeMap<String, Integer> weeks = new TreeMap<>();
        items
            .stream()
            .forEach(i -> {
                MonthData week;
                MainTest mainTest;
                try {
                    week = getWeek(i.getName());
                    mainTest = getMain(i.getMain());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                if (weeks.containsKey(week.getName())) {
                    weeks.get(week.getName()).intValue();
                } else {
                    week.addValue();
                    weeks.put(week.getName(), week.getValue());
                }
                updateValueWeek(weeks, week.getName(), mainTest.getMain());
            });

        return weeks;
    }

    public static MonthData getWeek(String date) {
        if (Strings.isNullOrEmpty(date)) {
            System.out.println(" ##### NULL ");
            return new MonthData("x");
        } else {
            WeekFields weekFields = WeekFields.of(DayOfWeek.SUNDAY, 1);
            TemporalField weekOfWeekBasedYear = weekFields.weekOfWeekBasedYear();
            LocalDate day = LocalDate.parse(date);
            int week = day.get(weekOfWeekBasedYear);
            return new MonthData(String.valueOf(week));
        }
    }

    public static MainTest getMain(int main) throws NullPointerException {
        if (main == 0) {
            System.out.println("null");
        }
        return new MainTest(main);
    }

    public static void updateValueWeek(TreeMap<String, Integer> months, String name, Integer main) {
        if (months.containsKey(name)) {
            months.put(name, months.get(name) + main);
        } else {
            months.put(name, main);
        }
    }
}
