package com.mycompany.myapp.web.rest.vm.parseJson;

import com.google.common.base.Strings;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.mycompany.myapp.web.rest.vm.parseJson.bean.Item;
import com.mycompany.myapp.web.rest.vm.parseJson.bean.MainTest;
import com.mycompany.myapp.web.rest.vm.parseJson.bean.MonthData;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;

public class Quarter {

    public static void updateValueQuarter(TreeMap<String, Integer> months, String name, Integer main) {
        if (months.containsKey(name)) {
            months.put(name, months.get(name) + main);
        } else {
            months.put(name, main);
        }
    }

    public static TreeMap<String, Integer> Quarter() throws Exception {
        List<Item> items = new Gson()
            .fromJson(new String(Files.readAllBytes(Paths.get("test2.json"))), new TypeToken<List<Item>>() {}.getType());

        TreeMap<String, Integer> quarters = new TreeMap<>();
        items
            .stream()
            .forEach(i -> {
                MonthData quarter;
                MainTest mainTest;
                try {
                    quarter = getQuarter(i.getName());
                    mainTest = getMain(i.getMain());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                if (quarters.containsKey(quarter.getName())) {
                    quarters.get(quarter.getName()).intValue();
                } else {
                    quarter.addValue();
                    quarters.put(quarter.getName(), quarter.getValue());
                }
                updateValueQuarter(quarters, quarter.getName(), mainTest.getMain());
            });

        return quarters;
    }

    public static MonthData getQuarter(String date) throws Exception {
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

    public static MainTest getMain(int main) throws NullPointerException {
        if (main == 0) {
            System.out.println("null");
        }
        return new MainTest(main);
    }
}
