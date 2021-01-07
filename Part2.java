
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    
    String findSimpleGene (String dna, String startCodon, String stopCodon){
        //This function works with only lower case characters (to show that the function works properly)
        dna = dna.toLowerCase();
        startCodon = startCodon.toLowerCase();
        stopCodon = stopCodon.toLowerCase();
        if (dna.isEmpty())  return "";
        int startIndex, endIndex, len, cursor = 0, codonLength = 3;
        len = dna.length();
        while (len - cursor >= (6-1)){
            startIndex = dna.indexOf(startCodon, cursor);
            if (startIndex == -1)
                return "";
            endIndex = dna.indexOf(stopCodon, startIndex + codonLength);
            if (endIndex == -1)
                return "";
            if ((endIndex - startIndex) % codonLength == 0)
                return dna.substring (startIndex, endIndex + codonLength);
            cursor = endIndex + codonLength;
        }
        return "";
    }
    
    void testSimpleGene (){
        System.out.println ("GGGATATGGGTTAAGTC has gene "
                            + findSimpleGene ("GGGATATGGGTTAAGTC", "ATG", "TAA"));
        System.out.println ("GGTTAAGTC has gene "
                            + findSimpleGene ("GGTTAAGTC", "ATG", "TAA"));
        System.out.println ("ATGGGTGTC has gene "
                            + findSimpleGene ("ATGGGTGTC", "ATG", "TAA"));
        System.out.println ("ATGUUTAAGTC has gene "
                            + findSimpleGene ("ATGUUTAAGTC", "ATG", "TAA"));
        System.out.println ("ATGTAA has gene "
                            + findSimpleGene ("ATGTAA", "ATG", "TAA"));
        System.out.println ("GUATGAUTT has gene "
                            + findSimpleGene ("GUATGAUTT", "ATG", "TAA"));
        System.out.println ("---------------------------------------");
        System.out.println ("AAATGCCCTAACTAGATTAAGAAACC has gene "
                            + findSimpleGene ("AAATGCCCTAACTAGATTAAGAAACC", "ATG", "TAA"));
       
    }
}
