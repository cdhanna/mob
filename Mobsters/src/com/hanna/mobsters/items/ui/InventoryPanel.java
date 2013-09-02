/**
 * 
 */
package com.hanna.mobsters.items.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.ActorBin;
import com.hanna.mobsters.items.Inventory;
import com.hanna.mobsters.items.Item;
import com.hanna.mobsters.items.ItemRegistry;
import com.hanna.mobsters.ui.shared.ComboBox;
import com.hanna.mobsters.ui.shared.Panel;
import com.hanna.mobsters.ui.shared.TextField;

/**
 * @author cdhan_000
 *
 */
public class InventoryPanel extends Panel{

	private JLabel title;

	private JTable itemTable;
	private DefaultTableModel itemTableModel;
	private HashMap<Integer, Item<?>> idToItem;

	private JButton addItemButton;
	private TextField addItemNameBox;
	private ComboBox<Class<? extends Item<?>>> addItemTypeBox;
	
	private JButton removeItemButton;
	private JButton giveItemToButton;

	private ComboBox<Actor> giveToBox;

	private ItemDetailsController itemController;

	private Integer lastKnownInventorySize;

	


	@Override
	protected void initComponents() {
		this.title = new JLabel("Inventory");
		this.idToItem = new HashMap<>();
		this.itemTableModel = new DefaultTableModel();
		this.itemTable = new JTable(this.itemTableModel){
			@Override
			public boolean isCellEditable(int x, int y){
				return false;
			}
		};

		this.itemTableModel.addColumn("Id");
		this.itemTableModel.addColumn("ItemType");
		this.itemTableModel.addColumn("ItemName");

		this.itemTable.getColumnModel().getColumn(0).setPreferredWidth(40);
		this.itemTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		this.itemTable.getColumnModel().getColumn(2).setPreferredWidth(500);
		this.addItemButton = new JButton("Create");
		this.addItemButton.setEnabled(false);
		this.addItemNameBox = new TextField("Name");
		this.addItemNameBox.setEditable(false);
		this.addItemTypeBox = new ComboBox<Class<? extends Item<?>>>("Item Type"){
			@Override 
			public String elementToString(Class<? extends Item<?>> clazz){
				return clazz.getSimpleName();
			}
		};
		
		this.addItemNameBox.addKeyListener(new KeyAdapter(){
			@Override
			public void keyReleased(KeyEvent k){
				checkAddButton();
			}
		});
		
		this.addItemTypeBox.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				checkAddButton();
				if (addItemTypeBox.getSelection() == null){
					addItemNameBox.setText("");
					addItemNameBox.setEditable(false);
				} else addItemNameBox.setEditable(true);
			}});
		
		
		this.addItemTypeBox.setElements(ItemRegistry.getInstance().getAllItemTypes());
		this.removeItemButton = new JButton("Remove");
		this.giveItemToButton = new JButton("Give To");
		this.giveToBox = new ComboBox<Actor>("Select an Actor");

		this.giveItemToButton.setEnabled(false);

		this.itemController = new ItemDetailsController(){
			@Override
			public void itemUpdated(Item<?> item){
				refreshItem(item);
			}
		};

		this.giveToBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				giveItemToButton.setEnabled(giveToBox.getSelection() != null
						&& itemTable.getSelectedRow() > -1 && itemTable.getSelectedRow() < itemTable.getRowCount());
			}});

		this.itemTable.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){

				itemController.setForItem(getSelectedItem());

				giveItemToButton.setEnabled(giveToBox.getSelection() != null
						&& itemTable.getSelectedRow() > -1 && itemTable.getSelectedRow() < itemTable.getRowCount());
				removeItemButton.setEnabled(itemTable.getSelectedRow() > -1 && itemTable.getSelectedRow() < itemTable.getRowCount());
			}
		});

		this.removeItemButton.setEnabled(false);


		this.itemController.setForItem(null);

	}

	@Override
	protected void addComponents() {
		
		JPanel top = new JPanel(new MigLayout());
		
		top.add(this.title, "cell 0 0");
		top.setBorder(BorderFactory.createLineBorder(Color.black));
		this.itemTable.setPreferredScrollableViewportSize(new Dimension(10,300));
		JScrollPane tablePane = new JScrollPane(this.itemTable);
		//tablePane.setPreferredSize(new Dimension(tablePane.getPreferredSize().width, 30));
		top.add(tablePane, "cell 0 1, pushx, growx, spanx");
		top.add(this.addItemButton, "cell 0 2");
		top.add(this.addItemTypeBox, "cell 0 2");
		top.add(this.addItemNameBox, "cell 0 2, growx");
		top.add(this.removeItemButton, "cell 1 2, center");
		top.add(this.giveItemToButton, "cell 2 2, right");
		top.add(this.giveToBox, "cell 2 2, right");

	//	this.add(this.itemController.getPanel(), "cell 0 3, pushx, growx, spanx");

		this.add(top, "cell 0 0, growx, pushx");
		this.add(this.itemController.getPanel(), "cell 0 1, grow, push");
		
	}

	@Override
	public void setUpComponents(Object... parameters) {
		if (this.doesInputMatchExpected(parameters)){

			//this.giveToBox.setElements(ActorBin.getInstance().getAllActors());
			this.lastKnownInventorySize = 0;
			refresh((Inventory)parameters[0]);

		}

	}



	@Override
	public Class<?>[] getSetUpParameterTypes() {
		return new Class<?>[]{Inventory.class};
	}

	private void checkAddButton(){
		boolean e = this.addItemNameBox.getText() != null && !this.addItemNameBox.getText().isEmpty() && this.addItemTypeBox.getSelection() != null;
		this.addItemButton.setEnabled(e);
	}
	
	public void addItem(Item<?> item){
		this.idToItem.put(item.getId(), item);
		this.itemTableModel.addRow(new Object[]{item.getId(), item.getClass().getSimpleName(), item.getName()});
	}

	public void removeItem(Item<?> item){

		if (item.equals(this.getSelectedItem()))
			this.itemController.setForItem(null);

		this.idToItem.remove(item.getId());
		int rem = -1;
		for (int row = 0 ; row < this.itemTable.getRowCount() ; row ++){
			if (this.itemTable.getValueAt(row, 0) == item.getId())
				rem = row;
		}
		if (rem != -1)
			this.itemTableModel.removeRow(rem);


	}
	public void removeAllItems(){
		while (this.itemTableModel.getRowCount() > 0){
			this.removeItem(this.idToItem.get(this.itemTable.getValueAt(0, 0)));
		}
	}

	public void refresh(Inventory inventory) {

		Item<?> oldSelected = this.getSelectedItem();
		//if (this.lastKnownInventorySize != inventory.getSize()){
		this.removeAllItems();
		int i = 0;
		for (Item<?> item : inventory.getItems()){
			this.addItem(item);

			if (item.equals(oldSelected)){
				this.itemTable.changeSelection(i, 0, false, false);
				this.itemController.setForItem(item);
			}
			i++;
		}
		this.lastKnownInventorySize = inventory.getSize();
		//}



		this.giveToBox.setElements(ActorBin.getInstance().getAllActors());
	}
	public JButton getGiveToButton(){
		return this.giveItemToButton;
	}
	public ComboBox<Actor> getGiveToBox(){
		return this.giveToBox;
	}
	public JButton getRemoveButton(){
		return this.removeItemButton;
	}
	public JButton getAddItemButton(){
		return this.addItemButton;
	}
	public ComboBox<Class<? extends Item<?>>> getAddItemTypeBox(){
		return this.addItemTypeBox;
	}
	public TextField getAddItemNameBox(){
		return this.addItemNameBox;
	}
	public void refreshItem(Item<?> item){

		for (int row = 0 ; row < this.itemTableModel.getRowCount() ; row ++){
			if (this.itemTableModel.getValueAt(row, 0) == item.getId()){
				this.itemTableModel.setValueAt(item.getName(), row, 2);
			}
			//			Item<?> rowItem = this.idToItem.get(this.itemTableModel.getValueAt(0, row));
			//			if (rowItem.equals(item)){
			//				
			//			}
		}
	}
	
	public void setSelectedItem(Item<?> selected){
		for (int i = 0 ; i < this.itemTableModel.getRowCount() ; i ++){
			Item<?> item = this.idToItem.get(this.itemTableModel.getValueAt(i, 0));

			if (item.equals(selected)){
				this.itemTable.changeSelection(i, 0, false, false);
				this.itemController.setForItem(item);
			}
			
		}
	}
	public Item<?> getSelectedItem(){
		Integer row = this.itemTable.getSelectedRow();
		if (row > -1 && row < this.itemTable.getRowCount()){
			return this.idToItem.get(this.itemTable.getValueAt(row, 0));
		} else return null;
	}

}
