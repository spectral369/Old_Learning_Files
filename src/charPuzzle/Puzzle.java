package charPuzzle;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;

public class Puzzle {
	public static void main(String[] args) {
	
		String result =null;
		System.load("/usr/local/lib/libtesseract.so");
		String low = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		low = low.toLowerCase();
		 String whiteList = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"/*+low+"0123456789!"*/;
		  Tesseract1 instance = new Tesseract1(); // JNA Direct Mapping
	         instance.setTessVariable("tessedit_char_whitelist", whiteList);
	         
	         instance.setDatapath("/usr/local/share/tessdata/"); 
	         instance.setLanguage("eng");
	         try {
		           result  = instance.doOCR(new File("/home/spectral369/image4.png"));
		           
		         //  System.out.println(result);
		        } catch (TesseractException e) {
		            System.err.println(e.getMessage());
		}
	     char[][] mat =   setCharMatrix(result);
	    //printMatrix(mat);
	   //String dia = "oop".toUpperCase();
	    String[] dictionary = {"hitandrun","flcoins","cryptocoins","SnatchList","passkey","vip","ratio","gmail"};
	
	    Map<String, Boolean> map =  new LinkedHashMap<>();
	    Map<String,Point> location =  new LinkedHashMap<>();
 	   
	  
	     char[][] matcopy = mat;
	     boolean found = false;
	     
	     for(int i = 0;i<4;i++) {
	    	 for(int j = 0;j<dictionary.length;j++) {
	    	

	    	 found =  searchHorizontally(matcopy,dictionary[j],location);
	    	 if(found) {
	    		

	    		 map.put(dictionary[j], found);
	    		 continue;
	    	 }
	    	 found =  searchVerically(matcopy,dictionary[j],location);
	    	 if(found) {
	    		

	    		 map.put(dictionary[j], found);
	    		 continue;
	    	 }
	    	 found =  searchDiagonally(matcopy,dictionary[j],location);
	    	 if(found) {
	    		

	    		 map.put(dictionary[j], found);
	    		 continue;
	    	 }
	    	 found =  searchDiagonallyBack(matcopy,dictionary[j],location);
	    	 if(found) {
	    		
	    		 map.put(dictionary[j], found);
	    		 continue;
	    	 }
	    	
	    	 }
	    	 rot(matcopy);
	    	
	     }
	     
	     
	    for(String s:map.keySet()) {
	    	System.out.println("Word: "+s+" found: "+map.get(s));
	    }
	   // System.out.println( searchHorizontally(mat,dia,location));
	   // System.out.println(searchVerically(mat,dia,location));
	   // System.out.println( searchDiagonally(mat,dia,location));
	  //  System.out.println( searchDiagonallyBack(mat,dia,location));
	    for(String s:location.keySet()) {
	    	List<Integer> x =  location.get(s).getX();
	    	List<Integer> y = location.get(s).getY();
	    	System.out.println("Location of word: "+s+" found x: "+x+" y: "+y);
	    }
	     
	     

	    
	   // System.out.println( searchHorizontally(mat,dia));
	   // System.out.println( searchHorizontally(mat,dia));
	    //System.out.println(searchVerically(mat,dia));
	   // System.out.println( searchDiagonally(mat,dia));
	//   System.out.println( searchDiagonallyBack(rot(mat),dia));
	
	  
	         
			
	}
	
	public static char[][] setCharMatrix(String s) {
		String lines[] = s.split("\\r?\\n");
		char[][] matrix =  new char[lines.length][lines[0].length()];
		for(int h = 0;h<lines[0].length();h++)
			lines[h] = lines[h].replaceAll(" ", "");
	
	
		for(int i = 0;i<lines[0].length();i++) {
			
			for(int j=0;j<lines[0].length();j++) {
				
				matrix[i][j] = lines[i].charAt(j);
				
			}
		}
		return matrix;
	}
	
