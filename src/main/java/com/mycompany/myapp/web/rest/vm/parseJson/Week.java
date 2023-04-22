package com.mycompany.myapp.web.rest.vm.parseJson;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Week {

    public static Map<String, WeeeksFilter> Week() throws Exception {
        List<Item> items = new Gson()
            .fromJson(new String(Files.readAllBytes(Paths.get("test2.json"))), new TypeToken<List<Item>>() {}.getType());

        Map<String, WeeksFilter> weeks = new HashMap<>();
        items
            .stream()
            .forEach(i -> {
                MainForMethods week;
                MainForMethods value;
                MainForMethods year;
                try {
                    week = getWeek(i.getName());
                    value = getValue(i.getValue());
                    year = getYear(i.getName());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                if (weeks.containsKey(week.getName() + "/" + year.getName())) {
                    weeks.get(week.getName() + "/" + year.getName()).addValue(value);
                } else {
                    weeks.put(week.getName() + "/" + year.getName(), new WeeksFilter(value.getValue()));
                }
            });
        return weeks;
    }

    private static MainForMethods getYear(String date) {
        if (Strings.isNullOrEmpty(date)) {
            System.out.println(" ##### NULL ");
            return new MainForMethods("x");
        } else {
            LocalDate day = LocalDate.parse(date);
            int year = day.getYear();
            return new MainForMethods(String.valueOf(year));
        }
    }

    private static MainForMethods getWeek(String date) {
        if (Strings.isNullOrEmpty(date)) {
            System.out.println(" ##### NULL ");
            return new MainForMethods("x");
        } else {
            WeekFields weekFields = WeekFields.of(DayOfWeek.SUNDAY, 1);
            TemporalField weekOfWeekBasedYear = weekFields.weekOfWeekBasedYear();
            LocalDate day = LocalDate.parse(date);
            int week = day.get(weekOfWeekBasedYear);
            return new MainForMethods(String.valueOf(week));
        }
    }

    private static MainForMethods getValue(int main) throws NullPointerException {
        return new MainForMethods(main);
    }
}
