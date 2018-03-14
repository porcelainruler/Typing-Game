package TypingGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;


public class TypingGame extends JFrame implements ActionListener{
	JLabel lbltime;
	JLabel lblscore;
	JLabel lblword;
	JTextField txtword;
	JButton btnstop;
	JButton btnstart;

	Timer timer;
	int timeRemaining;
	int score;
	boolean isRunning;
	String[] word;

	public TypingGame(String[] Word) {
		GridLayout layout = new GridLayout(3, 2);
        this.word=Word;
		
		Font font = new Font("Comic Sans MS", 1, 150);
		
		

		super.setLayout(layout);

		super.setTitle("Typing Tutor");
		super.setSize(100, 200);

		lbltime = new JLabel("Time", JLabel.CENTER);
		lbltime.setBorder(BorderFactory.createEtchedBorder(Color.black, Color.white));
		lbltime.setFont(font);
		lbltime.setOpaque(true);
		lbltime.setBackground(Color.magenta);
		super.add(lbltime);

		lblscore = new JLabel("Score", JLabel.CENTER);
		lblscore.setBorder(BorderFactory.createEtchedBorder(Color.black, Color.white));
		lblscore.setOpaque(true);
		lblscore.setBackground(Color.magenta);
		lblscore.setFont(font);
		super.add(lblscore);

		lblword = new JLabel("", JLabel.CENTER);
		lblword.setBorder(BorderFactory.createEtchedBorder(Color.black, Color.white));
		lblword.setFont(font);
		super.add(lblword);

		txtword = new JTextField();
		txtword.setBorder(BorderFactory.createEtchedBorder(Color.black, Color.white));
		txtword.setFont(font);
		super.add(txtword);

		btnstart = new JButton("Start");
		btnstart.setBorder(BorderFactory.createEtchedBorder(Color.black, Color.white));
		btnstart.setOpaque(true);
		btnstart.setBackground(Color.red);
		btnstart.setFont(font);
		btnstart.addActionListener(this);
		super.add(btnstart);

		btnstop = new JButton("Stop");
		btnstop.setBorder(BorderFactory.createEtchedBorder(Color.black, Color.white));
		btnstop.setOpaque(true);
		btnstop.setBackground(Color.red);
		btnstop.setFont(font);
		btnstop.addActionListener(this);
		super.add(btnstop);

		super.setExtendedState(MAXIMIZED_BOTH);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);

		super.setVisible(true);
		SetUpGame();
	}

	private void SetUpGame() {
		timer = new Timer(1000, this);
		timeRemaining = 6;
		score = 0;
		isRunning = false;

		lbltime.setText("Time: ");
		lblscore.setText("Score: ");
		lblword.setText("");
		txtword.setText("");
		txtword.setEnabled(false);
		btnstart.setText("Start");
		btnstop.setText("Stop");
		btnstop.setEnabled(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnstart) {
			Handlestart();
		} else if (e.getSource() == btnstop) {
			Handlestop();
		} else {
			Handletime();
		}

	}

	private void Handletime() {
		timeRemaining--;
		
		if(lblword.getText().equals(txtword.getText()) && lblword.getText().length()!=0 ){
			score++;
		}
		
		lblscore.setText("Score: "+score);
		
		if(timeRemaining<0){
			Handlestop();
			return;
		}

		lbltime.setText("Time: "+timeRemaining);
		
		txtword.setText("");
		
		int idx=(int)(Math.random()*word.length);
		lblword.setText(word[idx]);
	}

	private void Handlestart() {
		if (!isRunning) {
			timer.start();
			txtword.setEnabled(true);
			btnstart.setText("Pause");
			btnstop.setEnabled(true);

			isRunning = true;

			
		} else {
			timer.stop();
			txtword.setEnabled(false);
			btnstart.setText("Rsume");

			isRunning=false;
		}

	}

	private void Handlestop() {

		timer.stop();
		int choice=JOptionPane.showConfirmDialog(this, "Replay ? ");
		if(choice==JOptionPane.YES_OPTION){
			SetUpGame();
		}else if(choice==JOptionPane.NO_OPTION){
			super.dispose();
		}else{
			if(timeRemaining<0){
				SetUpGame();
			}else{
				timer.start();
			}
		}
	}
	

	public static void main(String[] args) {
	 	// ac gf af ad hf gg
		String str="ac gf af ad hf gg";
	 TypingGame tt=new TypingGame(args);

	}

}
