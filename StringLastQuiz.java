import edu.duke.*;


public class StringLastQuiz {
    
    private final int codonLen = 3;
    private final String startCodon = "ATG";
    
    private int findStartCodon (String dna, int cursor){
        return dna.indexOf(startCodon, cursor);
    }
    
    /*private int findStopCodon (String dna, int startIndex, String stopCodon){
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
    }*/
    
    private int findStopCodon (String dna, int startIndex, String stopCodon){
        int stopIndex;
        stopIndex = dna.indexOf(stopCodon, startIndex);
        while(stopIndex != -1){
            System.out.println("111");
            if ((stopIndex - startIndex) % 3 == 0)
                return stopIndex;
            else
                stopIndex = dna.indexOf(stopCodon, stopIndex + 1);
        }
        return -1;
    }
    
    /*private String findGene (String dna, int cursor){
        if (dna.isEmpty())          
            return "";
        int startIndex, endIndexTAA, endIndexTGA, endIndexTAG, minimun;
        System.out.println("I am here in find gene");
        startIndex = findStartCodon (dna, cursor);
        System.out.println("startindex = " + startIndex);
        if (startIndex == -1)
            return "";
        endIndexTAA = findStopCodon(dna, startIndex, "TAA");
        endIndexTGA = findStopCodon(dna, startIndex, "TGA");
        endIndexTAG = findStopCodon(dna, startIndex, "TAG");;
        System.out.println("(taa,tga,tag)  = " + endIndexTAA + endIndexTGA + endIndexTAG);
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
        /*if( (taa != -1)&&((taa < tag && tag != -1)||(taa < tga && tga != -1)) )
            gene = dna.substring(atg,taa+3);
        else if( (tga != -1)&&((tga < tag && tag != -1)||(tga < taa && taa != -1)) )
            gene = dna.substring(atg,tga);
        else if( (tag != -1)&&((tag < taa && taa != -1)||(tag < tga && tga != -1)) )
            gene = dna.substring(atg,tag);
        else
            gene = "";
        
    }*/
    
    private String findGene (String dna, int cursor){
        if(dna.isEmpty())
            return "";
        
        int startIndex = dna.indexOf("ATG",cursor);
        
        if(startIndex == -1)
            return "";
            
        int taa = findStopCodon(dna,startIndex,"TAA");
        int tga = findStopCodon(dna,startIndex,"TGA");
        int tag = findStopCodon(dna,startIndex,"TAG");
        int minIndex = 0;
        
        if(taa == -1 || (tga != -1 && tga < taa))
            minIndex = tga;
        else
            minIndex = taa;
        
        if(minIndex == -1 || tag != -1 && tag < minIndex)
            minIndex = tag;
        
        if(minIndex == -1)
            return "";
        
        return dna.substring(startIndex,minIndex + 3);
    }
    
    /*private StorageResource storeAllGenes(String dna){
        StorageResource store = new StorageResource();
        int cursor = 0, i =1;
        while (true){
            String currentGene = findGene (dna, cursor);
            if (currentGene.isEmpty())
                break;
            if (store.contains(currentGene)){
                cursor = dna.indexOf(currentGene, cursor) + currentGene.length();
                continue;
            }
            System.out.println(i++ + " " + currentGene);
            store.add(currentGene);
            cursor = dna.indexOf(currentGene, cursor) + currentGene.length();
        }
        return store;
    }*/
    
    private StorageResource storeAllGenes(String dna){
        StorageResource store = new StorageResource();
        int cursor = 0;
        
        while(true){
            String gene = findGene(dna,cursor);
            
            if(gene.isEmpty())
                break;
            System.out.println("000");
            /*if(store.contains(gene))
                continue;*/
            
            store.add(gene);
            cursor = dna.indexOf(gene,cursor) + gene.length();
        }
        return store;
    }
    
    private void howManyGenes(StorageResource str){
        int count = 0;
        for ( String string : str.data())
            count++;
        System.out.println("No. of genes = " + str.size() + " " + count + "(count)"); 
    }
    
