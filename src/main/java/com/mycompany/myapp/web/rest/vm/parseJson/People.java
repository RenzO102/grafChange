package com.mycompany.myapp.web.rest.vm.parseJson;

import java.time.LocalDate;

public class People {

    private String percentY;
    private String name;
    private int main;
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
        private long main;
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

        public long getMain() {
            return main;
        }

        public void setMain(long value) {
            this.main = value;
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
        return "People{" + "percentY='" + percentY + '\'' + ", name='" + name + '\'' + ", main=" + main + ", y=" + y + ", z=" + z + '}';
    }
}
