package com.mycompany.myapp.web.rest.vm.parseJson;

import com.google.common.base.Strings;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.mycompany.myapp.web.rest.vm.parseJson.bean.DateSelecetMethod;
import com.mycompany.myapp.web.rest.vm.parseJson.bean.Item;
import com.mycompany.myapp.web.rest.vm.parseJson.bean.OptionsMonth;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Month {

    public static List<OptionsMonth> month() throws Exception {
        List<Item> items = new Gson()
            .fromJson(new String(Files.readAllBytes(Paths.get("test2.json"))), new TypeToken<List<Item>>() {}.getType());

        List<OptionsMonth> months = new ArrayList<>();
        items
            .stream()
            .forEach(i -> {
                OptionsMonth optionsMonth;
                try {
                    optionsMonth =
                        OptionsMonth
                            .month()
                            .setNumber(getMoths(i.getName()).getName())
                            .setYear(getYear(i.getName()).getName())
                            .setValue(getValue(i.getValue()).getValue());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                Optional<OptionsMonth> month = months.stream().filter(mk -> mk.key().equals(optionsMonth.key())).findFirst();

                if (month.isPresent()) {
                    month.get().addValue(optionsMonth.getValue());
                } else {
                    months.add(optionsMonth);
                }
            });
        return months;
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

    private static DateSelecetMethod getValue(int value) throws NullPointerException {
        return new DateSelecetMethod(value);
    }

    public static DateSelecetMethod getMoths(String date) throws Exception {
        if (Strings.isNullOrEmpty(date)) {
            System.out.println(" ##### NULL ");
            return new DateSelecetMethod("x");
        } else {
            LocalDate months = LocalDate.parse(date);
            int month = months.getMonthValue();
            return new DateSelecetMethod(String.valueOf(month));
        }
    }
}
