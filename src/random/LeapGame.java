package random;

import java.util.Scanner;

public class LeapGame {

	public static boolean canWin(int leap, int[] game) {

	        int size =  game.length;
	        int currPos =  0;
	        boolean isFinished = false;
	        System.out.println("leap: "+game[leap]);
	     while(!isFinished ){
	      // System.out.println(currPos+"forward 1: "+game[currPos+1]);
	            if(currPos==size-1 && game[size-1]==0)
	                isFinished=true;
	         if(currPos<size-1 && (currPos+leap)<size-1 && game[currPos+leap]==0){
	        	  System.out.println("increment by leap " +game[currPos+leap]);
	                    currPos=currPos+leap;
	                
	                   continue;
	                } 
	             else  if(currPos<size-1 && game[currPos+1]==0){
	                    currPos++;
	                  System.out.println("increment by one");
	                   continue;         
	                } else if(currPos<size-1 && (currPos+leap)>size-1 ){
	                         isFinished=true;
	                    System.out.println("Finished by leap");
	                   
	                }  
	         else if(currPos<size-1 && currPos>0 &&game[currPos+1]!=0 && game[currPos-1] == 0 && game[currPos+leap] !=0){
	             for(int i =currPos;i>=0;i--){
	                 if(game[i]==0){
	                   if(game[i]==0 && game[i+leap]==0){
	                       currPos=currPos-i;
	                       continue;
	                   }
	                 }
	                
	                       
	             }
	         }
	         // System.out.println("Done break");
	        break;
	    
	     }   
	     
	            
	       if(isFinished)
	           return true;
	        else return false;
	        
	        
	    }

	    public static void main(String[] args) {
	        Scanner scan = new Scanner(System.in);
	        int q = scan.nextInt();
	        while (q-- > 0) {
	            int n = scan.nextInt();
	            int leap = scan.nextInt();
	            
	            int[] game = new int[n];
	            for (int i = 0; i < n; i++) {
	                game[i] = scan.nextInt();
	            }
	           
	            System.out.println((canWin(leap, game)) ? "YES" : "NO" );
	        }
	        scan.close();
	    }

}
