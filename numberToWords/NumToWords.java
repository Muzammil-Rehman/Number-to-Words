import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NumToWords {
    private String[] arrUnit;
    private String[] arrTenBef;
    private String[] arrTenAft;
    private String[] arrHunAft;
    private String hund;
    private QueuesLinkList<String> que;

    public NumToWords(){
        this.arrUnit = readFile("Units.txt", 10);
        this.arrTenBef = readFile("TensBeforeTwenty.txt", 10);
        this.arrTenAft = readFile("TensAfterTwenty.txt", 8);
        this.arrHunAft = readFile("AftHund.txt", 14);
        this.hund = "hundred";
        this.que = new QueuesLinkList<>();
    }

    public String[] readFile(String fileName,int size){
        File file = new File(fileName);
        String[] arr = new String[size];
        try {
            Scanner scan = new Scanner(file);
            int i = 0;
            while (scan.hasNextLine()){
                arr[i] = scan.nextLine();
                i++;
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File Does not exist");
        }
        return arr;
    }

    private String out(int num){
        String s = "";
        if(num < 10 && num > 0){
            s = s + arrUnit[num];
        }
        else {
            if(num < 20){
                s = s + arrTenBef[num%10];
            }
            else if(num > 20 && num < 100){
                s = s + arrTenAft[Math.floorDiv(num,10) - 2];
                if(num%10 != 0){
                    s = s + arrUnit[num%10];
                }
            }
        }
        return s;
    }

    private QueuesLinkList<String> words(long num){
        QueuesLinkList<String> temp = new QueuesLinkList<>();

        String s = "" + num;
        String word = "";

        int size = s.length();
        int l;
        int m = 1;
        for (int i = 0; i < size; i++) {
            if(s.length()%3==0 && s.length() > 2 && s.charAt(0) != '0'){
                word = this.arrUnit[Integer.parseInt("" + s.charAt(0))] + this.hund;
                if(s.length() > 3 && s.charAt(0) == '0' && s.charAt(1)=='0'){
                    word += this.arrHunAft[Math.floorDiv(s.length() - 3,3)] + " ";
                }
                m = 1;
                temp.enQueue(word);
            }
            else if(s.length()%3==2 && s.charAt(0) != '0'){
                l = Integer.parseInt("" + s.charAt(0) + s.charAt(1));
                i++;
                word = out(l);
                if(s.length() > 3){
                    word += this.arrHunAft[Math.floorDiv(s.length() - 3,3)] + " ";
                }
                m = 0;
                s = s.replaceFirst(""+ s.charAt(0), "");
                s = s.replaceFirst(""+ s.charAt(0), "");
                temp.enQueue(word);
            }
            else if(s.length()%3==1 && s.charAt(0) != '0'){
                l = Integer.parseInt("" + s.charAt(0));
                word = out(l);
                if(s.length() > 3){
                    word += this.arrHunAft[Math.floorDiv(s.length() - 3,3)] + " ";
                }
                m = 1;
                temp.enQueue(word);
            }
            if(m == 1){
                s = s.replaceFirst(""+ s.charAt(0), "");
            }
        }
        return temp;
    }

    public String numbersToWords(long num){
        String ans = "";

        que = words(num);
        int l = que.len();
        for (int i = 0; i < l; i++) {
            ans = que.deQueue() + ans;
        }

        return ans;
    }

    public static void main(String[] args) {
        NumToWords n = new NumToWords();
        String s =n.numbersToWords(101206500l);
        System.out.println(s);
    }
}