/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onetimepad;

import java.util.Scanner;

/**
 *
 * @author jubel
 */
public class OneTimePad {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        OneTimePadManage otp = new OneTimePadManage();
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("\n**** ONE TIME PAD ****");

        while(true){
            
            System.out.println("\n1: Encryption \n2: Decryption\n0: Exit");
            System.out.print("\nSelect your choice : ");
            int choice = sc.nextInt();
        
            switch (choice) {
                case 1:
                    {
                        System.out.print("Enter the plain text : ");
                        sc.nextLine();
                        String plainText = sc.nextLine();
                        System.out.print("Enter the key : ");
                        String key = sc.nextLine();
                        String cipherText = otp.getCipherText(plainText, key);
                        System.out.println("\n   CIPHER TEXT IS : "+cipherText);
                        break;
                    }
                case 2:
                    {
                        System.out.print("Enter the cipher text text : ");
                        sc.nextLine();
                        String cipherText = sc.nextLine();
                        System.out.print("Enter the key : ");
                        String key = sc.nextLine();
                        String plainText = otp.getPlainText(cipherText, key);
                        System.out.println("\n   PLAIN TEXT IS : "+plainText);
                        break;
                    }
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Enter correct input");
                    break;
            }
        }
        
       
    }
   
    
}
