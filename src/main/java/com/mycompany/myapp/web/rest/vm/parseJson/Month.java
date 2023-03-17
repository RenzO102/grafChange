package com.mycompany.myapp.web.rest.vm.parseJson;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.myapp.web.rest.vm.parseJson.bean.Item;
import com.mycompany.myapp.web.rest.vm.parseJson.bean.MainTest;
import com.mycompany.myapp.web.rest.vm.parseJson.bean.MonthData;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Month {

    public static TreeMap<String, Integer> Month() throws Exception {
        List<Item> items = new Gson()
            .fromJson(new String(Files.readAllBytes(Paths.get("test2.json"))), new TypeToken<List<Item>>() {}.getType());

        TreeMap<String, Integer> months = new TreeMap<>();
        items.forEach(Item -> {
            MonthData month;
            MainTest mainTest;
            try {
                month = getMoths(Item.getName());
                mainTest = getMain(Item.getMain());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if (months.containsKey(month.getName())) {
                months.get(month.getName());
            } else {
                month.addValue();
                months.put(month.getName(), month.getValue());
            }
            updateValue(months, month.getName(), mainTest.getMain());
        });

        return months;
    }

    public static void updateValue(TreeMap<String, Integer> months, String name, Integer main) {
        if (months.containsKey(name)) {
            months.put(name, months.get(name) + main);
        } else {
            months.put(name, main);
        }
    }

    public static MonthData getMoths(String date) throws Exception {
        if (Strings.isNullOrEmpty(date)) {
            System.out.println(" ##### NULL ");
            return new MonthData("x");
        } else {
            LocalDate months = LocalDate.parse(date);
            int month = months.getMonthValue();
            return new MonthData(String.valueOf(month));
        }
    }

    public static MainTest getMain(int main) throws NullPointerException {
        if (main == 0) {
            System.out.println("null");
        }
        return new MainTest(main);
    }
}
