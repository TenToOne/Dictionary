import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Car{
    String author;
    String date;
    String text;
    String favorite;
    ArrayList<String> terms = new ArrayList<>();

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
            terms.add(set[i]);
        }
        terms.add(date);
        String temp = text.replace(","," ");
        temp = temp.replace("."," ");
        set = temp.split(" ");
        for(int i=0;i<set.length;i++){
            if(!set[i].equals("")) terms.add(set[i]);
        }
        temp = favorite.replace(","," ");
        temp = temp.replace("."," ");
        set = temp.split(" ");
        for(int i=0;i<set.length;i++){
            if(!set[i].equals("")) terms.add(set[i]);
        }
    }
}

public class Main{
    static int count=0;

    public static void inputData(String file) throws FileNotFoundException {
        Scanner input = new Scanner(new File(file));
        input.nextLine();
        ArrayList<Car> carList = new ArrayList<Car>();
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
    }

}
