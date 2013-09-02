package com.hanna.mobsters.utilities.console;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;

import com.hanna.mobsters.ui.shared.Panel;

public class LinePanel extends Panel{

	private JTextArea textArea;
	
	@Override
	protected void initComponents() {
		this.setLayout(new MigLayout("insets 0 0 0 0"));
		this.textArea = new JTextArea();
		this.textArea.setEditable(false);
		
//		this.setOpaque(false);
//		this.setBackground(new Color(0,0,0,0));
		this.textArea.setOpaque(false);
		this.textArea.setBackground(new Color(0,0,0,0));
	}

	@Override
	protected void addComponents() {
		this.add(this.textArea, "push, grow");
	}

	@Override
	public void setUpComponents(Object... parameters) {
		if (this.doesInputMatchExpected(parameters)){
			String line = (String)parameters[0];
			String origin = (String)parameters[1];
			Color color = (Color)parameters[2];
			
			this.textArea.setText(line);
			this.textArea.setForeground(color);
			
			this.textArea.setToolTipText(origin);
			
			this.repaint();
			
		}
	}

	@Override
	public Class<?>[] getSetUpParameterTypes() {
		return new Class<?>[]{String.class, String.class, Color.class};
	}

}
