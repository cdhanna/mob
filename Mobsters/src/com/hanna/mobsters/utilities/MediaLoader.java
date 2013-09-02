package com.hanna.mobsters.utilities;

import java.net.URL;

public class MediaLoader {

	public static URL load(String path){
		return MediaLoader.class.getResource("/" + path);
	}
	
}
