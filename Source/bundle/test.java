import com.filecon.FileCon;
import com.filecon.FileCon.FileWatcher;
import com.filecon.FileCon.FileSearch;
import com.filecon.FileCon.TextMatcher;
import com.filecon.FileCon.FileUtil;

import java.io.IOException;
import java.lang.InterruptedException;

public class Test{
	public static void main(String[] args) throws IOException, InterruptedException {

		String fp = "./com/filecon/Writer.class";
		
		// System.out.println(FileUtil.readFile());

		FileCon.FCFile f = new FileCon.FCFile(fp);
		FileCon.UPath p = new FileCon.UPath(fp);

		System.out.println(p);
		System.out.println(f.sizeInKB());

	}
}