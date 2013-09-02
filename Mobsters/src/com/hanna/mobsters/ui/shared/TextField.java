/**
 * 
 */
package com.hanna.mobsters.ui.shared;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import javax.swing.JTextField;

/**
 * @author Chris Hanna
 *
 */
public class TextField extends JTextField{

	private String defaultText;
	private Color validColor, invalidColor;
	

	
	public TextField(final String defaultText){
		super();
		this.defaultText = defaultText;
		this.validColor = Color.black;
		this.invalidColor = Color.gray;
		
		this.setText(this.defaultText);
		//this.transferFocus();
		this.addKeyListener(new KeyAdapter(){
			@Override
			public void keyReleased(KeyEvent k){
				setText(getText());
				if (k.getKeyCode() == KeyEvent.VK_ENTER){
					transferFocus();
				}
			}
		});
		
		this.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent arg0) {
				onGain();
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				onLoss();
			}});
	}
	
	private void onGain(){
		if (getText().equals(defaultText) && isEnabled() && isEditable()){
			setText("");
		}
	}
	private void onLoss(){
		if (getText().isEmpty()){
			setText(defaultText);
		}
	}
	
	@Override
	public void setText(String text){
		if (text == null || text.isEmpty()){
			super.setText(defaultText);
		}
		
		if (text.equals(this.defaultText)){
			this.setForeground(this.invalidColor);
		} else {
			this.setForeground(this.validColor);
		}
		super.setText(text);
	}
	
	@Override
	public void setEnabled(boolean enabled){
		super.setEnabled(enabled);
	}
	
	public String getDefaultText(){
		return this.defaultText;
	}
	public void setDefaultText(String defaultText){
		this.defaultText = defaultText;
		super.setText(this.defaultText);
		//onGain();
	}
	
}
