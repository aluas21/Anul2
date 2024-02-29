package Domain;

public class BP extends HealthData{

    Integer systolicValue;
    Integer diastolicValue;
    public BP(){
        super();
        systolicValue = 0;
        diastolicValue = 0;
    }

    public BP(String date,Integer systolicValue, Integer diastolicValue){
        super(date);
        this.systolicValue = systolicValue;
        this.diastolicValue = diastolicValue;
    }

    @Override
    public boolean isNormalValue() {
        if(100<=systolicValue && systolicValue<=130 && 60<=diastolicValue && diastolicValue<=80)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "BP{" +
                "data='" + date + '\'' +
                "systolicValue=" + systolicValue +
                ", diastolicValue=" + diastolicValue +
                '}';
    }

    public Integer getSystolicValue() {
        return systolicValue;
    }

    public Integer getDiastolicValue() {
        return diastolicValue;
    }
}
