import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeMap;

class Car{
    String author;
    String date;
    String text;
    String favorite;
    HashSet<String> terms = new HashSet<>();

    public Car(String author,String date,String text,String favorite){
        this.author=author;
        this.date=date;
        this.text=text;
        this.favorite=favorite;
        makeTerm();
        System.out.println(terms);
    }

    @Override
    public String toString() {
        return "Author : "+author+"\n"+"Date : "+date+"\n"+"Text : "+text+"\n"+"Favorite : "+favorite+"\n";
    }

    public void makeTerm(){
        String[] set = author.split(" ");
        for(int i=0;i<set.length;i++){
            terms.add(set[i].toLowerCase());
        }
        terms.add(date);
        String temp = text.replace(","," ");
        temp = temp.replace("."," ");
        set = temp.split(" ");
        for(int i=0;i<set.length;i++){
            if(!set[i].equals("")) terms.add(set[i].toLowerCase());
        }
        temp = favorite.replace(","," ");
        temp = temp.replace("."," ");
        set = temp.split(" ");
        for(int i=0;i<set.length;i++){
            if(!set[i].equals("")) terms.add(set[i].toLowerCase());
        }
    }
}

public class Main{
    static int count=0;
    static ArrayList<Car> carList = new ArrayList<Car>();
    static TreeMap<String,ArrayList<Integer>> dictionary = new TreeMap<>();

    public static void inputData(String file) throws FileNotFoundException {
        Scanner input = new Scanner(new File(file));
        input.nextLine();
        while(input.hasNextLine()) {
            input.nextLine();
            String date = input.nextLine();
            date=date.replaceAll("<DATE>","");
            date=date.replaceAll("</DATE>","");
            String author = input.nextLine();
            author=author.replaceAll("<AUTHOR>","");
            author=author.replaceAll("</AUTHOR>","");
            String text = input.nextLine();
            text=text.replaceAll("<TEXT>","");
            text=text.replaceAll("</TEXT>","");
            String favorite = input.nextLine();
            favorite=favorite.replaceAll("<FAVORITE>","");
            favorite=favorite.replaceAll("</FAVORITE>","");
            input.nextLine();
            Car c = new Car(author,date,text,favorite);
            System.out.println(c+"\n\n");
            carList.add(c);
            count++;
        }
    }

    public static void main(String[]args)  throws FileNotFoundException {
        inputData("2007_acura_mdx");
        inputData("2007_acura_rl");
        inputData("2007_acura_tl");
        inputData("2007_acura_tsx");
        inputData("2007_audi_a3");
        inputData("2007_audi_a4");
        inputData("2007_audi_a6");
        inputData("2007_audi_a8");
        inputData("2007_audi_q7");
        inputData("2007_audi_rs4");
        inputData("2007_audi_s8");
        inputData("2007_bmw_3_series");
        inputData("2007_bmw_5_series");
        inputData("2007_bmw_6_series");
        inputData("2007_bmw_x3");
        inputData("2007_bmw_x5");
        inputData("2007_bmw_z4");
        inputData("2007_chevrolet_aveo");
        inputData("2007_chevrolet_corvette");
        inputData("2007_chevrolet_impala");
        inputData("2007_chevrolet_malibu");
        System.out.println("Number of Car : "+count);
        System.out.println("Number of Car : "+carList.size());
        for(int i=0;i<carList.size();i++){
            String[] set=carList.get(i).terms.toArray(new String[carList.get(i).terms.size()]);;
            for(int j=0;j<set.length;j++){
//                System.out.print(set[j]+" ");
                if(dictionary.get(set[j])==null){
                    ArrayList<Integer> value = new ArrayList<>();
                    value.add(i);
                    dictionary.put(set[j],value);
                }
                else{
                    ArrayList value = dictionary.get(set[j]);
                    value.add(i);
                }
            }
//            System.out.println();
        }
        System.out.println();
        System.out.println(dictionary.get("this"));
        System.out.println(carList.get(0));
    }

}
