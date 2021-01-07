
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    
    String findSimpleGene (String dna){
        if (dna.isEmpty())  return "";
        int startIndex, endIndex, len, cursor = 0, codonLength = 3;
        len = dna.length();
        while (len - cursor >= (6-1)){
            startIndex = dna.indexOf("ATG", cursor);
            if (startIndex == -1)
                return "";
            endIndex = dna.indexOf("TAA", startIndex + 3);
            if (endIndex == -1)
                return "";
            if ((endIndex - startIndex) % codonLength == 0)
                return dna.substring (startIndex, endIndex + 3);
            cursor = endIndex + 3;
        }
        return "";
    }
    
    void testSimpleGene (){
        System.out.println ("GGGATATGGGTTAAGTC has gene "
                            + findSimpleGene ("GGGATATGGGTTAAGTC"));
        System.out.println ("GGTTAAGTC has gene "
                            + findSimpleGene ("GGTTAAGTC"));
        System.out.println ("ATGGGTGTC has gene "
                            + findSimpleGene ("ATGGGTGTC"));
        System.out.println ("ATGUUTAAGTC has gene "
                            + findSimpleGene ("ATGUUTAAGTC"));
        System.out.println ("ATGTAA has gene "
                            + findSimpleGene ("ATGTAA"));
        System.out.println ("GUATGAUTT has gene "
                            + findSimpleGene ("GUATGAUTT"));
        System.out.println ("UATGAGTAAAGTUATGTAA has gene "
                            + findSimpleGene ("UATGAGTAAAGTUATGTAA"));
    }
}
