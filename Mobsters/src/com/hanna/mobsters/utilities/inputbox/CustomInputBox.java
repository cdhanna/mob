/**
 * 
 */
package com.hanna.mobsters.utilities.inputbox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.BorderUIResource.BevelBorderUIResource;

import net.miginfocom.swing.MigLayout;

import com.hanna.mobsters.actors.properties.Location;
import com.hanna.mobsters.ui.shared.Panel;
import com.hanna.mobsters.ui.shared.TextField;

/**
 * @author cdhan_000
 *
 */
public abstract class CustomInputBox<T> extends InputBox<T>{

//	public static void main(String[] args){
//		new CustomInputBox(Location.class);
//	}
	
	private JPopupMenu popup;
	private JPanel popUpContent;
	private JButton popUpAcceptButton;
	
	private TextField field;
	
	public CustomInputBox(Class<T> type, T object){
		super(type, object);
		this.setLayout(new GridLayout());
		this.field.setDefaultText(this.getDefaultText(type));
		if (instance != null)
			this.field.setText(this.instance.toString());
		this.popup.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), type.getSimpleName(),
				TitledBorder.LEFT, TitledBorder.BELOW_TOP,
				new Font("arial", Font.BOLD, 12), Color.black));
		
	}
	
	@Override
	protected String getDefaultText(Class<T> type){
		String base = "Select ";
		
		String typeName = type.getSimpleName();
		
		if ("aeiou".contains((""+typeName.charAt(0)).toLowerCase()))
			base += "an ";
		else base += "a ";
		base += typeName;
		
		return base;
	}
	
	@Override
	protected void initComponents() {
		
		final CustomInputBox cib = this;
		
		this.popup = new JPopupMenu();
		
		this.popup.setLayout(new MigLayout());
		this.popUpContent = new JPanel(new MigLayout());
		this.popUpContent.setMinimumSize(new Dimension(200,10));
		this.popUpContent.setBorder(BorderFactory.createLineBorder(Color.black));
		this.popup.setLightWeightPopupEnabled(false);
		this.popUpAcceptButton = new JButton("Accept");
		
		this.field = new TextField("Select a value");
	//	this.field.setMinimumSize(new Dimension(100,1));
		
		this.field.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent m){
				//setContentForValue(instance);
				
				popup.show(cib,
						cib.getWidth()/2 - (popup.getPreferredSize().width/2),
						-popup.getPreferredSize().height);
			}
		});
		
		this.popUpAcceptButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onPopupClose();
				popup.setVisible(false);
			}});
		

		this.popup.addPopupMenuListener(new PopupMenuListener(){
			@Override
			public void popupMenuCanceled(PopupMenuEvent arg0) {
				
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				
				onPopupClose();
			}

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
				setContentForValue(instance);
			}});
		
		this.initContent(this.popUpContent);
		this.addContent(this.popUpContent);
	}

	@Override
	protected void addComponents() {
		
		this.popup.add(this.popUpContent, "cell 0 0, pushx, growx");
		this.popup.add(this.popUpAcceptButton, "cell 0 1, pushx, growx");
		
		//this.add(this.field, "pushx, growx");
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

	private void onPopupClose(){
		setValue();
		if (instance == null){
			field.setText("null");
		}
		else field.setText(instance.toString());
		cleanUp();
	}
	public abstract void cleanUp();
	public abstract void initContent(JPanel panel);
	public abstract void addContent(JPanel panel);

	public abstract Class<T> getType();
}