    private double cgRatio (String dna){
        double sum = 0;
        int len = dna.length();
        for (int i = 0; i < len; ++i){
            if (dna.charAt(i) == 'C')
                sum += 1;
            else if (dna.charAt(i) == 'G')
                sum += 1;
        }
        return sum / len;
    }
    
    private void geneGT60(StorageResource str){
        int countGenes = 0, max = 0;
        String largest = new String();
        for ( String string : str.data()){
            int len = string.length();
            if (len > 60){
                ++countGenes;
            }
            if (max <= len ){
                largest = string;
                max = len;
            }
            //System.out.println(" countGenes = " + countGenes + ",present len = " + len + ", maximum = " + max);
        }
        System.out.println ("No. of the Genes with length > 60 : " + countGenes);
        System.out.println ("Length of the longest Gene : " + max);
    }
        
    private void geneGT35(StorageResource str){
        int countCG = 0;
        for ( String string : str.data()){
            if (cgRatio(string) > 0.35){
                ++countCG;
            }
        }
        System.out.println ("No. of the Genes with CG ratio > 0.35 : " + countCG);
    }
    
    private void howManythisCodons(String dna, String codon){
        int index, cursor = 0, count = 0;
        while(true){
        index = dna.indexOf(codon, cursor);
        if(index == -1)
            break;
        ++count;
        cursor = index + codonLen;
        }
        System.out.println("No. of " + codon + "'s present in the DNA sample is " + count);
    }
    
    void noOfGenes (){
            URLResource url = new URLResource ("https://users.cs.duke.edu/~rodger/GRch38dnapart.fa");
            String dna = url.asString();
            //String dna = "ATGGGTTAAGTCATGCCCTAGATGTAAGATGCCCTAGT";
            //String dna = "ATGAAATACAGGAGACTGTACCCCAGAAGCAGCGGGTTCACTGCTCCATTGATTAAGCAAGTCTGGGACACACATGTAGCTAAGCTGTGAGTTCTGTACCAGCGATCCCAACACCCACGCCCTCAGAAAGACACTGGTGTGGGGCCTGGGTGCTTGTCAGGCCTGAAAGTGGAGAGCACGGGCCAGAGACACTGAGTAGGGGGAACCCACCCTAGGGCTCTGAGGGACGACGATGTGGGGAGCTGGTGACAGAGCCTGAGCTGGCCCAATGTTGCACGGTGGGGACAGATTCGAGGTACAGTGGGGACTGGTGACCTCAGTTCCCAGTGTCCCAGCCTGGCCTCCCAGTCCACCCAGCAATTAGTGGGTGCTGCCCTGCAAAGACTCTGGGGGTGCCTCAGCCCTCCTCATCACACGTGACTGGTGACTTCTGTGTCCACCCGCACAATAAGAGGGATCTTCTCTCACTTTCAGGCAAGCCCAAGAAAGTCAGGGGCCTATGTGAGCCAAAGAGGAGAGAAGGTGATGCCTCAGCCCAGTGTTTCTGCCCCACCTCGCTTGTGGCCTTCGGAACTTGATTTGCACCGCAGGAAAATGGGCAATGAAAACCCCTCCCTAACTGGCTTCTCAGTCCACTCTGACCAGCCCACTGCACAGCGCCCACCCTGCAGCTCCAGGTACAGAGGCTGGGATGGCTCTGGGCTGACCTAA";
            StorageResource store = storeAllGenes(dna);
            displayStore(store);
            howManyGenes(store);
            geneGT60(store);
            geneGT35(store);
            howManythisCodons(dna,"CTG");
            displayStore(store);
    }
    
    void displayStore (StorageResource store){
        int i = 1;
        System.out.println( "-----------------------------------------------------------------------------" );
        for ( String string : store.data()){
            System.out.println( i++ + ". " + string);
            }
        }
}