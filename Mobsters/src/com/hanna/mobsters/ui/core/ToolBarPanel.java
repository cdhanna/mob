/**
 * 
 */
package com.hanna.mobsters.ui.core;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.hanna.mobsters.ui.shared.Panel;

/**
 * @author Chris Hanna
 *
 */
public class ToolBarPanel extends Panel{

	private JLabel title;
	private JLabel focusPanelSignerature;
	
	private JPanel focusPanel;
	private JScrollPane focusPanelPane;
	
	private JButton runActionButton;
	
	@Override
	protected void initComponents() {
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setMinimumSize(new Dimension(350, 1));
		
		
		this.title = new JLabel("Toolbar");
		this.focusPanelSignerature = new JLabel("Nothing selected");
		
		this.runActionButton = new JButton("Run All Once");
	}

	@Override
	protected void addComponents() {
		this.add(this.title, "cell 0 0");
		this.add(this.runActionButton, "cell 0 0,spanx, right");
		this.add(this.focusPanelSignerature, "cell 0 1, spanx");
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

	public void setFocusPanel(JPanel focusPanel, String signerature) {
		if (this.contains(this.focusPanelPane))
			this.remove(this.focusPanelPane);
		if (this.focusPanelPane!=null)
			this.focusPanelPane.removeAll();
		this.focusPanel = focusPanel;
		this.focusPanelPane = new JScrollPane(this.focusPanel);
		this.add(this.focusPanelPane, "cell 0 2, pushx, growx, pushy, growy");

		this.focusPanelSignerature.setText(signerature);
		
		this.revalidate();
		this.repaint();
	}
	
	public void clearFocusPanel(){
		this.focusPanelSignerature.setText("Nothing Selected");
		if (this.contains(this.focusPanelPane))
			this.remove(this.focusPanelPane);
		if (this.focusPanelPane!=null)
			this.focusPanelPane.removeAll();
		this.revalidate();
		this.repaint();
	}
	
	public JButton getRunOnceButton(){
		return this.runActionButton;
	}

}
