import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    Scanner sc = getInputTxtScanner();
    if(sc == null){
        return;
    }
    //  Первая строка входного файла INPUT.TXT содержит два целых числа troom и tcond, разделенных ровно одним пробелом (–50 ≤ troom ≤ 50, –50 ≤ tcond ≤ 50).
        int tRoom = sc.nextInt();
        int tCond = sc.nextInt();

    //  Вторая строка содержит одно слово, записанное строчными буквами английского алфавита — режим работы кондиционера, как указано выше.
        sc.nextLine();
        String modeName = sc.next();
        ConditionMode mode = getMode(modeName);
        if(mode == ConditionMode.WRONG){
            return;
        }

        int tResult = getResultTemperature(tRoom, tCond, mode);
        System.out.println("Температура через час: " + tResult);
    }

    static Scanner getInputTxtScanner(){
        // Just for ACMP
        String inputFileName = "INPUT.TXT";
        try{
            Scanner sc = new Scanner(new File(inputFileName));
            return sc;
        }
        catch(Exception e){
            System.out.println("Не удалось открыть " + inputFileName);
            return null;
        }
    }

    static ConditionMode getMode(String modeName){
        ConditionMode mode;
        if(modeName.equals("freeze")){
            mode = ConditionMode.FREEZE;
        }
        else if(modeName.equals("heat")){
            mode = ConditionMode.HEAT;
        }
        else if(modeName.equals("auto")){
            mode = ConditionMode.AUTO;
        }
        else if(modeName.equals("fan")){
            mode = ConditionMode.FAN;
        }
        else{
            System.out.println("Режим задан неверно");
            mode = ConditionMode.WRONG;
        }
        return mode;
    }

    static int getResultTemperature(int tRoom, int tCond, ConditionMode mode) {
        int tResult = 0;
        if (mode == ConditionMode.FAN
         || mode == ConditionMode.FREEZE && tRoom <= tCond
         || mode == ConditionMode.HEAT && tRoom >= tCond
         )
        {
            tResult = tRoom;
        }
        if (mode == ConditionMode.AUTO
         || mode == ConditionMode.FREEZE && tRoom > tCond
         || mode == ConditionMode.HEAT && tRoom < tCond
        )
        {
            tResult = tCond;
        }
        return tResult;
    }
}