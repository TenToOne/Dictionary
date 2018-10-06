import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeMap;

class Text{
    String author;
    String date;
    String text;
    String favorite;
    HashSet<String> terms = new HashSet<>(); // 단어의 중복 저장을 막기 위해 Set으로 구현

    public Text(String author,String date,String text,String favorite){
        this.author=author;
        this.date=date;
        this.text=text;
        this.favorite=favorite;
        makeTerm();
//        System.out.println(terms);
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
    static int count=0,sum=0,max=-1,min=-1;
    static ArrayList<Text> carList = new ArrayList<Text>();
    static TreeMap<String,ArrayList<Integer>> dictionary = new TreeMap<>(); // 사전은 균형이진트리로 만들어진 TreeMap을 만들어 구현

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
            Text c = new Text(author,date,text,favorite);
            int size=c.terms.size();
            sum+=size;
            if(max==-1|size>max) max=size;
            if(min==-1|size<min) min=size;
//            System.out.println(c+"\n\n");
            carList.add(c);
            count++;
        }
    }

    public static void main(String[]args)  throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
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
//        System.out.println("Number of Car : "+count);
//        System.out.println("Number of Car : "+carList.size());
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
            // 트리맵의 키 값을 단어, 벨류값을 해당 단어의 포스팅 리스트로 사전을 트리에 구현
        }
        System.out.println();
        System.out.println("문서의 수 : "+count);
        System.out.println("문서당 평균 단어 수 : "+(sum/count));
        System.out.println("문서의 포함된 단어 중 가장 큰 수와 작은 수: "+max+","+min);
        System.out.println();
        System.out.print("원하는 단어를 입력하세요 : ");
        ArrayList postingList = dictionary.get(input.next());
        if(postingList==null){
            System.out.println("None");
        }
        else{
            System.out.println(postingList);
            System.out.print("원하는 문서의 id를 입력하세요 : ");
            int id = input.nextInt();
            if(postingList.contains(id)){
                System.out.println(carList.get(id));
            }
            else {
                System.out.println("문서가 존재하지 않거나 해당 단어가 없는 문서 입니다.");
            }
        }
    }

}
