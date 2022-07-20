package rutinary;

import javax.swing.JFrame;

public class MainMenu {

	public static void main(String[] args) throws Exception
	{
        
		MainFrame window = new MainFrame();
		window.setVisible(true);
		window.setTitle("Rotinary");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setBounds(300,80,900,650);
	}

}
