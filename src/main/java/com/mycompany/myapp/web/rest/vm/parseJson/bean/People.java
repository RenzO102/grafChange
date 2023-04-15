package com.mycompany.myapp.web.rest.vm.parseJson.bean;

import java.time.LocalDate;

public class People {

    private String percentY;
    private String name;
    private int value;

    public int y;
    private int z;

    public class Welcome3 {

        private Person[] people;

        public Person[] getPeople() {
            return people;
        }

        public void setPeople(Person[] value) {
            this.people = value;
        }
    }

    public class Person {

        private LocalDate name;
        private String percentY;
        private long value;
        private long y;
        private long z;

        public LocalDate getName() {
            return name;
        }

        public void setName(LocalDate value) {
            this.name = value;
        }

        public String getPercentY() {
            return percentY;
        }

        public void setPercentY(String value) {
            this.percentY = value;
        }

        public long getValue() {
            return value;
        }

        public void setValue(long value) {
            this.value = value;
        }

        public long getY() {
            return y;
        }

        public void setY(long value) {
            this.y = value;
        }

        public long getZ() {
            return z;
        }

        public void setZ(long value) {
            this.z = value;
        }
    }

    @Override
    public String toString() {
        return "People{" + "percentY='" + percentY + '\'' + ", name='" + name + '\'' + ", value=" + value + ", y=" + y + ", z=" + z + '}';
    }
}
