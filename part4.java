
/**
 * Write a description of part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;


public class part4 {
        void checkForYoutube (){
            URLResource url = new URLResource ("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
            int startIndex, stopIndex;
            for (String word
                 : url.words()){
                     if (word.contains("youtube.com")){
                         startIndex = word.indexOf("\"");
                         stopIndex = word.lastIndexOf(">");
                         System.out.println( word.substring(startIndex+1, stopIndex-1));
                    }
        }
}
}