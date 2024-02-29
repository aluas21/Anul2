package service;

import Domain.BMI;
import repository.Repository;

import java.util.ArrayList;

public class ServiceBMI {
    Repository<BMI> repo;
    public ServiceBMI(Repository<BMI> repo){
        this.repo = repo;
    }
    public ArrayList<BMI> getAll(){
        return repo.getAll();
    }

    public void addBMI(String date,float value){
        BMI bmi = new BMI(date,value);
        repo.addEntity(bmi);
    }

    public boolean isNormalValue(String date){
        ArrayList<BMI> list = repo.getAll();
        list.sort((o1, o2) -> o1.getDate().compareTo(o2.getDate()));
        if(date.equals("1")){
            BMI bmi = list.get(0);
            if(bmi.isNormalValue())
                return true;
            else
                return false;
        }
        BMI bmi;
        BMI bmi1;
        bmi = list.get(0);
        bmi1 = list.get(1);
        for(int i = 2;i<list.size();i++){
            if(list.get(i).getDate().equals(date)){
                bmi = list.get(i);
                bmi1 = list.get(i-1);
            }
        }
        if(bmi1.isNormalValue()&&bmi.isNormalValue())
            return true;
        else
            return false;
    }

    public ArrayList<BMI> getAfterData(String data){
        ArrayList<BMI> list =new ArrayList<>();
        for(BMI bmi : repo.getAll())
            if(bmi.getDate().compareTo(data)>=0)
                list.add(bmi);
        return list;
    }

}
