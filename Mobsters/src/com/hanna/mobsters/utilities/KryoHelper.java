/**
 * 
 */
package com.hanna.mobsters.utilities;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;

/**
 * @author Chris Hanna
 *
 */
public class KryoHelper {

	private static KryoHelper instance;
	public static KryoHelper getInstance(){
		if (instance == null)
			instance = new KryoHelper();
		return instance;
	}
	
	private final Kryo kryo;
	private final String dataBasePath = "/savedata/";
	private final HashMap<String, Class<?>> pathClasses;
	private KryoHelper(){
		this.kryo = new Kryo();
		this.pathClasses = new HashMap<>();
	}
	
	public <T> T copy(T object){
		return this.kryo.copy(object);
	}
	
	public void save(String filePath, Object object){
		if (isValidFilePath(filePath)){
			
			try {
				String path = this.getFullPathWithExtension(filePath);
				
				Output out = new Output(new FileOutputStream(this.getFullPath(filePath) + ".bin"));
				this.kryo.writeObject(out, object);
				out.close();
				
				
			} catch (FileNotFoundException e) {
				System.err.println("kryo helper could not save file.");
				e.printStackTrace();
			}
			
		}
	}
	
	
	private String getFullPath(String filePath){
		return this.dataBasePath + filePath;
	}
	private String getFullPathWithExtension(String filePath){
		return this.getFullPath(filePath) + ".bin";
	}
	
	public boolean isValidFilePath(String filePath){
		String full = this.getFullPathWithExtension(filePath);
		return (!full.equals(this.dataBasePath)
				&& filePath.length() > 1);
	}
	
}
