package com.mycompany.myapp.web.rest.vm.parseJson;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.myapp.web.rest.vm.parseJson.bean.Item;
import com.mycompany.myapp.web.rest.vm.parseJson.bean.MainTest;
import com.mycompany.myapp.web.rest.vm.parseJson.bean.MonthData;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import org.apache.commons.lang3.StringUtils;
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
        System.out.println(months);

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
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
            Date date3 = formatter.parse(StringUtils.substringBefore(date, "T"));
            String formattedDateString = formatter.format(date3);
            return new MonthData(formattedDateString);
        }
    }

    public static MainTest getMain(int main) throws NullPointerException {
        if (main == 0) {
            System.out.println("null");
        }
        return new MainTest(main);
    }
}
