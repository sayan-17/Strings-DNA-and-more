import edu.duke.*;


public class Remedy {
    
    private final int codonLen = 3;
    private final String startCodon = "ATG";
    
    private int findStartCodon (String dna, int cursor){
        return dna.indexOf(startCodon, cursor);
    }
    
    private int findStopCodon (String dna, int startIndex, String stopCodon){
        int stopIndex;
        stopIndex = dna.indexOf(stopCodon, startIndex);
        while(stopIndex != -1){
            if ((stopIndex - startIndex) % 3 == 0)
                return stopIndex;
            else
                stopIndex = dna.indexOf(stopCodon, stopIndex);
        }
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
    
    private StorageResource storeAllGenes(String dna){
        StorageResource store = new StorageResource();
        int cursor = 0, i =1;
        while (true){
            String currentGene = findGene (dna, cursor);
            if (currentGene.isEmpty())
                break;
            System.out.println(i++ + " " + currentGene);
            store.add(currentGene);
            cursor = dna.indexOf (currentGene, cursor) + currentGene.length();
        }
        
        return store;
    }
    
    
    
    void testNoGenes (){
            URLResource url = new URLResource ("https://users.cs.duke.edu/~rodger/GRch38dnapart.fa");
            String dna = url.asString();
            int cursor = 0, count = 0;
            while (true){
                String currentGene = findGene (dna, cursor);
                if (currentGene.isEmpty())
                break;
                count++;
                cursor = dna.indexOf(currentGene, cursor) + currentGene.length();
            }
            System.out.println("No of genes - " + count);
        }
            
    
    
}