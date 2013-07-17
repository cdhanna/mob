/**
 * 
 */
package com.hanna.mobsters.actions.ui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.hanna.mobsters.ui.shared.Panel;
import com.hanna.mobsters.ui.shared.TextField;

/**
 * @author Chris Hanna
 *
 */
public class ValuesPanel extends Panel{
	
	private List<Value> values;
	private List<JLabel> valueTypes;
	private List<TextField> valueStrings;
	
	
	@Override
	protected void initComponents() {
		this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		
		this.values = new ArrayList<>();
		this.valueTypes = new ArrayList<>();
		this.valueStrings = new ArrayList<>();
	}

	@Override
	protected void addComponents() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUpComponents(Object... parameters) {
		

	//	if (this.doesInputMatchExpected(parameters)){
			this.removeAll();
			this.values.clear();
			this.valueStrings.clear();
			this.valueTypes.clear();
			//Class[] classes = (Class[])parameters[0];
			for (Object c : parameters)
				this.addValue((Class<?>)c);
			this.finalizeValues();
			
			
			
		//} else System.err.println("could not create values panel");
	}

	@Override
	public Class<?>[] getSetUpParameterTypes() {
		return new Class<?>[]{Class[].class};
	}

	private void addValue(Class<?> type){
		this.values.add(new Value(type));
		this.valueTypes.add(new JLabel(type.getSimpleName()));
		this.valueStrings.add(new TextField("null"));
	}
	
	private void finalizeValues(){
		for (int i = 0 ; i < this.values.size() ; i ++){
			this.add(this.valueTypes.get(i), "cell 0 " + i + ", growx, pushx");
			this.add(this.valueStrings.get(i), "cell 1 " + i + ", growx, pushx");
		}
		this.revalidate();
		this.repaint();
	}
	
	protected Object[] getValues(){
		Object[] vals = new Object[this.values.size()];
		for (int i = 0 ; i < vals.length ; i ++){
			this.values.get(i).valueString = this.valueStrings.get(i).getText();
			vals[i] = this.values.get(i).getValue();
		}
		return vals;
	}
	
	private class Value{
		public Class<?> type;
		public String valueString;
		public Value(Class<?> type){
			this.type = type;
		}
		public Object getValue(){
			Object val = null;
			
			if (this.type == Integer.class)
				try { val = Integer.parseInt(this.valueString);} catch (Exception e){}
			else if (this.type == Double.class)
				try { val = Double.parseDouble(this.valueString);} catch (Exception e){}
			else if (this.type == String.class)
				return this.valueString;
//			try{val = Integer.parseInt(this.valueString);}
//			catch (Exception e){
//				try{val = Double.parseDouble(this.valueString);}
//				catch (Exception e2){
//					return valueString;
//				}
//			}
			
			return val;
		}
	}
//	private class ValuePanel extends JPanel{
//		
//		private JLabel typeLabel;
//		private TextField valueField;
//		
//		private Class type;
//		
//		public ValuePanel(Class<?> type){
//			this.type = type;
//			
//		}
//		
//	}
}
