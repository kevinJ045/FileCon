package test;
import com.srch.fileutil.Path;
import org.junit.Assert;
import org.junit.Test;

import com.srch.fileutil.*;

public class Project {

	@Test
	public void Test(){
		/*
		String path = "/home/nad/Documents/p_nad/adf";
		String path2 = "C:/users/nad/pro/sdf";
		Path mPath = Path.form(path);
		Assert.assertNotNull(mPath);
		Assert.assertTrue(mPath.isPath());
		System.out.println(mPath.getPathAt(5));
		System.out.println(mPath.toString());

		Path [] p = new Path[3];
		p[0]=Path.form("/home/nad/p1");
		p[1]=Path.form("/home/nad/p2");
		p[2]=Path.form("/home/nad/p3");
		FileUtil.createDirs(p);

		Path [] p = new Path[3];
		p[0]=Path.form("/home/nad/p1");
		p[1]=Path.form("/home/nad/p2");
		p[2]=Path.form("/home/nad/p3");
		FileUtil.createDirs(p);
		FileUtil.deleteFiles(p);


		SRCHFileWriter.write(new Writer() {
			@Override
			public Path path() {
				return mPath;
			}

			@Override
			public String data() {
				return "This is a text data\nWriting to "+mPath.toString();
			}
		});

		 */

		Path mPath = Path.form("/home/nad/Desktop/srch2.dfa.ddfasd.mp3");
		System.out.println(FileClassifier.getType(mPath));


	}
}
