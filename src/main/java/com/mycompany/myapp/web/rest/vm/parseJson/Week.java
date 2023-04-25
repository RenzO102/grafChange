package com.mycompany.myapp.web.rest.vm.parseJson;

import com.google.common.base.Strings;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.mycompany.myapp.web.rest.vm.parseJson.bean.DateSelecetMethod;
import com.mycompany.myapp.web.rest.vm.parseJson.bean.Item;
import com.mycompany.myapp.web.rest.vm.parseJson.bean.OptionsWeeks;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class Week {

    public static List<OptionsWeeks> Week() throws Exception {
        List<Item> items = new Gson()
            .fromJson(new String(Files.readAllBytes(Paths.get("test2.json"))), new TypeToken<List<Item>>() {}.getType());

        var weeks = new ArrayList<OptionsWeeks>();
        items
            .stream()
            .forEach(i -> {
                DateSelecetMethod week;
                DateSelecetMethod value;
                DateSelecetMethod year;
                try {
                    week = getWeek(i.getName());
                    value = getValue(i.getValue());
                    year = getYear(i.getName());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                var hasWeekData = new AtomicBoolean(false);
                var optionsWeeks = new OptionsWeeks(value.getValue(), year.getName(), week.getName());
                weeks.forEach(wd -> {
                    if (Objects.equals(wd.key(), optionsWeeks.key())) {
                        wd.addValue(value);
                        hasWeekData.set(true);
                    }
                });
                if (!hasWeekData.get()) {
                    weeks.add(optionsWeeks);
                }
            });
        return weeks;
    }

    private static DateSelecetMethod getYear(String date) {
        if (Strings.isNullOrEmpty(date)) {
            System.out.println(" ##### NULL ");
            return new DateSelecetMethod("x");
        } else {
            LocalDate day = LocalDate.parse(date);
            int year = day.getYear();
            return new DateSelecetMethod(String.valueOf(year));
        }
    }

    private static DateSelecetMethod getWeek(String date) {
        if (Strings.isNullOrEmpty(date)) {
            System.out.println(" ##### NULL ");
            return new DateSelecetMethod("x");
        } else {
            WeekFields weekFields = WeekFields.of(DayOfWeek.SUNDAY, 1);
            TemporalField weekOfWeekBasedYear = weekFields.weekOfWeekBasedYear();
            LocalDate day = LocalDate.parse(date);
            int week = day.get(weekOfWeekBasedYear);
            return new DateSelecetMethod(String.valueOf(week));
        }
    }

    private static DateSelecetMethod getValue(int main) throws NullPointerException {
        return new DateSelecetMethod(main);
    }
}
