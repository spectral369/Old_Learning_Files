package filePlay;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileNameMatcher {

	public static void main(String[] args) {
		int matchedCounter = 0;
		
		File file1 =  new File("path to desired folder");
		File file2 =  new File("path to desired folder");
	
	
		List<File> notFound=  new ArrayList<>();
	File[] files1 = file1.listFiles(new FilenameFilter() {
		
		@Override
		public boolean accept(File dir, String name) {
			// TODO Auto-generated method stub
			  return name.toLowerCase().endsWith(".mp3");
		}
	});

	File[] files2 = file2.listFiles(new FilenameFilter() {
		
		@Override
		public boolean accept(File dir, String name) {
			// TODO Auto-generated method stub
			  return name.toLowerCase().endsWith(".flac");
		}
	});

	
	List<File> matches =  new ArrayList<>();
	List<File> checkControl =  new ArrayList<>();
	
	for(File f1:files1) {
		for(File f2:files2) {
		
			int index1 =  f1.getName().indexOf("-")+1;
			String s = "";
			try {
			s =  f1.getName().substring(index1, f1.getName().lastIndexOf("-"));
			}catch(Exception e) {
			s =  f1.getName().substring(index1, f1.getName().lastIndexOf("."));	
			s=s+"-";
			}
			if(!s.contains("-")) {
				s=s+"-";
			}

			s =  s.toLowerCase().trim();
			
			
			if(s.startsWith("\'"))
				s =  s.replaceFirst("'", "");
			if(s.contains("' ")) {
				int index =  s.lastIndexOf("'");
				String s1 = s.substring(0, index);
				String s2 =  s.substring(index+1,s.length());
				s = s1+s2;
			}

			Pattern p =  Pattern.compile("[\\(|\\[|\\-]|\\b(?:with|lyrics|WITH|LYRICS|Lyrics|With|Lyric|lyric)\\b");
			Matcher matcher = p.matcher(s);
			int index2 = -1;
			 if(matcher.find()){
				
				index2 = matcher.start();
			}else if(s.length()>0  && s.split("\\s+").length == 1) {
				
				index2 =s.length(); 
			}
	
		if(index2!=-1 ) {
			String word  =  s.substring(0, index2);
			/*if(word.contains("hell"))
			System.out.println("word: "+word);*/
			if(word.toLowerCase().contains("with lyrics"))
				word =  word.toLowerCase().substring(0, word.indexOf("with lyrics"));
			
			if(word.toLowerCase().contains("lyrics"))
				word =  word.toLowerCase().substring(0, word.toLowerCase().indexOf("lyrics"));
			if(word.toLowerCase().contains("lyrics"))
				word =  word.toLowerCase().substring(0, word.toLowerCase().indexOf("lyrics"));
			if(word.toLowerCase().contains("lyric"))
				word =  word.toLowerCase().substring(0, word.toLowerCase().indexOf("lyric"));
			//test
			//word = word.replaceAll("[\\-|\\_|\\+]", "");
			
			//test
					
		
	
			if(f2.getName().toLowerCase().trim().contains(word.toLowerCase().trim())) {
			
				String g =  f2.getName().toLowerCase().trim().substring(0,
						f2.getName().toLowerCase().trim().lastIndexOf("."));
				
				String h = word.toLowerCase().trim();
			
			
				int in =  g.indexOf(h);
				
				System.out.println(f2.getName().toLowerCase().trim()+" -> "+word.toLowerCase().trim());
			
				if(f2.getName().toLowerCase().trim().substring(in,
						f2.getName().toLowerCase().trim().lastIndexOf(".")).equals(h)
						||f2.getName().toLowerCase().trim().
						substring(in, f2.getName().toLowerCase().trim().lastIndexOf(".")).startsWith(h)) {
				matches.add(f2);
				checkControl.add(f1);
				System.out.println("Match Song: "+f2.getName());
				matchedCounter++;
				}
			}
			
		}
		
		}
	}
	
	System.out.println("matched songs: "+matchedCounter+" out of "+files1.length);

	/*for(File f:matches) {
		for(File fi:files2) {
			try {
				if(!java.nio.file.Files.isSameFile(Paths.get(f.getPath()), Paths.get(fi.getPath()))) {
					if(!matches.contains(fi)) {
						if(!notFound.contains(fi))
								notFound.add(fi);
					}
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}*/
	
	
	
	for(File f:checkControl) {
		for(File fi:files1) {
			try {
				if(!java.nio.file.Files.isSameFile(Paths.get(f.getPath()), Paths.get(fi.getPath()))) {
					if(!checkControl.contains(fi)) {
						if(!notFound.contains(fi))
								notFound.add(fi);
					}
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

	System.out.println("Remaining: ");
	for(File n: notFound)
		System.out.println(n.getName());
		
/*	for(File f:matches) {
		try {
			Files.move(f, new File("/home/spectral369/Music/Nightwish/"+f.getName()));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	}

}
