package com.mycompany.myapp.web.rest.vm.parseJson;

import com.google.common.base.Strings;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

public class Quarter {

    public void updateValueQuarter(TreeMap<String, Integer> months, String name, Integer main) {
        if (months.containsKey(name)) {
            months.put(name, months.get(name) + main);
        } else {
            months.put(name, main);
        }
    }

    public Quarter main() throws Exception {
        List<Item> items = new Gson()
            .fromJson(new String(Files.readAllBytes(Paths.get("test2.json"))), new TypeToken<List<Item>>() {}.getType());

        TreeMap<String, Integer> months = new TreeMap<>();
        items
            .stream()
            .forEach(i -> {
                MonthData quarter;
                Main mainTest;
                try {
                    quarter = getQuarter(i.getName());
                    mainTest = getMain(i.getMain());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                if (months.containsKey(quarter.getName())) {
                    months.get(quarter.getName()).intValue();
                } else {
                    quarter.addValue();
                    months.put(quarter.getName(), quarter.getValue());
                }
                updateValueQuarter(months, quarter.getName(), mainTest.getMain());
            });
        return new Quarter();
    }

    public MonthData getQuarter(String date) throws Exception {
        if (Strings.isNullOrEmpty(date)) {
            System.out.println(" ##### NULL ");
            return new MonthData("x");
        } else {
            LocalDate date1 = LocalDate.parse(date);
            int Month = date1.getMonthValue();
            int quarter;
            if (Month < 4) quarter = 1; else if (Month >= 4 && Month < 7) quarter = 2; else if (Month >= 7 && Month < 10) quarter =
                3; else quarter = 4;
            return new MonthData(String.valueOf(quarter));
        }
    }

    public Main getMain(int main) throws NullPointerException {
        if (main == 0) {
            System.out.println("null");
        }
        return new Main(main);
    }
}
