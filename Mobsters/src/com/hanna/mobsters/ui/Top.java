/**
 * 
 */
package com.hanna.mobsters.ui;

import com.hanna.mobsters.ui.core.ToolBarController;
import com.hanna.mobsters.ui.core.TopPanelController;

/**
 * @author Chris Hanna
 *
 */
public class Top {

	public static final TopPanelController controller = Window.getInstance().getController();
	public static final ToolBarController toolBar = controller.getPanel().getToolBarController();
	
}
