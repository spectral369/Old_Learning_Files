package random;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.binary.Hex;

public class Anagram {

	public static void main(String[] args) throws NoSuchAlgorithmException {

		String ana = "Str to search";
		Set<Character> an = new HashSet<>();

		char[] anaChar = ana.toCharArray();
		for (char c : anaChar)
			an.add(c);

		anaChar = new char[an.size()];
		Iterator<Character> it = an.iterator();
		int n = 0;
		while (it.hasNext()) {
			anaChar[n] = it.next();
			n++;
		}
		System.out.println(anaChar);
		List<String> sol = new LinkedList<>();
		List<String> words = getDictionary();

		int count = 0;

		for (String s : words) {

			char[] sw = s.toCharArray();
			for (int i = 0; i < sw.length; i++) {

				for (int j = 0; j < anaChar.length; j++) {
					// System.out.println(s+" "+sw[i]+" == "+anaChar[j]);
					if (sw[i] == anaChar[j]) {

						count++;

						if (count == s.length()) {
							// System.out.println("found:"+s);
							sol.add(s);
						}
						break;
					}
				}

			}
			count = 0;

		}
		/*int y = 0;
		for (String h : sol) {
			System.out.println(y + " " + h);
			y++;
		}*/
		 /* List <String> strings = new LinkedList <String>();
	        strings.add("slow,steady");
	        strings.add("blue, white, green");
	        strings.add("whale, shark ");

	        generateCombinations(strings , ",");
		*/
		int contor = 0;
		for(int i =818;i<sol.size();i++) {//818+257
			
			for(int j = 0;j<sol.size();j++) {
			
				for(int x=0;x<sol.size();x++) {
					String magic =  sol.get(i).concat(" ").concat(sol.get(j)).concat(" ").concat(sol.get(x));
					String mds =  MD5(magic);
					//System.out.println(magic+" "+MD5(magic));
					if("md5 str".equals(mds)||
							"md5 str".equals(mds)||
							"md5 str".equals(mds)) {
						System.out.println("found: "+ magic);
					}
				}
				
				
			}
			System.out.println(++contor);
		}

	}
	
	 public static void generateCombinations(List <String> strings, String delimiter){

	        int stringsSize = strings.size();
	        int i=0;
	        String text = "";
	        parseList(text, stringsSize ,i, strings, delimiter);

	    }

	    public static void parseList(String text, int stringsSize, int i, List<String> strings, String delimiter) {
	        String stringStream = strings.get(i);
	        String[] list = stringStream.split(delimiter);
	        if (i==stringsSize-1) {
	            for (String string : list){
	                System.out.println(text + " " + string.replaceAll("\\s+",""));
	            }
	        } else {
	            for (String string : list){
	                String text2 = text + " " + string.replaceAll("\\s+","");
	                int j = i+1;
	                parseList(text2, stringsSize, j, strings, delimiter);
	            }
	        }
	    }
	
	

	public static List<String> getDictionary() {
		List<String> lines = new LinkedList<String>();
		File file = new File("wordlist");//dictionary file
		if (!file.exists())
			throw new IllegalStateException("File doesn't exist");

		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(file));

			String line = null;
			while (null != (line = in.readLine())) {

				lines.add(line);

			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new IllegalStateException("error while processing...");
		}

		return lines;

	}

	public static String MD5(String md5) throws NoSuchAlgorithmException {
		final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.reset();
		messageDigest.update(md5.getBytes(Charset.forName("UTF8")));
		final byte[] resultByte = messageDigest.digest();
		final String result = new String(Hex.encodeHex(resultByte));
		return result;
	}

	public static void count(String test) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();

		char[] chars = test.toCharArray();

		for (int i = 0; i < chars.length; i++) {
			if (!map.containsKey(chars[i])) {
				map.put(chars[i], 0);
			}
			map.put(chars[i], map.get(chars[i]) + 1);
		}

		System.out.println(map.toString());
	}
	 static boolean isAnagram(String a, String b) {
	        a =  a.toUpperCase();
	        b = b.toUpperCase();
	        StringBuilder sb =  new StringBuilder(b);
	       boolean isValid=false;
	        int count = 0;
	      
	        if(a.length() !=  b.length())
	            return false;
	        else {  
	            for(int i = 0;i<a.length();i++){
	                for(int j=0;j<sb.length();j++){
	                  if(a.charAt(i) ==  sb.charAt(j)){
	                       count++;
	                   sb.deleteCharAt(j); 
	                    
	                    if(count==b.length())
	                        isValid=true;
	                      break;
	                }
	                
	            }
	            }
	        }
	      
	        if(isValid)
	              return true;
	        else
	            return false;
	    }

}