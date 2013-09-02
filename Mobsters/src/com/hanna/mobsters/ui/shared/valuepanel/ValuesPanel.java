/**
 * 
 */
package com.hanna.mobsters.ui.shared.valuepanel;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.hanna.mobsters.actions.core.ActionRegistry;
import com.hanna.mobsters.actions.core.ActionRegistry.ActionInfo;
import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.ActorBin;
import com.hanna.mobsters.actors.properties.Location;
import com.hanna.mobsters.ui.shared.Panel;
import com.hanna.mobsters.ui.shared.TextField;
import com.hanna.mobsters.utilities.inputbox.DefaultInputBox;
import com.hanna.mobsters.utilities.inputbox.InputBox;
import com.hanna.mobsters.utilities.inputbox.InputBoxRegistry;
import com.hanna.mobsters.utilities.inputbox.InputBox.ValueChangedListener;
import com.hanna.mobsters.utilities.inputbox.impl.LocationInputBox;
import com.hanna.mobsters.utilities.parsers.ParseRegistry;

/**
 * @author Chris Hanna
 *
 */
public class ValuesPanel extends Panel{

	private List<Value> values;
	private List<JLabel> valueTypes;
	private List<InputBox> valueStrings;
	private HashMap<Object, InputBox> idToTextField;
	private HashMap<Object, Value> idToValue;

	@Override
	protected void initComponents() {
		this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		this.values = new ArrayList<>();
		this.valueTypes = new ArrayList<>();
		this.valueStrings = new ArrayList<>();
		this.idToTextField = new HashMap<>();
		this.idToValue = new HashMap<>();
	}

	@Override
	protected void addComponents() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUpComponents(Object... parameters) {

		if (parameters.length == 1 && parameters[0] instanceof ValuePanelContent){
			//if (this.doesInputMatchExpected(parameters)){
			this.removeAll();
			this.values.clear();
			this.valueStrings.clear();
			this.valueTypes.clear();
			this.idToTextField.clear();
			this.idToValue.clear();
			ValuePanelContent content = (ValuePanelContent)parameters[0];
			Class<?>[] classes = content.getTypes();
			String[] descs = content.getTypeDescriptions();
			Object[] IDs = content.getItemIDs();
			Object[] actualObjs = content.getActualObjects();
			if (IDs == null)
				IDs = new Object[descs.length];
			if (classes.length != descs.length || classes.length != IDs.length)
			{
				System.err.println("could not create values panel. Wrong number of annotations");
				return;
			}
			//			if (info.getParameters().length != parameters.length-1){
			//				System.err.println("wrong number of arguements. Annotations count is off");
			//				return;
			//			}
			for (int i = 0 ; i < classes.length ; i ++)
				this.addValue(descs[i], classes[i], IDs[i], actualObjs[i]);
			this.finalizeValues();



		} else System.err.println("could not create values panel");
	}

	@Override
	public Class<?>[] getSetUpParameterTypes() {
		//return new Class<?>[]{ActionInfo.class, Class[].class};
		return new Class<?>[]{ValuePanelContent.class};
	}

	public void setValues(Object[] vals){
		if (vals.length == this.values.size()){

			for (int i = 0 ; i < vals.length ; i ++){
				if (vals[i] != null && vals[i].getClass() == this.values.get(i).type){
					this.values.get(i).valueString = vals[i].toString();
					//this.valueStrings.get(i).setText(vals[i].toString());
					this.valueStrings.get(i).setContentForValue(vals[i]);
				}
			}
			this.repaint();
		}
	}

	public void setValue(Object id, Object value){
		//this.idToTextField.get(id).setText(value.toString());
		this.idToTextField.get(id).setContentForValue(value);
		this.valueChangedAction(this.idToValue.get(id));
	}

