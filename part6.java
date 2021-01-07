
/**
 * Write a description of part6 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class part6 {
    private int howMany (String stringA, String stringB){
        int lenA, lenB, cursor = 0, count = 0; 
        lenA = stringA.length();
        lenB = stringB.length();
        
        if (lenA > lenB)
            return 0;
        
        while (cursor <= lenB){
            int i = stringB.indexOf(stringA, cursor);
            if ( i == -1 )
                break;
            
            ++count;
            cursor = i + lenA;
            }
        return count;
    }
    
    public void test (){
        String A = "SayanPaulIsVeryHandsome";
        String B = "a";
        String C = "an";
        System.out.println (A + " has " + howMany(B,A)
                            + " repititions of " + B + " and "
                            + howMany(C,A) + "repititions of " + C);
        System.out.println (A + " has " + howMany(C,A) + " repitions of " + C);
    }
}
