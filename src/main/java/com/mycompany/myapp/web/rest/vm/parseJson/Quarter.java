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
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import com.mycompany.myapp.web.rest.vm.parseJson.bean.OptionsWeeks;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Quarter {

    public List<OptionsQuarters> Quarters() throws Exception {
        List<Item> items = new Gson()
            .fromJson(new String(Files.readAllBytes(Paths.get("test2.json"))), new TypeToken<List<Item>>() {
            }.getType());

        List<OptionsQuarters> quarters = new ArrayList<>();
        items.stream()
            .forEach(i -> {
                OptionsQuarters optionsQuarters;
                try {
                    optionsQuarters = OptionsQuarters.quarter()
                        .setQuarterNumber(getQuarter(i.getName()).getName())
                        .setYear(getYear(i.getName()).getName())
                        .setValue(getValue(i.getValue()).getValue());

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                Optional<OptionsQuarters> quarter = quarters.stream()
                    .filter(qk -> qk.key().equals(optionsQuarters.key()))
                    .findFirst();

                if (quarter.isPresent()) {
                    quarter.get().addValue(optionsQuarters.getValue());
                } else {
                    quarters.add(optionsQuarters);
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
            if (Month < 4) {
                quarter = 1;
            } else if (Month < 7) {
                quarter = 2;
            } else if (Month < 10) {
                quarter = 3;
            } else quarter = 4;
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
