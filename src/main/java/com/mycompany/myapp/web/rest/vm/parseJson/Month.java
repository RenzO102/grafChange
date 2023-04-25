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
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Month {

    public static List<OptionsMonth> Month() throws Exception {
        List<Item> items = new Gson()
            .fromJson(new String(Files.readAllBytes(Paths.get("test2.json"))), new TypeToken<List<Item>>() {}.getType());

        var months = new ArrayList<OptionsMonth>();
        items
            .stream()
            .forEach(i -> {
                DateSelecetMethod month;
                DateSelecetMethod MonthValue;
                DateSelecetMethod year;
                try {
                    month = getMoths(i.getName());
                    MonthValue = getValue(i.getValue());
                    year = getYear(i.getName());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                var hasMonthData = new AtomicBoolean(false);
                var optionsMonth = new OptionsMonth(MonthValue.getValue(), year.getName(), month.getName());
                months.forEach(wd -> {
                    if (Objects.equals(wd.key(), optionsMonth.key())) {
                        wd.addValue(MonthValue);
                        hasMonthData.set(true);
                    }
                });
                if (!hasMonthData.get()) {
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
