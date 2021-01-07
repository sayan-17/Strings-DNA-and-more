import edu.duke.*;


public class part7 {
    
    private final int codonLen = 3;
    private final String startCodon = "ATG";
    
    private int findStartCodon (String dna, int cursor){
        return dna.indexOf(startCodon, cursor);
    }
    
    private int findStopCodon (String dna, int startIndex, String stopCodon){
        if (dna.isEmpty())  
            return -1;
        int len, endIndex, cursor = startIndex + 3;
        Boolean checkFlag = true;
        len = dna.length();
        cursor = startIndex;
        while (len - cursor >= (6-1)){
            endIndex = dna.indexOf(stopCodon, cursor + codonLen);
            if (endIndex == -1){
                checkFlag = false;
                break;
            }
            if ((endIndex - startIndex) % codonLen == 0)
                return endIndex;
            cursor = endIndex + codonLen;
        }
        if (checkFlag)
            return cursor;
        return -1;
    }
    
    private String findGene (String dna, int cursor){
        if (dna.isEmpty())          
            return "";
        int startIndex, endIndexTAA, endIndexTGA, endIndexTAG, minimun;
        
        startIndex = findStartCodon (dna, cursor);
        if (startIndex == -1)
            return "";
        endIndexTAA = findStopCodon(dna, startIndex, "TAA");
        endIndexTGA = findStopCodon(dna, startIndex, "TGA");
        endIndexTAG = findStopCodon(dna, startIndex, "TAG");;
        
        if( (endIndexTAA != -1) && ((endIndexTAA < endIndexTGA && endIndexTGA != -1) || 
                (endIndexTAA < endIndexTAG && endIndexTAG != -1) || (endIndexTGA == -1 && endIndexTAG == -1) )){
                    return dna.substring (startIndex, endIndexTAA+3);}
        else if( (endIndexTGA != -1) && ((endIndexTGA < endIndexTAA && endIndexTAA != -1) || 
                    (endIndexTGA < endIndexTAG && endIndexTAG != -1) || (endIndexTAA == -1 && endIndexTAG == -1) ) ){
                    return dna.substring (startIndex, endIndexTGA+3);}
        else if( (endIndexTAG != -1) && ((endIndexTAG < endIndexTAA && endIndexTAA != -1) || 
                    (endIndexTAG < endIndexTGA && endIndexTGA != -1) || (endIndexTGA == -1 && endIndexTAA == -1) ) ){
                    return dna.substring (startIndex, endIndexTAG+3);}
        else
                return "";
        
    }
    
    private StorageResource printAllGenes(String dna){
        StorageResource store = new StorageResource();
        int cursor = 0;
        while (true){
            String currentGene = findGene (dna, cursor);
            if (currentGene.isEmpty())
                break;
            store.add(currentGene);
            cursor = dna.indexOf (currentGene, cursor) + currentGene.length();
        }
        
        return store;
    }
    
    private int howManyGenes(String dna){
        int cursor = 0, count = 0;
        while (true){
            String currentGene = findGene (dna, cursor);
            if (currentGene.isEmpty())
                return count;
            ++count;
            cursor = dna.indexOf (currentGene, cursor) + currentGene.length();
        }
    }
    
    private float cgRatio (String dna){
        float sum = 0;
        int len = dna.length();
        for (int i = 0; i < len; ++i){
            if (dna.charAt(i) == 'C')
                sum += 1;
            else if (dna.charAt(i) == 'G')
                sum += 1;
        }
        return sum / len;
    }
    
    private void processGenes(StorageResource str){
        //all the Strings in sr that are longer than 9 characters - count+ display
        int countGenes = 0, countCG = 0, max = 0;
        String largest = new String();
        System.out.println ("Strings with length > 60 :");
        for ( String string : str.data()){
            int len = string.length();
            if (len > 60){
                System.out.println (string);
                ++countGenes;
            }
            if (max <= len ){
                largest = string;
                max = len;
            }
        }
        System.out.println ("Longest Gene : " + largest);
        System.out.println ("Longest Gene length : " + max);
        System.out.println ("Strings with C-G ratio > 0.35 :");
        for ( String string : str.data()){
            if (cgRatio(string) > 0.35){
                System.out.print(string + " . ");
                ++countCG;
            }
        }
    }
    
    public void test(){
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();
        dna = dna.toUpperCase();
        dna.concat ("ATGTAAATGTAGATGTGA");
        StorageResource str = printAllGenes(dna);
        //System.out.println ("The DNA string is - " + dna);
        for ( String gene : str.data()){
        System.out.println ("1. " + gene);
        }
        System.out.println ("The DNA string has " + howManyGenes(dna) + " genes");
        System.out.println ("GG ratio of DNA string is " + cgRatio (dna) + " genes");
        processGenes (str);
    }
    
}
