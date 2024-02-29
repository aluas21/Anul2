package validari;

import domain.Pacient;

public class ValidariPacient {
    Pacient pacient;
    public ValidariPacient(Pacient pacient){
        this.pacient = pacient;
    }

    public void validari(){
        if(this.pacient.getId()<=0)
            throw new RuntimeException("INVALID ID!");
        if(this.pacient.getAge()<0)
            throw new RuntimeException("INVALID AGE!");
    }
}
