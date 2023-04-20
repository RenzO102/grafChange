package com.mycompany.myapp.web.rest.vm.parseJson;

import com.google.common.base.Strings;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.mycompany.myapp.web.rest.vm.parseJson.bean.Item;
import com.mycompany.myapp.web.rest.vm.parseJson.bean.MainForMethods;
import com.mycompany.myapp.web.rest.vm.parseJson.bean.QuarterFilter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Quarter {

    public static Map<String, QuarterFilter> Quarter() throws Exception {
        List<Item> items = new Gson()
            .fromJson(new String(Files.readAllBytes(Paths.get("test2.json"))), new TypeToken<List<Item>>() {}.getType());

        Map<String, QuarterFilter> quarters = new HashMap<>();
        items
            .stream()
            .forEach(i -> {
                MainForMethods quarter;
                MainForMethods value;
                MainForMethods year;
                try {
                    quarter = getQuarter(i.getName());
                    value = getValue(i.getValue());
                    year = getYear(i.getName());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                if (quarters.containsKey(quarter.getName() + "/" + year.getName())) {
                    quarters.get(quarter.getName() + "/" + year.getName()).addValue(value);
                } else {
                    quarters.put(quarter.getName() + "/" + year.getName(), new QuarterFilter(value.getValue()));
                }
            });
        return quarters;
    }

    private static MainForMethods getQuarter(String date) throws Exception {
        if (Strings.isNullOrEmpty(date)) {
            System.out.println(" ##### NULL ");
            return new MainForMethods("x");
        } else {
            LocalDate date1 = LocalDate.parse(date);
            int Month = date1.getMonthValue();
            int quarter;
            if (Month < 4) quarter = 1; else if (Month >= 4 && Month < 7) quarter = 2; else if (Month >= 7 && Month < 10) quarter =
                3; else quarter = 4;
            return new MainForMethods(String.valueOf(quarter));
        }
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

    private static MainForMethods getValue(int main) throws NullPointerException {
        return new MainForMethods(main);
    }
}
