package setup;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;

public class Start {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start window = new Start();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Start() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(400,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		String[] programas = new String[4];
		programas[0] = "Jogo do Caos";
		programas[1] = "Lindemayer";
		programas[2] = "Mandlebrot e Julia";
		//programas[3] = "Shark Attack";
		
		
		JComboBox comboBox = new JComboBox(programas);
		comboBox.setBounds(100, 168, 200, 21);
		
		comboBox.addActionListener(new ActionListener() {     
		     
		     
		     @Override
		     public void actionPerformed(ActionEvent e) {
		    	 ProcessingSetup p = new ProcessingSetup();
		    	 p.setPrograma(comboBox.getSelectedItem().toString());  
		    	 frame.dispose();
		     }
			
		   });
		
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel = new JLabel("MSSN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Rockwell", Font.PLAIN, 30));
		lblNewLabel.setBounds(150, 21, 100, 88);
		frame.getContentPane().add(lblNewLabel);
		
		
	}
}
