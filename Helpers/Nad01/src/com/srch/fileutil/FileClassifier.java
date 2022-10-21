package com.srch.fileutil;

import java.util.HashMap;
import java.util.Map;

public class FileClassifier {

	private static HashMap<TYPE, String []> fileMap;
	private static String [] IMAGE_FORMATS = {"jpg", "png", "png"};
	private static String [] AUDIO_FORMAT ={"mp3","m4a", "ogg"};
	private static String [] VIDEO_FORMAT ={"mp4", "avi","mkv"};
	private static String [] OTHER_FORMAT ={"pdf", "txt", "js", "java"};

	static{
		fileMap = new HashMap<>();
		fileMap.put(TYPE.IMAGE, IMAGE_FORMATS);
		fileMap.put(TYPE.AUDIO, AUDIO_FORMAT);
		fileMap.put(TYPE.VIDEO, VIDEO_FORMAT);
		fileMap.put(TYPE.OTHER, OTHER_FORMAT);
	}

	public static TYPE getType(Path path){
		TYPE fileTYPE = TYPE.FILE;
		String format = FileUtil.getFormat(path);
		for (Map.Entry entry: fileMap.entrySet()){
			for(String formats : (String[]) entry.getValue()){
				if(formats.equals(format))
					fileTYPE = (TYPE) entry.getKey();
			}
		}
		return fileTYPE;
	}
	public static enum TYPE{
		IMAGE,
		AUDIO,
		VIDEO,
		OTHER,
		FILE
	}
}