	public static void printMatrix(char[][] matrix) {
		
		for(int i = 0;i<matrix.length;i++) {

			for(int j=0;j<matrix.length;j++) {
				System.out.print(matrix[i][j]);
			}
			System.out.println();
			
		}
		
	}
	
	public static boolean searchHorizontally(char[][] matrix,String wo,Map<String, Point> location) {
		
		char[] word =  wo.trim().toUpperCase().toCharArray();
	
		
		boolean found = false;
	
		int y = 0;
		//forward
		for(int i = 0;i<matrix.length;i++) {

			for(int j=0;j<matrix.length;j++) {
				//System.out.println("current char: "+matrix[i][j]);
				if(matrix[i][j] == word[0]) {

					for(int g=j;g<matrix.length;g++) {
					//	System.out.println("matrix: "+matrix[i][g]);
						if(matrix[i][g]==word[y]) {
						//	System.out.println("matrix: "+matrix[i][g]+" "+word[y]);
						
							y++;
							
							if(y==word.length) {
							found=true;
							Point p =  new Point();
							p.addX(i);
							for(int x  = j;x<=g;x++) {
								p.addY(x);
							}
							location.put(wo,p );
							break;
							}
						}else {y=0; break;}
					}
					y=0;
				}
			
			
			}
		}
			//System.out.println("----------------------------------------------------");
			//backward
	
		
		for(int a = 0;a<matrix.length;a++) {
				//System.out.println(matrix[a]);
				
				for(int b = matrix.length-1;b>=0;b--) {
					
				//	System.out.println("current char: "+matrix[a][b]);
				
					if(matrix[a][b] == word[0]) {
						
						for(int g=b;g>=0;g--) {
					
						
							if(matrix[a][g]==word[y]) {
						
								y++;
								
								if(y==word.length) {
								found=true;
								Point p =  new Point();
								p.addX(a);
								for(int x  = b;x>=g;x--) {
									p.addY(x);
								}
								location.put(wo,p );
								break;
								
							}
						}else {y=0;break;}
							
						}
				y=0;
				}
					
			}
			
		}
	
		
	//	System.out.println("found: "+found);
		return found;
	}
	
	public static boolean searchVerically(char[][] matrix,String ver,Map<String,Point> location) {
	char[] word =  ver.trim().toUpperCase().toCharArray();
		
		boolean found = false;
		
		int y = 0;
		//forward
		
		for(int i = 0;i<matrix.length;i++) {

			for(int j=0;j<matrix.length;j++) {
				//System.out.println("current char: "+matrix[j][i]);
				if(matrix[j][i] == word[0]) {

					for(int g=j;g<matrix.length;g++) {
						if(matrix[g][i]==word[y]) {
						//	System.out.println("matrix: "+matrix[j][i]+" "+word[y]);
							y++;
							
							if(y==word.length) {
							found=true;
							Point p =  new Point();
							p.addX(i);
							for(int x  = j;x<=g;x++) {
								p.addY(x);
							}
							location.put(ver,p );
							break;
							}
						}else {y=0; break;}
					}
					y=0;
				}
			}
			
			
			
		}
		
		///System.out.println("----------------------------------------------------");
		
		//backward
		
		for(int a = 0;a<matrix.length;a++) {

			for(int b=matrix.length-1;b>=0;b--) {
				//System.out.println("current char: "+matrix[b][a]);
				
				if(matrix[b][a] == word[0]) {

					for(int g=b;g>=0;g--) {
						
						if(matrix[g][a]==word[y]) {
						//	System.out.println("matrix: "+matrix[b][a]+" "+word[y]);
							y++;
							
							if(y==word.length) {
							found=true;
							Point p =  new Point();
							p.addX(a);
							for(int x  = b;x>=g;x--) {
								p.addY(x);
							}
							
							
							
							location.put(ver,p );
							break;
							
						}
					}else {y=0;break;}
						
					}
			y=0;
				}
			}
		
		
	}
		
		
		
	//	System.out.println(found);
		return found;
		
		
	}
	//precum HP spell(stackoverflow)
	public static boolean searchDiagonally(char[][] matrix,String wd,Map<String,Point> location) {
	
	
		
		boolean found = false;
		
		
		   int c = 0;
		    int count = matrix.length + matrix[0].length -1;
		    int i = 0, j = 0;
		    //There can be at most  m + n -1 diagonals to be printed
		    while (c < count) {
		        //Start printing diagonals from i and j
		       found = printDiagonal(i, j, matrix,wd,location);
		       if(found)
		    	   return found;
		        
		        if (i < matrix.length -1) {
		            //We increment row index until we reach the max number of rows
		            i++;
		        } else if (j < matrix[0].length - 1) {
		            //We are at maximum index of row; so its time to increment col index
		            //We increment column index until we reach the max number of columns
		           j++;
		        }
		     
		        
		        c++;
		    }
		    return found;
	}
	
	
	
