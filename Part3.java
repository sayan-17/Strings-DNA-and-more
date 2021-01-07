
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    
    Boolean twoOccurrences (String stringA, String stringB){
        int lenA = stringA.length();
        int indexTest = stringB.indexOf(stringA);
        if (indexTest == -1)
            return false;
        indexTest = stringB.indexOf(stringA, indexTest+lenA);
        if (indexTest == -1)
            return false;
        return true;
    }
    
    void testTwoOccurrences (){
        System.out.println ("'Sayan Paul' has two times of 'a'" + twoOccurrences("a","Sayan Paul"));
        System.out.println ("'Sexy Computer' has two times of 'Computer' - " + twoOccurrences("Computer","Sexy Computer"));
        System.out.println ("'I like to live like a hero' has two times of 'like' - " + twoOccurrences("like","I like to live like a hero"));
        System.out.println ("'I like to live like a hero' has two times of 'live' - " + twoOccurrences("live","I like to live like a hero"));
        System.out.println ("'I like to live like a hero' has two times of 'xyz' - " + twoOccurrences("xyz","I like to live like a hero"));
    }
    
    String lastPart (String stringA, String stringB){
       int lenA = stringA.length(); 
       int indexTest = stringB.indexOf(stringA);
       if (indexTest == -1)
            indexTest = 0;
       else
            indexTest += lenA;
       return stringB.substring(indexTest);
    }
    
    void testLastPart (){
        System.out.println("What comes after \"an\" in \"banana\"? - " + lastPart("an", "banana"));
        System.out.println("What comes after \"forest\" in \"zoo\"? - " + lastPart("forest", "zoo"));
        System.out.println("What comes after \"yan\" in \"SayanPaul\"? - " + lastPart("yan", "SayanPaul"));
        System.out.println("What comes after \"an\" in \"Doritoan\"? - " + lastPart("an", "Doritoan"));
        System.out.println("What comes after \"D\" in \"banana\"? - " + lastPart("D", "banana"));
    }
}
