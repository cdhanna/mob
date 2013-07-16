/**
 * 
 */
package com.hanna.mobsters.ui.shared;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

/**
 * @author Chris Hanna
 *
 */
public abstract class Panel extends JPanel{

	/**
	 * 
	 */
	public Panel(){
		this.setLayout(new MigLayout());
		this.initComponents();
		this.addComponents();
	}
	
	protected abstract void initComponents();
	protected abstract void addComponents();
	
	
	
	/**
	 * Give the panel all of the information it needs to populate. This will vary for different implementations
	 * To know what information to pass, use the <i> getSetUpParameters() <i> function. When implementing this function
	 * there is a class protected helper function called doesInputMatchExpected(Object[] x). Use that function.
	 * @param parameters
	 */
	public abstract void setUpComponents(Object... parameters);
	
	/**
	 * This will tell you what the panel expects to receive in its <i> setUpComponents(Object... x) <i> function.
	 * @return
	 */
	public abstract Object[] getSetUpParameterTypes();
	
	
	protected boolean doesInputMatchExpected(Object[] parameters){
		Object[] types = this.getSetUpParameterTypes();
		if (types.length != parameters.length) 
			return false;
		else for (int i = 0 ; i < types.length ; i ++)
			if (types[i] != parameters[i].getClass())
				return false;
		return true;
	}
	
	private static class SetUpException extends Exception{
		public SetUpException(Object[] parameters, Class<?>[] types){
			super(assembleMessage(parameters, types));
		}
		private static String assembleMessage(Object[] parameters, Class<?>[] types){
			String message = "SetUpException.\nRequired Types : ";
			for (Class<?> type : types)
				message += type.getSimpleName() + ", ";
			message += "}\nGiven Types and Values : ";
			for (Object param : parameters)
				message += param.getClass().getSimpleName() + " = " + param + ", ";
			message += "\n";
			return message;
		}
	}
	
}
