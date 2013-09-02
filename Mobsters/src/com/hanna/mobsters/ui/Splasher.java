package com.hanna.mobsters.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.hanna.mobsters.utilities.MediaLoader;

import net.miginfocom.swing.MigLayout;

public class Splasher extends JFrame{

	public static void main(String[] args){
		Thread main = new Thread(new Runnable(){
			@Override
			public void run() {
				instance = new Splasher();
			}});
		main.start();
		
	}
	
	private static Splasher instance;
	public static Splasher getInstance(){
		if (instance == null)
			instance = new Splasher();
		return instance;
	}
	
	public static void setMsg(String message){
		if (instance != null)
			instance.setMessage(message);
		//getInstance().setMessage(message);
	}
	
	private final String messagePreface = "> LOADING : ";
	private final String messagePostScript = " ...";
	public void setMessage(final String message){
		
		Runnable r = new Runnable(){
			@Override
			public void run() {
				messageLabel.setText(messagePreface + message + messagePostScript);
				messageLabel.setBounds(50,158, messageLabel.getPreferredSize().width,messageLabel.getPreferredSize().height);

				repaint();
				revalidate();
				
			}};
//				
		Thread t = new Thread(r);
		t.start();
//		
//		try {
//			Thread.sleep(300);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	private JLabel messageLabel;
	
	private Splasher(){
		
		//JPanel panel = new JPanel(new MigLayout());
		JPanel panel = new JPanel(null);
		this.add(panel);
		ImageIcon image = new ImageIcon(MediaLoader.load("media/splash2.png"));
		ImageIcon ico = new ImageIcon(MediaLoader.load("media/icon.png"));
		this.setIconImage(ico.getImage());
		JLabel imageLabel = new JLabel(image);
		this.setUndecorated(true);
		this.setAlwaysOnTop(true);
		
		
		imageLabel.setOpaque(false);
		panel.setOpaque(false);
		this.getRootPane().setOpaque(false);
		this.getContentPane().setBackground(new Color(0,255,0,0));
		this.setBackground(new Color(0,255,0,0));

		panel.setPreferredSize(new Dimension(image.getIconWidth(),image.getIconHeight()));

		messageLabel = new JLabel("> LOADING");
		messageLabel.setForeground(Color.red);
		messageLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		panel.add(messageLabel);
		messageLabel.setBounds(50,158, messageLabel.getPreferredSize().width,messageLabel.getPreferredSize().height);
		
		//panel.add(imageLabel, "cell 0 0");
		panel.add(imageLabel);
		imageLabel.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
		panel.repaint();
		

		
		panel.repaint();
		//panel.add(messageLabel, "cell 0 1");
		

		//this.add(messageLabel);
		Dimension screenSize = this.getContentPane().getToolkit().getScreenSize();
		this.setLocation(screenSize.width/2 - (image.getIconWidth()/2), screenSize.height/2 - (image.getIconHeight()/2));
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setMessage("Toad");
//		SwingUtilities.invokeLater(new Runnable(){
//
//			@Override
//			public void run() {
//				
//				Window.main(new String[]{});
//				//setVisible(false);
//			}});
		//Window.main(new String[]{});
		Thread windowThread = new Thread(new Runnable(){
			@Override
			public void run() {
				
				Window.launch(new String[]{});
				setVisible(false);
			}});
		windowThread.start();
	}
	
}
