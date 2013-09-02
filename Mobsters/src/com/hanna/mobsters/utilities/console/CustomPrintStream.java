/**
 * 
 */
package com.hanna.mobsters.utilities.console;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import com.hanna.mobsters.utilities.MediaLoader;

/**
 * @author cdhan_000
 *
 */
public class CustomPrintStream extends PrintStream{

	private static final PrintStream stdOut = System.out;
	private static final PrintStream stdErr = System.err;
	private static final String logPath = "stdout.txt";
	private static final String LogPathErr = "stderr.txt";
	
	public static void initialize(String stdOutPath, String stdErrPath){
		CustomPrintStream cpsOUT;
		CustomPrintStream cpsERR;
		try {
			
			cpsOUT = new CustomPrintStream(stdOutPath,stdOut);
			System.setOut(cpsOUT);
			
			cpsERR = new CustomPrintStream(stdErrPath,stdErr);
			System.setErr(cpsERR);
			
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
		
	}
	public static void initialize(){
		initialize(logPath, LogPathErr);
	}

	private PrintStream replacing;
	public CustomPrintStream(String filePath,PrintStream replacing) throws FileNotFoundException {
		//super(MediaLoader.load(filePath).getPath());
		//super(System.out);
		super(filePath);
		
		this.replacing = replacing;

	}

	public static void main(String[] args){
		
		CustomPrintStream.initialize();
		

		
	}
	
	//TODO override all the methods. But thats boring, and I don't want to do it :(
	
	@Override
	public void println(int i){
		super.println(log(i));
	}
	
	@Override
	public void println(double i){
		super.println(log(i));
	}
	
	@Override
	public void println(long i){
		super.println(log(i));
	}
	
	@Override
	public void println(boolean i){
		super.println(log(i));
	}
	
	@Override
	public void println(float i){
		super.println(log(i));
	}
	
	@Override
	public void println(char i){
		super.println(log(i));
	}
	
	@Override
	public void println(char[] i){
		super.println(log(i));
	}
	
	@Override
	public void println(Object o){
		super.println(log(o));
	}
	
	@Override
	public void println(String s){
		super.println(log(s));
	}
	
	private String getOrigin(){
		StackTraceElement e = Thread.currentThread().getStackTrace()[4];
		return " " + e.getClassName() + "." + e.getMethodName() + " at " + e.getLineNumber() + " ";
	}
	
	private String log(Object o){
		
		
		
		Date now =  Calendar.getInstance().getTime();
		String nowStr = DateFormat.getTimeInstance().format(now);
		String stamp = "[" + nowStr + "]";
		String ostr = "null"; if (o != null) ostr = o.toString();
		if (replacing.equals(stdOut)){
			System.setOut(this.replacing);
			System.out.println(o);
			System.setOut(this);
			
			String result = "OUT:    " + stamp + "   " + ostr;
			ConsoleController.getInstance().writeLine(ostr,getOrigin(), Color.blue);
			return result;
		}
		else if (replacing.equals(stdErr)){
			System.setErr(this.replacing);
			System.err.println(o);
			System.setErr(this);
			String result ="ERR:    " + stamp + "   " + ostr;
			ConsoleController.getInstance().writeLine(ostr,getOrigin(), Color.red);
			return result;
		}
		
		
		
		
		return "PrintStream invalid:\t" + o;
	}

}
