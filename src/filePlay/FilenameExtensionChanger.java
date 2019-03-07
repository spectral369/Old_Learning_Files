package filePlay;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilenameExtensionChanger {

	public static void main(String[] args) throws IOException {
		
		List<File> file1 =  new ArrayList<File>();
		
		file1 = Files.walk(Paths.get("path to file"))
				.filter(Files::isRegularFile)
				.filter(p -> p.toString().endsWith(".aac"))
				.map(Path::toFile)
				.collect(Collectors.toList());


	
	for(File s :file1) {
		System.out.println(s.getName());
		boolean isSucc =  s.renameTo(new File(s.getParentFile(), s.getName().substring(0, s.getName().lastIndexOf(".")+1)+"mp3"));
		System.out.println(" "+isSucc);
	}
	System.out.println(file1.size());
	
	
	
	
	
	
	}

}
