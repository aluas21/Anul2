package com.example.practic.domain;

public class EnitityTest {
    //atribute int id, string atribut1, string atribut2
    //constructori
     int id;
        String atribut1;
        String atribut2;
        public EnitityTest(int id, String atribut1, String atribut2) {
            this.id = id;
            this.atribut1 = atribut1;
            this.atribut2 = atribut2;
        }
        public EnitityTest() {
        }
        //getteri si setteri
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public String getAtribut1() {
            return atribut1;
        }
        public void setAtribut1(String atribut1) {
            this.atribut1 = atribut1;
        }
        public String getAtribut2() {
            return atribut2;
        }
        public void setAtribut2(String atribut2) {
            this.atribut2 = atribut2;
        }
        //toString
        @Override
        public String toString() {
            return "EnitityTest{" +
                    "id=" + id +
                    ", atribut1='" + atribut1 + '\'' +
                    ", atribut2='" + atribut2 + '\'' +
                    '}';
        }


}
