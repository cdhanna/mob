/**
 * 
 */
package com.hanna.mobsters.utilities.inputbox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import com.hanna.mobsters.ui.shared.TextField;
import com.hanna.mobsters.utilities.parsers.ParseRegistry;

/**
 * @author cdhan_000
 *
 */
public class DefaultInputBox<T> extends InputBox<T>{

	private TextField field;

	public DefaultInputBox(Class<T> type, T instance) {
		super(type, instance);
		this.setLayout(new GridLayout());
		this.field.setDefaultText(this.getDefaultText(type));
		//this.setLayout(new MigLayout("insets 0 1 1 1"));
	//	this.setMaximumSize(new Dimension(200, this.field.getPreferredSize().height));
		//this.setBorder(BorderFactory.createLineBorder(Color.blue));
	}

	@Override
	protected void initComponents() {
		this.field = new TextField("Enter a value");
		//this.field.setMinimumSize(new Dimension(100,1));
		this.field.addKeyListener(new KeyAdapter(){
			@Override
			public void keyReleased(KeyEvent k){
				setValue();
				//setContentForValue(instance);
			}
		});

	}

	@Override
	protected void addComponents() {
		//this.add(this.field, "top, pushx, growx");
		this.add(this.field);
	}

	@Override
	public void setUpComponents(Object... parameters) {
		// TODO Auto-generated method stub

	}

	@Override
	public Class<?>[] getSetUpParameterTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setContentForValue(T instance) {
		if (instance != null){
			this.instance = instance;
			this.field.setText(this.instance.toString());
		}
	}

	@Override
	public void setValue() {
		this.instance = ParseRegistry.getInstance().parse(this.field.getText(), this.type);
		//System.out.println("instance = " + instance + " from " + this.field.getText() + " casted to " + this.type);
		this.fireValueChanged();
	}



}
