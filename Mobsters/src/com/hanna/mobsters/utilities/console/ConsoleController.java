/**
 * 
 */
package com.hanna.mobsters.utilities.console;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * @author cdhan_000
 *
 */
public class ConsoleController {

	private static ConsoleController instance;
	
	
	
	
	public static ConsoleController getInstance(){
		if (instance == null)
			instance = new ConsoleController();
		return instance;
	}
	
	
	private ConsolePanel panel;

	
	
	private ConsoleController(){
		this.panel = ConsolePanel.getInstance();

//		this.panel.getTextAreaPane().addMouseMotionListener(new MouseMotionListener(){
//
//			@Override
//			public void mouseDragged(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void mouseMoved(MouseEvent e) {
//				setMouseToolTip(e);
//			}});
	}
	
//	private void setMouseToolTip(MouseEvent e){
//		Point p = e.getLocationOnScreen();
//		p.x -= this.panel.getLinePanel().getLocationOnScreen().x;
//		p.y -= this.panel.getLinePanel().getLocationOnScreen().y;
//	//	System.out.println(p.x);
//		Component com = this.panel.getTextAreaPane().getComponentAt(p);
//		
//		while (com != null && com.getClass() != LinePanel.class){
//			p.x -= com.getLocationOnScreen().x;
//			p.y -= com.getLocationOnScreen().y;
//			com = com.getComponentAt(p);
//		}
//		if (com != null)
//		System.out.println(com.getClass().getSimpleName());
//		//if (com != null && com.getClass() != JPanel.class)
//		//	System.out.println(com.getClass().getSimpleName());
//			
//			
//	}
	
	public String writeLine(String line, String origin, Color color){
		//String beforeText = this.panel.getTextArea().getText();

		
		//this.panel.getTextArea().insertElement(line, 0, origin);
	//	LinePanel lp = new LinePanel();
	//	lp.setUpComponents(line, origin, color);
	//	this.panel.getTextArea().insertElement(lp, 0);
		
		this.panel.writeLine(line, origin, color);
		
		//this.panel.getTextArea().setSelectionStart(beforeText.length());
		//this.panel.getTextArea().setText(line + "\n" + this.panel.getTextArea().getText());
		//this.panel.getTextArea().setText(this.panel.getTextArea().getText() + "\n" + line);
		//this.panel.getTextArea().setSelectionEnd(this.panel.getTextArea().getText().length());
		
		
		//this.panel.getTextArea().setSelectionStart(0);
		//this.panel.getTextArea().setSelectionEnd(4);
		//this.panel.getTextArea().setSelectionColor(color);
		//this.panel.getTextArea().setSelectedTextColor(color);
		
		//this.panel.getTextAreaPane().getVerticalScrollBar().setValue(1);
		return line;
	}
	
	public ConsolePanel getPanel(){
		return this.panel;
	}
	
}
