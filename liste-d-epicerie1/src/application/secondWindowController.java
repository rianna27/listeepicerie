package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class secondWindowController {
	
	@FXML
    Button about;
	private Main main1;
	
	public void setMain(Main main)
	{
		this.main1=main;
	}
	
	public void about1()
	{
		main1.mainWindow();
	}

}