	public static boolean searchDiagonallyBack(char[][] matrix,String dia,Map<String,Point> location) {
		char[] word =  dia.trim().toUpperCase().toCharArray();
		Point p =  new Point();
			
			boolean found = false;
			
			int u = 0;
			for (int k = 0; k <= 2 * (matrix.length - 1); ++k) {
			    int yMin = Math.max(0, k - matrix.length + 1);
			    int yMax = Math.min(matrix.length - 1, k);
			    for (int y = yMin; y <= yMax; ++y) {
			        int x = k - y;
			      //  System.out.print(matrix[y][x]+" ");
			        if(matrix[y][x] == word[u]) {
			        	//System.out.println(u);
			        	
			        	u++;
			        	if(u==word.length) {
			        		for(int rt  = word.length;rt>0;rt--) {
				        	p.addX(y--);
				        	p.addY(x++);
			        		}
			        	}
			        	if(u==word.length) {
							found=true;
							//System.out.println(found);
							
						
							location.put(dia,p );
							u=0;
							break;
							
						}
			        	
			        }else {u=0;}
			        
			    }
			   // System.out.println();
			}
			return found;
		}
	

	
	
	public static char[][] rot(char[][]matrix){
		 char[][] ret =  matrix;
		 int z = matrix.length;
		    for (int i = 0; i < z / 2; i++) {
		        for (int j = 0; j < (z / 2 + z % 2); j++) {
		            int x = i, y = j;
		            char temp = ret[x][y];
		            for (int k = 0; k < 4; k++) {
		                char temptemp = ret[y][z - x - 1];
		                ret[y][z - x - 1] = temp;
		                temp = temptemp;

		                int tempX = y;
		                y = z - x - 1;
		                x = tempX;
		            }
		        }
		    }
		    return ret;
	}

	
	

	private static boolean printDiagonal(int i, int j, char[][] m,String wo,Map<String,Point> location) {
		char[] word =  wo.trim().toUpperCase().toCharArray();
		int y = 0;
		
	    while (i >=0 && j< m[0].length ) {
	       System.out.print(m[i][j] + " ");
	        if(m[i][j] == word[y]) {
	        	
	        	y++;
	        	if(y==word.length) {
	        		Point p =  new Point();
	        		for(int x  = i+word.length;x>i;x--) {
						p.addY(x);
					}
					for(int x  = j-word.length;x<j;x++) {
						p.addX(x);
					}
					location.put(wo,p );
					return true	;			
						
				}
	        	
	        }else {y=0;}

	        i--;
	        j++;
	    }
	  //  System.out.println("");
	    return false;
	 
	}


}


class Point{
	List<Integer> x;
	List<Integer> y;
	
	
	public Point() {
		x =  new LinkedList<>();
		y =  new LinkedList<>();
	}
	
	public void addX(int i) {
		x.add(Integer.valueOf(i));
	}
	public void addY(int i) {
		y.add(Integer.valueOf(i));
	}
	
	public List<Integer> getX() {
		return x;
	}
	
	public List<Integer> getY(){
		return y;
	}
}
