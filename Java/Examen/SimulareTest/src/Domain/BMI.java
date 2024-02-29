package Domain;

public class BMI extends HealthData{

    private float value;

    public BMI(){
        super();
        this.value = 0;
    }

    public BMI(String date,float value){
        super(date);
        this.value = value;
    }
    @Override
    public boolean isNormalValue() {
        if(value>=18.5 && value<=25)
            return true;
        else
            return false;
    }



    @Override
    public String toString() {
        return "BMI{" +
                "data='" + date + '\'' +
                "value=" + value +
                '}';
    }

    public float getValue() {
        return value;
    }
}
