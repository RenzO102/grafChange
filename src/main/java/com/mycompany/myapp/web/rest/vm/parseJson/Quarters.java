package com.mycompany.myapp.web.rest.vm.parseJson;

import com.google.common.base.Strings;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.mycompany.myapp.web.rest.vm.parseJson.bean.DateSelecetMethod;
import com.mycompany.myapp.web.rest.vm.parseJson.bean.Item;
import com.mycompany.myapp.web.rest.vm.parseJson.bean.OptionsQuarters;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Quarters {

    public List<OptionsQuarters> Quarters() throws Exception {
        List<Item> items = new Gson()
            .fromJson(new String(Files.readAllBytes(Paths.get("test2.json"))), new TypeToken<List<Item>>() {}.getType());

        var quarters = new ArrayList<OptionsQuarters>();
        items
            .stream()
            .forEach(i -> {
                DateSelecetMethod quarter;
                DateSelecetMethod QuarterValue;
                DateSelecetMethod year;
                try {
                    quarter = getQuarter(i.getName());
                    QuarterValue = getValue(i.getValue());
                    year = getYear(i.getName());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                var hasQuarterData = new AtomicBoolean(false);
                var optionsQuaters = new OptionsQuarters(QuarterValue.getValue(), year.getName(), quarter.getName());
                quarters.forEach(wd -> {
                    if (Objects.equals(wd.key(), optionsQuaters.key())) {
                        wd.addValue(QuarterValue);
                        hasQuarterData.set(true);
                    }
                });
                if (!hasQuarterData.get()) {
                    quarters.add(optionsQuaters);
                }
            });
        return quarters;
    }

    private static DateSelecetMethod getQuarter(String date) throws Exception {
        if (Strings.isNullOrEmpty(date)) {
            System.out.println(" ##### NULL ");
            return new DateSelecetMethod("x");
        } else {
            LocalDate date1 = LocalDate.parse(date);
            int Month = date1.getMonthValue();
            int quarter;
            if (Month < 4) quarter = 1; else if (Month >= 4 && Month < 7) quarter = 2; else if (Month >= 7 && Month < 10) quarter =
                3; else quarter = 4;
            return new DateSelecetMethod(String.valueOf(quarter));
        }
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

    private static DateSelecetMethod getValue(int main) throws NullPointerException {
        return new DateSelecetMethod(main);
    }
}
