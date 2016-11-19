package anime.view;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import anime.controller.AnimeController;
import anime.controller.InvalidInputException;
import anime.model.Anime;
import anime.model.AnimeListManager;

public class AnimeView extends JFrame {

	private static final long serialVersionUID = -8062635784771606869L;
	
	//UI elements
	private JLabel errorMessage;
	private JLabel Animepic;
	private JButton AddAnimeButton;
	private JTextField AnimeNameTextField;
	private JLabel AnimeNameLabel;
	
	//data elements
	private String error = null;
	private HashMap<Integer, Anime> animeHash;
	//private BufferedImage image;
	
	/** Creates new form AnimeView */
	public AnimeView(){
		initComponents();
		refreshData();
	}
	
	/** This method is called from within the constructor to initialize the form. */
	private void initComponents(){
		//elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);		

		Animepic = new JLabel();
		AddAnimeButton = new JButton();
		AnimeNameTextField = new JTextField();
		AnimeNameLabel = new JLabel();
		AnimeNameLabel.setText("Enter an Anime:");
		AddAnimeButton.setText("add Anime");
		AddAnimeButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				addAnimeButtonActionPerformed(evt);
			}
		});

		BufferedImage myPicture=null;
		try {
			myPicture = ImageIO.read(new File("C:\\Users\\yu-yu\\Pictures\\Saved Pictures\\banner.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Animepic = new JLabel(new ImageIcon(myPicture));

		
		// layout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
							.addComponent(AnimeNameLabel)
							.addComponent(AddAnimeButton))
						.addGroup(layout.createParallelGroup()
								.addComponent(Animepic)
								.addComponent(AnimeNameTextField))
								
						.addGroup(layout.createParallelGroup()
								)
						.addGroup(layout.createParallelGroup())
						.addGroup(layout.createParallelGroup()))
						);
		
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]{AddAnimeButton, AnimeNameLabel});
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createParallelGroup()
						.addComponent(Animepic))
				.addGroup(layout.createParallelGroup()
						.addComponent(AnimeNameLabel)
						.addComponent(AnimeNameTextField, 20,20,20))
				.addGroup(layout.createParallelGroup()
						.addComponent(AddAnimeButton))
				.addGroup(layout.createParallelGroup())
				.addGroup(layout.createParallelGroup())
				.addGroup(layout.createParallelGroup())
				);
		
		pack();
	}
	
	private void refreshData(){
		AnimeListManager aml = AnimeListManager.getInstance();
		//error
		errorMessage.setText(error);
		if(error == null || error.length() == 0){
			//Anime List
			animeHash = new HashMap<Integer, Anime>();
			Iterator<Anime> aIt = aml.getList().iterator();
			Integer i = 0;
			while(aIt.hasNext()){
				Anime a1 = aIt.next();
				animeHash.put(i, a1);
				i++;
			}
		}
		//For when error messages appear
		pack();
	}
	
	private void addAnimeButtonActionPerformed(java.awt.event.ActionEvent evt){
		//call anime controller
		AnimeController aC = new AnimeController();
		URL aURL=null;
		try {
			aURL = new URL("https://myanimelist.net/api/anime/search.xml?q=conan");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		error = null;
		try{
			aC.createAnime(AnimeNameTextField.getText(),420, 7.8, 24, true, aURL);
		} catch(InvalidInputException e){
			error = e.getMessage();	
		}
		//update visuals
		refreshData();	
	}
	
	private void suggestButtonActionPerformed(){
		AnimeListManager aml = AnimeListManager.getInstance();
		//TODO
		
	}
}
