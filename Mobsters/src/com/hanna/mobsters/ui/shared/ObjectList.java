/**
 * 
 */
package com.hanna.mobsters.ui.shared;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JToolTip;

/**
 * @author Chris Hanna
 *
 */
public class ObjectList<T> extends JList<T>{

	private DefaultListModel<T> model;
	private HashMap<T, String> elementToDescription;


	public ObjectList(){
		super();
		this.model = new DefaultListModel<T>();
		this.elementToDescription = new HashMap<>();
		this.setModel(this.model);

		this.addMouseMotionListener(new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseMoved(MouseEvent e) {

				int fontSize = getFont().getSize();
				Point l = e.getLocationOnScreen();
				l.x -= getLocationOnScreen().x;
				l.y -= getLocationOnScreen().y;

				int row = l.y / fontSize;

				if (row < model.getSize()){
					T selected = model.getElementAt(row);
					if (selected != null)
						setToolTipText(selected.toString() + " :: " + elementToDescription.get(selected));
					else setToolTipText("null");
				} else setToolTipText("");

			}});

	}

	public void insertElement(T object, int index){
		this.insertElement(object, index, object.toString());
	}
	

	public void addElement(T object){
		this.addElement(object, object.toString());
	}

	public void removeElement(T object){
		this.model.removeElement(object);
	}

	public void removeAllElements(){
		this.model.removeAllElements();

	}
	
	public void addElement(T object, String description){
		this.model.addElement(object);
		this.elementToDescription.put(object, description);
	}
	public void insertElement(T object, int index, String description){
		this.model.insertElementAt(object, index);
		this.elementToDescription.put(object, description);
	}
	

}
