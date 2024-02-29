package Domain;

public abstract class  HealthData {
    String date = "";

    public HealthData(){
        this.date = "";
    }

    public HealthData(String date){
        this.date = date;
    }

    public boolean isNormalValue(){
        return false;
    }

    public String toString(){
        return "HealthData{" +
                "date='" + date + '\'' +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