	private void addValue(String desc, Class<?> type, Object id, Object obj){
		final Value val = new Value(type, id);
		this.values.add(val);
		this.valueTypes.add(new JLabel(type.getSimpleName() + " " + desc));
		//final TextField textField = new TextField("null");

		final InputBox inputBox = InputBoxRegistry.getInstance().getInputBoxForUnsafe(type, obj);
		//		if (type == Location.class)
		//			inputBox = new LocationInputBox((Location) obj);
		//		else inputBox = new DefaultInputBox(type, obj);



		inputBox.addValueChangedListener(new ValueChangedListener(){
			@Override
			public void valueChanged(Object instance) {
				if (instance != null){
					val.valueString = inputBox.getValue().toString();
					valueChangedAction(val);
				}
			}});
		//		textField.addKeyListener(new KeyAdapter(){
		//			@Override
		//			public void keyReleased(KeyEvent k){
		//				val.valueString = textField.getText();
		//				valueChangedAction(val);
		//			}
		//		});
		//this.idToTextField.put(id, textField);
		this.idToTextField.put(id, inputBox);
		this.idToValue.put(id,  val);
		//textField.setEditable(this.isEnabled());
		inputBox.setEnabled(this.isEnabled());
		this.valueStrings.add(inputBox);
	}

	private void finalizeValues(){

		for (int i = 0 ; i < this.values.size() ; i ++){

			this.add(this.valueTypes.get(i), "cell 0 " + i );
			this.add(this.valueStrings.get(i), "cell 1 " + i + ", growx, pushx, spanx");
		}
		this.revalidate();
		this.repaint();
	}

	public boolean hasAllValues(){
		boolean n = true;
		Object[] vals = this.getValues();
		for (Object val : vals)
			n = n && (val!=null);
		return n;
	}

	public List<InputBox> getTextFields(){
		return this.valueStrings;
	}

	public List<Value> getVals(){
		return this.values;
	}

	public Object[] getValues(){
		Object[] vals = new Object[this.values.size()];
		for (int i = 0 ; i < vals.length ; i ++){
			//this.values.get(i).valueString = this.valueStrings.get(i).getText();
			if (this.valueStrings.get(i).getValue() != null)
				this.values.get(i).valueString = this.valueStrings.get(i).getValue().toString();
			else this.values.get(i).valueString = "null";
			vals[i] = this.values.get(i).getValue();
		}
		return vals;
	}

	protected void valueChangedAction(Value value){

	}

	@Override
	public void setEnabled(boolean enabled){
		super.setEnabled(enabled);
		for (InputBox t : this.getTextFields())
			t.setEnabled(enabled);
	}

	public interface ValuePanelContent{

		/**
		 * @return a list of names for the objects in the value panel
		 */
		public Object[] getItemIDs();

		/**
		 * @return a list of types for the objects in the value panel
		 */
		public Class<?>[] getTypes();

		/**
		 * @return a list of descriptions for the objects in the value panel
		 */
		public String[] getTypeDescriptions();

		/**
		 * @return The actual objects you want to fill these spaces
		 */
		public Object[] getActualObjects();
	}


	public class Value{
		private Class<?> type;
		private String valueString;
		private Object id;
		public Value(Class<?> type, Object ID){
			this.type = type;
			this.id = ID;
		}

		public Class<?> getType(){
			return this.type;
		}

		public Object getID(){
			return this.id;
		}

		public Object getValue(){
			Object val = null;

			val = ParseRegistry.getInstance().parse(this.valueString, this.getType());

			//			if (this.type == Integer.class)
			//				try { val = Integer.parseInt(this.valueString);} catch (Exception e){}
			//			else if (this.type == Double.class)
			//				try { val = Double.parseDouble(this.valueString);} catch (Exception e){}
			//			else if (this.type == Actor.class){
			//				if (ActorBin.getInstance().lookUpActor(this.valueString) != null)
			//					val = ActorBin.getInstance().lookUpActor(this.valueString);
			//			} 
			//			else if (this.type == String.class)
			//				return this.valueString;
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

}
