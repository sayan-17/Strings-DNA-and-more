
/**
 * Write a description of Names here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OverloadEg {
    public static void print(int num){
        System.out.println(num);
    }
    public static void print(String str,int num){
        System.out.println(str + " " + num);
    }
    
    public test(){
        print(3);
        print("Hello", 3);
    }
}
