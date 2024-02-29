import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        CMMMC obj = new CMMMC();
        List<Integer> numere;
        if(args.length>0)
            numere = obj.transformToInt(args);
        else
            numere = obj.readConsole();

        System.out.println(obj.rez(numere));

    }
}
