/**
 * 
 */
package com.hanna.mobsters.utilities.console;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import com.hanna.mobsters.ui.shared.ObjectList;
import com.hanna.mobsters.ui.shared.Panel;

/**
 * @author cdhan_000
 *
 */
public class ConsolePanel extends Panel{

	private static ConsolePanel instance;
	public static ConsolePanel getInstance(){
		if (instance == null)
			instance = new ConsolePanel();
		return instance;
	}
	
	private JLabel title;

	private List<LinePanel> lines;
	private JPanel linesPanel;
	
	private JScrollPane textAreaPane;

	private ConsolePanel(){
		super();
		
		
		
	}
	
	@Override
	protected void initComponents() {
		this.title = new JLabel("Console: std out");
//		this.textArea = new JTextArea();
//		this.textArea.setEditable(false);
		//this.textArea = new ObjectList<String>();
		this.lines = new ArrayList<>();
		this.linesPanel = new JPanel(new MigLayout("insets 0 0 0 0, flowy"));
		this.textAreaPane = new JScrollPane(this.linesPanel);
		this.textAreaPane.setMinimumSize(new Dimension(0, 100));
		this.textAreaPane.setMaximumSize(new Dimension(Integer.MAX_VALUE,220));
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
	}

	@Override
	protected void addComponents() {
		this.add(this.title, "cell 0 0");
		
		
		
		this.add(this.textAreaPane, "cell 0 1, grow, push, span");
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

//	public ObjectList<LinePanel> getTextArea(){
//		return this.lines;
//	}
	public List<LinePanel> getLinePanels(){
		return this.lines;
	}
	
	public void writeLine(final String line, final String origin, Color color){
		
		Date now =  Calendar.getInstance().getTime();
		String nowStr = DateFormat.getTimeInstance().format(now);
		String stamp = "[" + nowStr + "] ";
		
		LinePanel lp = new LinePanel();
		lp.setUpComponents(stamp + line, origin, color);
		
		
		
		for (int i = 0 ; i < this.lines.size() ; i ++)
			this.linesPanel.remove(this.lines.get(i));
		
		this.linesPanel.add(lp);
		
		for (int i = this.lines.size()-1 ; i > -1 ; i --)
			this.linesPanel.add(this.lines.get(i));

		this.lines.add(lp);
		this.revalidate();

		
	}
	
	public JScrollPane getTextAreaPane() {
		return this.textAreaPane;
	}

	public JPanel getLinePanel() {
		return this.linesPanel;
	}
	
}
