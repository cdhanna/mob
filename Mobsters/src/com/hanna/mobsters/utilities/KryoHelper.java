/**
 * 
 */
package com.hanna.mobsters.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.ActorBin;
import com.hanna.mobsters.actors.properties.Location;
import com.hanna.mobsters.actors.properties.Property;
import com.hanna.mobsters.actors.properties.impl.LocationProperty;

/**
 * @author Chris Hanna
 *
 */
public class KryoHelper {

	public static void main(String[] args){
		KryoHelper k = getInstance();
//		ActorBin ab = ActorBin.getInstance();
//		ab.createActor("Joe Peshi");
//		k.saveSimple("t.bin", ab);
//		ActorBin loadedab = k.loadSimple("t.bin", ActorBin.class);
		
		HashMap<Class<? extends Property<?>>, Property<?>> propertyTable = new HashMap<>();
		LocationProperty lp = new LocationProperty();
		lp.setValue(Location.MOB_HOME);
		propertyTable.put(LocationProperty.class, lp);
		k.saveSimple("h.bin", propertyTable);
		HashMap<Class<? extends Property<?>>, Property<?>> hl = k.loadSimple("h.bin", HashMap.class);
		System.out.println(hl.get(LocationProperty.class).getValue());
		
	}
	
	
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
	
	public void saveSimple(String filePath, Object object){
		try {
			Output out = new Output(new FileOutputStream(filePath));
			this.kryo.writeObject(out, object);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public <T> T loadSimple(String filePath, Class<T> clazz){
		try {
			Input in = new Input(new FileInputStream(filePath));
			T t = this.kryo.readObject(in, clazz);
			in.close();
			return t;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
