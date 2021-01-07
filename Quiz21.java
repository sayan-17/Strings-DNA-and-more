
/**
 * Write a description of Quiz21 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Quiz21 {
    
    
    public void findAbc(String input) {
    int index = input.indexOf("abc");
    int len = input.length();
    System.out.println("For string " + input + " with length = " + len);
    while (true) {
        if (index == -1) {
            break;
        }
        System.out.println((int)(index+1) + " -> " + (int)(index +4));
        if (index + 4 > len)
            break;
        String found = input.substring(index+1, index+4);
        System.out.println(" - " + found);
        index = input.indexOf("abc", index+3);
        System.out.println ("After updation index = " + index);
    }
    System.out.println("......................");
}
   public void test() {
    //no code yet
    findAbc("abcd");
    findAbc("abcdabc");
    findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
    findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
    findAbc("abcabcabcabca");
}
}
