package filePlay;

import java.io.File;
import java.io.FilenameFilter;

public class FileRename {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file2 =  new File("path to desired folder");
		File[] files2 = file2.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				  return name.toLowerCase().endsWith(".flac");
			}
		});
		 for (int i = 0; i < files2.length; i++) {

	            if (files2[i].isFile()) {

	                File f = files2[i];
	                String name =  files2[i].getName().replace("_", " ");
	                System.out.println(name);
	                f.renameTo(new File("path to desired folder"+name));
	            }
	        }

	}

}
