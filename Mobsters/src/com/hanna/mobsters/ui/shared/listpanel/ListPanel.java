/**
 * 
 */
package com.hanna.mobsters.ui.shared.listpanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.ui.shared.Panel;
import com.hanna.mobsters.ui.shared.TextField;

/**
 * @author cdhan_000
 *
 */
public class ListPanel<T> extends Panel{

	private JLabel title, searchLabel;
	private JList<T> list;
	private TextField searchBox;
	private JScrollPane listPanel;
	private DefaultListModel<T> model, filterModel;
	
	public ListPanel(){
		super();
	}
	
	@Override
	protected void initComponents() {
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.list = new JList<T>();
		this.title = new JLabel("List");
		this.searchLabel = new JLabel("Search");
		this.searchBox = new TextField("search");
		this.listPanel = new JScrollPane(this.list);
		this.model = new DefaultListModel<T>();
		this.filterModel = new DefaultListModel<T>();
		this.list.setModel(this.filterModel);
		this.setMinimumSize(new Dimension(200,100));
		
		
		this.searchBox.addKeyListener(new KeyAdapter(){
			@Override
			public void keyReleased(KeyEvent k){
				filterFor();
			}
		});


	}


	
	@Override
	protected void addComponents() {
		this.add(this.title, "cell 0 0");
		this.add(this.searchLabel, "cell 0 1");
		this.add(this.searchBox, "cell 0 1, right, pushx, growx");
		this.add(this.listPanel, "cell 0 2, pushx, growx");
	}

	@Override
	public void setUpComponents(Object... parameters) {
		if (this.doesInputMatchExpected(parameters)){
			String name = (String) parameters[0];
			this.title.setText(name);
			this.searchBox.setText("");
			
			List<T> elements = (List<T>) parameters[1];
			this.model.removeAllElements();
			for (T element : elements){
				this.model.addElement(element);
				this.filterModel.addElement(element);
			}
			this.filterFor();
			
		}
	}

	public void addElement(T element){
		this.model.addElement(element);
		this.filterModel.addElement(element);
		this.filterFor();
	}
	public void removeElement(T element){
		this.model.removeElement(element);
		this.model.removeElement(element);
		this.filterFor();
	}
	
	private void filterFor(){
		String text = this.searchBox.getText();
		this.filterModel.removeAllElements();
		
		for (int i = 0 ; i < this.model.getSize() ; i ++){
			if (model.getElementAt(i).toString().toLowerCase().startsWith(text.toLowerCase())){
				this.filterModel.addElement(model.getElementAt(i));
			}
		}
	}
	
	@Override
	public Class<?>[] getSetUpParameterTypes() {
		return new Class<?>[]{String.class, ArrayList.class};
	}

	public T getSelection(){
		return this.list.getSelectedValue();
	}

	public void setSelection(Actor instance) {
		if (instance != null)
			this.list.setSelectedValue(instance, false);
	}
	
	public JList<T> getList(){
		return this.list;
	}
	
}
