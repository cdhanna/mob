/**
 * 
 */
package com.hanna.mobsters.ui.core;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.hanna.mobsters.utilities.MediaLoader;

import net.miginfocom.swing.MigLayout;

/**
 * @author cdhan_000
 *
 */
public class AboutWindow extends JFrame{

	private JPanel content;
	private JTextArea text;
	public AboutWindow(){
		
		this.content = new JPanel(new MigLayout());
		//this.content.setPreferredSize(new Dimension(400,400));
		this.content.add(new JLabel(new ImageIcon(MediaLoader.load("media/splash.png"))));
		text = new JTextArea();
		this.content.add(new JScrollPane(text), "cell 0 1, push, grow");
		
		this.text.setEditable(false);
		this.text.setBackground(content.getBackground());
		this.text.setWrapStyleWord(true);
		
		text.setText(this.getAboutText());
		
		this.setAlwaysOnTop(true);
		this.setIconImage(new ImageIcon("media/icon.png").getImage());
		this.add(this.content);
		this.setLocation(300, 300);
		pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
	}
	
	
	
	private String getAboutText() {
		String s = "";
		s = addLine(s, "Saint is an artificial intelligence system.");
		s = addLine(s, "It is designed to allow actors to behave as");
		s = addLine(s, "real as possible. Saint is being created for");
		s = addLine(s, "Rat Bastard (working title). ");
		s = addLine(s, "");
		s = addLine(s, "Saint is the sole property of William Hanna");
		s = addLine(s, "and Christopher Hanna. All rights are reserved.");
		s = addLine(s, "You may not redistribute this material in any");
		s = addLine(s, "way, shape, or form.");
		s = addLine(s, "");
		s = addLine(s, "");
		s = addLine(s, "Third party libraries used:");
		s = addLine(s, "\tMigLayout");
		s = addLine(s, "\tGoogle Reflections");
		s = addLine(s, "\tKryo");
		s = addLine(s, "\tdom4j");
		s = addLine(s, "\tguava");
		s = addLine(s, "\tslf4j");
		s = addLine(s, "\tjavassist");
		return s;
	}
	
	private String addLine(String text, String line){
		return text + "\n" + line;
	}
	
}
