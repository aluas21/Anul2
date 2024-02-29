package service;

import Domain.BMI;
import Domain.BP;
import Domain.HealthData;
import repository.Repository;

import java.util.ArrayList;

public class ServiceBP {
    Repository<BP> repo;

    public ServiceBP(Repository<BP> repo){
       this.repo = repo;
    }

    public ArrayList<BP> getAll(){
        return repo.getAll();
    }
    public void addBP(String date,int systolicValue, int diastolicValue){
        BP bp = new BP(date,systolicValue, diastolicValue);
        repo.addEntity(bp);
    }

    public boolean isNormalValue(String date){
        ArrayList<BP> list = repo.getAll();
        list.sort((o1, o2) -> o1.getDate().compareTo(o2.getDate()));
        if(date.equals("1")){
            BP bp = list.get(0);
            if(bp.isNormalValue())
                return true;
            else
                return false;
        }

        BP bp = list.get(0);
        BP bp1 = list.get(1);
        for(int i = 2;i<list.size();i++){
            if(list.get(i).getDate().equals(date)){
                bp = list.get(i);
                bp1 = list.get(i-1);
            }
        }

        if(bp.isNormalValue() && bp1.isNormalValue())
            return true;
        else
            return false;
    }

    public ArrayList<BP> getAfterData(String data){
        ArrayList<BP> list =new ArrayList<>();
        for(BP bp : repo.getAll())
            if(bp.getDate().compareTo(data)>=0)
                list.add(bp);
        return list;
    }

}
