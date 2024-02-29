package com.example.demo.validari;

import com.example.demo.domain.Programare;

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
