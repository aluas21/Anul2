import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CMMMC {
    public List<Integer> transformToInt(String[] params) {
        List<Integer> numberList = new ArrayList<>();

        for (int i = 0; i < params.length; i++) {
            try {
                numberList.add(Integer.parseInt(params[i]));
            } catch (NumberFormatException nfe) {
                // Ignoram exceptia, desi de regula nu este o idee buna :)
            }
        }

        return numberList;
    }

    public List<Integer> readConsole() {
        List<Integer> numere = new ArrayList<Integer>();
        Scanner cons = new Scanner(System.in);
        System.out.println("Introduceti numere intregi. Stop pentru oprire.");

        while (cons.hasNext()) {
            String token = cons.next();

            if ("stop".equals(token)) {
                break;
            }

            try {
                numere.add(Integer.parseInt(token));
            } catch (NumberFormatException nfe) {

            }
        }
        return numere;
    }

    private  int cmmdc(int a,int b){
        while(a!=b){
            if(a>b)
                a = a-b;
            else
                b = b-a;
        }
        return a;
    }

    private int cmmmc(int a,int b){
        return a*b/cmmdc(a,b);
    }

    public int rez(List<Integer> numere){
        int rez1 = cmmmc(numere.get(0),numere.get(1));
        for(int i = 2;i<numere.size();i++)
            rez1 = cmmmc(rez1,numere.get(i));
        return rez1;
    }
}
