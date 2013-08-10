/**
 * 
 */
package com.hanna.mobsters.ui.shared;

import javax.swing.JTextField;

/**
 * @author Chris Hanna
 *
 */
public class TextField extends JTextField{

	public TextField(String defaultText){
		super();
	}
	
	@Override
	public void setEnabled(boolean enabled){
		System.out.println(enabled);
		super.setEnabled(enabled);
	}
	
}
