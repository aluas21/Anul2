//package com.example.template.domain;
//
//public class ExempleConvert implements EntityConvert<Pacient> {
//
//    @Override
//    public String ToString(Pacient pacient) {
//        return pacient.getId()+" "+pacient.getName()+" "+pacient.getSurname()+" "+pacient.getAge();
//    }
//
//    @Override
//    public Pacient fromString(String line) {
//        String[] attributes = line.split(" ");
//        Pacient pacient = new Pacient();
//        pacient.setId(Integer.parseInt(attributes[0]));
//        pacient.setName(attributes[1]);
//        pacient.setSurname(attributes[2]);
//        pacient.setAge(Integer.parseInt(attributes[3]));
//        return pacient;
//    }
//}
