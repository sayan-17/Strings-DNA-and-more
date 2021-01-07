public class part5 {
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
    
    private void printAllGenes(String dna){
        int cursor = 0;
        while (true){
            String currentGene = findGene (dna, cursor);
                        if (currentGene.isEmpty()){
                System.out.println ("........");
                break;
            }
            System.out.println (currentGene);
            cursor = dna.indexOf (currentGene, cursor) + currentGene.length();
        }
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
    
    public void test(){
        String dna = "ATGTAAGATGCCCTAGT";
        dna = dna.toUpperCase();
        System.out.println ("The DNA string is - " + dna);
        printAllGenes (dna);
        System.out.println ("The DNA string has " + howManyGenes(dna) + " genes");;
    }
}