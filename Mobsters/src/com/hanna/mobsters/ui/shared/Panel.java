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
	public abstract Class<?>[] getSetUpParameterTypes();
	
	
	protected final boolean doesInputMatchExpected(Object[] parameters){
		
//		System.out.println("expected:");
//		for (Class<?> c : getSetUpParameterTypes())
//			System.out.println("\t"+c.getSimpleName());
//		System.out.println("got:");
//		for (Object o : parameters)
//			System.out.println("\t"+o.getClass().getSimpleName());
		
		
		Object[] types = this.getSetUpParameterTypes();
		if (types.length != parameters.length) 
			return false;
		else for (int i = 0 ; i < types.length ; i ++){
			
			if (types[i] != parameters[i].getClass())
				if (parameters[i].getClass().getSuperclass() != types[i])
					return false;
		}
		return true;
	}
	
	
}
