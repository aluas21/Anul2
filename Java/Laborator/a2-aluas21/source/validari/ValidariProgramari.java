package validari;

import domain.Programare;

public class ValidariProgramari {
    Programare programare;
    public ValidariProgramari(Programare programare){
        this.programare = programare;
    }
    public void validari(){
        if(this.programare.getId()<=0)
            throw new RuntimeException("INVALID ID!");
    }
}
