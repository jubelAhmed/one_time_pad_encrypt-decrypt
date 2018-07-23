/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onetimepad;

public class OneTimePadManage {
    
    public String getCipherText(String plainText , String key){
        
        plainText = plainText.replaceAll(" ", "");
        plainText = plainText.toUpperCase();
        char [] textArray = plainText.toCharArray();
        
        key = key.replaceAll(" ", "");
        key = key.toUpperCase();
        char [] keyArray = key.toCharArray();
        
        char [] finalKeyArray = getFinalKeyArray(keyArray, textArray.length);
        
        
        int [] takePlainTextPosition = getTextPosition(textArray);
        int [] takeKeyPosition = getKeyArrayPosition(finalKeyArray);
        
        int [] resultPosition = new int[textArray.length];
        
        for(int i = 0 ; i < resultPosition.length ; i++){
            resultPosition[i] = takePlainTextPosition[i] + takeKeyPosition[i];
        }
        
        for(int i = 0 ; i<resultPosition.length ; i++){
            if(resultPosition[i] > 26){
                int result = (int)(resultPosition[i]/26);
                int finalResult = resultPosition[i]-(result*26);
                resultPosition[i] = finalResult;
            }
        }
        
        char [] cipherText = createText(resultPosition);
        
        displayResult("Encryption",textArray, takePlainTextPosition, finalKeyArray, takeKeyPosition, resultPosition, cipherText);
        
        return String.valueOf(cipherText);
    }
    
    public String getPlainText(String cipherText , String key){
         cipherText = cipherText.toUpperCase();
         char [] cipherTextArray = cipherText.toCharArray();
         
         key = key.replaceAll(" ", "");
         key = key.toUpperCase();
         char [] keyArray = key.toCharArray();
         
         char [] finalKeyArray = getFinalKeyArray(keyArray, cipherTextArray.length);
         
         int [] takeCipherTextPosition = getTextPosition(cipherTextArray);
         int [] takeKeyPosition = getKeyArrayPosition(finalKeyArray);
         
         int [] resultPosition = new int[cipherTextArray.length];
         
         for(int i = 0 ; i < resultPosition.length ; i++){
             resultPosition[i] = takeCipherTextPosition[i] - takeKeyPosition[i];
         }
          
         for(int i = 0 ; i< resultPosition.length ; i++){
             if(resultPosition[i] < 0){
                 int positiveValue = Math.abs(resultPosition[i]);
                 if(positiveValue > 26){
                     int result = (int) (positiveValue/26) ;
                     int finalResult = positiveValue - (26*result);
                     resultPosition[i] = 26-finalResult;
                     
                 }else{
                     resultPosition[i] = 26 + resultPosition[i];
                 }
             }
             
         }
         char [] plainText = createText(resultPosition);
         
         displayResult("Decruption",cipherTextArray, takeCipherTextPosition, finalKeyArray, takeKeyPosition, resultPosition, plainText);

         return String.valueOf(plainText); 
    }
    
   
    
    private char[] createText(int [] resultPosition){
        char [] text = new char[resultPosition.length];
        char [] alphabet = getAlphabet();
        for(int i = 0 ; i < text.length ; i++){
            for(int j =0 ; j<alphabet.length ; j++){
                if(resultPosition[i] == (j+1) ){
                    text[i] = alphabet[j];
                }
            }
        }
        return text;
    }
    
    private int[] getTextPosition(char [] textArray){
        char [] alphabet = getAlphabet();
        int [] takeTextPosition = new int[textArray.length];
         for(int i = 0 ; i<takeTextPosition.length ; i++){
            for(int f = 0 ; f< alphabet.length ; f++){
                if(textArray[i]== alphabet[f]){              
                        takeTextPosition[i] = f+1;
                        break;             
                }
            }
            
        }
         return takeTextPosition;
    }
    
    private int[] getKeyArrayPosition(char [] finalKeyArray){
        int [] takeKeyPosition = new int[finalKeyArray.length];
        char [] alphabet = getAlphabet();
        
        for(int i = 0 ; i<takeKeyPosition.length ; i++){
            for(int f = 0 ; f< alphabet.length ; f++){
                if(finalKeyArray[i] == alphabet[f]){
                   
                        takeKeyPosition[i] = f+1;
                        break;
                   
                   
                }
            } 
   
        }
        return takeKeyPosition;
    }
    
    private char[] getFinalKeyArray(char [] keyArray,int textArraylength){
        char [] finalKeyArray = new char[textArraylength];
        int k = 0;
        for(int i = 0; i<finalKeyArray.length ; i++){
            if(k == keyArray.length){
                k=0;
            }
            finalKeyArray[i] = keyArray[k];
            k++;
        }
        return finalKeyArray;
    }
    
    private char[] getAlphabet(){
        char [] alphabet = new char[26];
        int j = 0;
        for(char i = 'A' ; i<= 'Z'; i++){
            alphabet[j] = i;
            j++;
        }
        return alphabet;
    }
    
    public void displayResult(String textType,char [] plainText , int [] plainTextValue,char [] key , int [] keyValue , int [] result , char [] cipherText){
        System.out.println("\n\n************************   "+textType+"  ************************\n");
        System.out.print("P  = ");
        for(int i = 0 ; i<plainText.length ; i++){
            System.out.print(plainText[i] + "   ");
        }
        System.out.println("");
        rowPrint(plainText.length); 
         System.out.print("Pv = ");
          for(int i = 0 ; i<plainText.length ; i++){
              if(plainTextValue[i] > 9)
               System.out.print(plainTextValue[i]+ "  ");
              else System.out.print(plainTextValue[i]+ "   "); 
        }
        System.out.println("");
        rowPrint(plainText.length); 
        System.out.print("K  = ");
        for(int i = 0 ; i<plainText.length ; i++){
            System.out.print(key[i]+ "   ");
        }
        System.out.println("");
        rowPrint(plainText.length); 
         System.out.print("Kv = ");
        for(int i = 0 ; i<plainText.length ; i++){
            if(keyValue[i]>9)
            System.out.print(keyValue[i]+ "  ");
            else System.out.print(keyValue[i]+ "   ");
        }
        System.out.println("");
        rowPrint(plainText.length);  
         System.out.print("Cv = ");
        for(int i = 0 ; i<plainText.length ; i++){
            if(result[i]>9)
            System.out.print(result[i]+ "  ");
            else  System.out.print(result[i]+ "   ");
        }
        System.out.println("");
        rowPrint(plainText.length); 
         System.out.print("C  = ");
        for(int i = 0 ; i<plainText.length ; i++){
            System.out.print(cipherText[i] + "   ");
        }
        System.out.println("");
        rowPrint(plainText.length);  
        
        
    }
    
    public void rowPrint(int length){
        for(int i = 0 ; i< length+1 ; i++){
            System.out.print("----");
        }
        System.out.println("");
    }
}
