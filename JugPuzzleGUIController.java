package ca.utoronto.utm.jugpuzzle;
import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JTextField;


import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JTextField;

public class JugPuzzleGUIController extends JPanel implements ActionListener {
	// Initializing all the buttons/text fields
	JButton j1, j2, j3, Exit, Retry;
	JTextField j1a, j2a, j3a, moves;
	int from = 4;
	int to = 4;
	private JugPuzzle game;


	public JugPuzzleGUIController() {
		// Begininng the game
		game = new JugPuzzle();
		// Adding the jugs.
		j1 = new JButton("8");
		j1.setActionCommand("empty");

		j2 = new JButton("5");
		j2.setActionCommand("empty");

		j3 = new JButton("3");
		j3.setActionCommand("empty");
		
		Exit = new JButton("Exit");
		Retry = new JButton("Retry");
		
		// Listen for actions on buttons 1 2 3.
		j1.addActionListener(this);
		j2.addActionListener(this);
		j3.addActionListener(this);
		Exit.addActionListener(this);
		Retry.addActionListener(this);
		
		// Setting the textfield texts.
		j1a = new JTextField("Amount: " + game.getJug(0) + "/8", 10);
		j2a = new JTextField("Amount: " + game.getJug(1) + "/5", 10);
		j3a = new JTextField("Amount: " + game.getJug(2) + "/3", 10);
		
		Exit.setActionCommand("Exit");
		moves = new JTextField("Moves: "+ game.getMoves());
		moves.setEditable(false);
		j1a.setEditable(false);
		j2a.setEditable(false);
		j3a.setEditable(false);

		// Adding all the buttons
		add(j1);
		add(j2);
		add(j3);
		add(j1a);
		add(j2a);
		add(j3a);
		add(Exit);
		add(moves);
		add(Retry);
}

public void actionPerformed(ActionEvent e) {
	JButton ButtonPushed = (JButton) e.getSource();
	// Determining which button is the "from"
	if ((j1.getActionCommand() == "empty") && (j2.getActionCommand() == "empty") && (j3.getActionCommand() == "empty")){
		if (ButtonPushed == j1) {
			from = 0;
			j1.setActionCommand("from");
		}
		if (ButtonPushed == j2) {
			from = 1;
			j2.setActionCommand("from");
		}if (ButtonPushed == j3) {
			from = 2;
			j3.setActionCommand("from");
		}
	}
	// Determining which button is the "to"
	else{
		if (ButtonPushed == j1) {
			to = 0;
		}
		if (ButtonPushed == j2) {
			to = 1;
		}if (ButtonPushed == j3)  {
			to = 2;
		}
	}
	
	// If a 'from' and 'to' are selected, make the switch and update the view
	if (from <= 2 && to <= 2) {
		game.move(from, to);
		j1a.setText("Amount: " + game.getJug(0) + "/8");
		j2a.setText("Amount: " + game.getJug(1) + "/5");
		j3a.setText("Amount: " + game.getJug(2) + "/3");
		j1.setActionCommand("empty");
		j2.setActionCommand("empty");
		j3.setActionCommand("empty");
		moves.setText("Moves: "+ game.getMoves());
		from = 3;
		to = 3;
	}
	// If the game is won, a pop-up comes up with three options
	if (game.getJug(0) == 4 && game.getJug(1) == 4 && ButtonPushed != Retry && ButtonPushed != Exit) {		
		String[] options = {"Play Again", "Exit", "Close Dialog"};
		int x = JOptionPane.showOptionDialog(null, "Congrats you solved it in " + game.getMoves() + " moves!!", "Game Over", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		// If Exit is selected, the GUI is closed.
		if (x == 1) {
			System.exit(0);
		}
		// If 'Play Again' is selected, the game resets
		if (x == 0) {
			j1.setEnabled(true);
			j2.setEnabled(true);
			j3.setEnabled(true);
			game.retry();
			j1a.setText("Amount: " + game.getJug(0) + "/8");
			j2a.setText("Amount: " + game.getJug(1) + "/5");
			j3a.setText("Amount: " + game.getJug(2) + "/3");
			j1.setActionCommand("empty");
			j2.setActionCommand("empty");
			j3.setActionCommand("empty");
			

			moves.setText("Moves: "+ game.getMoves());
		}
		// If the user would like to close the pop-up
		if (x==2) {
			j1.setEnabled(false);
			j2.setEnabled(false);
			j3.setEnabled(false);
		}
		

	}
	// When the exit button is pushed
	if (ButtonPushed == Exit) {
		System.exit(0);		
	}
	// When the retry button is pushed.
	if (ButtonPushed == Retry) {
		game.retry();
		j1a.setText("Amount: " + game.getJug(0) + "/8");
		j2a.setText("Amount: " + game.getJug(1) + "/5");
		j3a.setText("Amount: " + game.getJug(2) + "/3");
		j1.setActionCommand("empty");
		j2.setActionCommand("empty");
		j3.setActionCommand("empty");
		j1.setEnabled(true);
		j2.setEnabled(true);
		j3.setEnabled(true);

		moves.setText("Moves: "+ game.getMoves());
	}
}



private static void createAndShowGUI() {

	//Create and set up the window.
	JFrame frame = new JFrame("Jug Puzzle");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	//Create and set up the content pane.
	JugPuzzleGUIController newContentPane = new JugPuzzleGUIController();
	
	frame.setContentPane(newContentPane);
	frame.getContentPane().setLayout(new GridLayout(3,3));
	//Display the window.
	frame.pack();
	frame.setVisible(true);
}

public static void main(String[] args) {
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
		public void run() {
		createAndShowGUI(); 
		}
		});
		}
}
