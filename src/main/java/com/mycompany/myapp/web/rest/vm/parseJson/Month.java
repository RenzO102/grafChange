package com.mycompany.myapp.web.rest.vm.parseJson;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.myapp.web.rest.vm.parseJson.bean.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Month {

    public static Map<String, MonthsFilter> Month() throws Exception {
        List<Item> items = new Gson()
            .fromJson(new String(Files.readAllBytes(Paths.get("test2.json"))), new TypeToken<List<Item>>() {}.getType());

        Map<String, MonthsFilter> months = new HashMap<>();
        items
            .stream()
            .forEach(i -> {
                MainForMethods month;
                MainForMethods value;
                MainForMethods year;
                try {
                    month = getMoths(i.getName());
                    value = getValue(i.getValue());
                    year = getYear(i.getName());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                if (months.containsKey(month.getName() + "/" + year.getName())) {
                    months.get(month.getName() + "-" + year.getName()).addValue(value);
                } else {
                    months.put(month.getName() + "-" + year.getName(), new MonthsFilter(month.getValue()));
                }
            });
        return months;
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

    private static MainForMethods getValue(int value) throws NullPointerException {
        return new MainForMethods(value);
    }

    public static MainForMethods getMoths(String date) throws Exception {
        if (Strings.isNullOrEmpty(date)) {
            System.out.println(" ##### NULL ");
            return new MainForMethods("x");
        } else {
            LocalDate months = LocalDate.parse(date);
            int month = months.getMonthValue();
            return new MainForMethods(String.valueOf(month));
        }
    }
}
