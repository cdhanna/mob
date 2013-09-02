/**
 * 
 */
package com.hanna.mobsters.ui.shared.valuepanel;

import java.lang.reflect.Field;

import com.hanna.mobsters.ui.shared.valuepanel.ValuesPanel.ValuePanelContent;


/**
 * @author cdhan_000
 *
 */
public class ClassValuesPanel extends ValuesPanel{

	public ClassValuesPanel(){
		super();
	}
	
	@Override
	public void setUpComponents(Object... parameters) {
		if (parameters.length == 1){
			Object obj = parameters[0];
			Class<?> objClass = obj.getClass();
			final Field[] fields = objClass.getFields();
			final String[] fieldNames = new String[fields.length];
			final Class<?>[] fieldTypes = new Class<?>[fields.length];
			final Object[] fieldValues = new Object[fields.length];
			for (int i = 0 ; i < fields.length ; i ++){
				fieldNames[i] = fields[i].getName();
				fieldTypes[i] = fields[i].getType();
				try {
					fieldValues[i] = fields[i].get(obj);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			super.setUpComponents(new ValuePanelContent(){

				@Override
				public Object[] getItemIDs() {
					return fields;
				}

				@Override
				public Class<?>[] getTypes() {
					return fieldTypes;
				}

				@Override
				public String[] getTypeDescriptions() {
					return fieldNames;
				}

				@Override
				public Object[] getActualObjects() {
					return fieldValues;
				}});
			super.setValues(fieldValues);
		}
	}
	
	@Override
	public Class<?>[] getSetUpParameterTypes() {
		return new Class<?>[]{Object.class};
	}
	
}
