import java.io.IOException;

import java.util.Random;
import java.util.Scanner;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class tic_toc {
	private static char posn[] = new char[10];
	static char posndef[] = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	public static boolean Winner = false;
	static boolean Turn = true;
	static boolean OnOff = true;
	static boolean Aion = false;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int key;
		while (OnOff) {

			System.out.println(" Press 1: Play \n Press 2: for restart\n Press 3: To turn On/Off ai");
			Scanner scan = new Scanner(System.in);
			key = scan.nextInt();

			switch (key) {
			case 1:
				Game();
				break;

			case 2:
				Restart();
				Game();
				break;

			case 3:
				if (Aion == true) {
					Aion = false;
					System.out.println("AI is now turn off\\n\n");
					
					
				}else {
				Aion = true;
				System.out.println("AI is now on BEWARE\n\n");
				}
			}

			
		}
	}

	public static int Ai() {
		Random rn = new Random();
		int ai = rn.nextInt(9);
		return ai;

		/// se till att den inte loopar för evigt när det är slut

	}

	public static void Restart() {

		posndef = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		Winner = false;

	}

	public static String CurrentBoard() {
		// System.out.println("\n\n");
		System.out.println("\n\n\t\t" + "    |  " + "  | ");
		System.out.println(" \t\t  " + posn[1] + " | " + posn[2] + "  | " + posn[3]);
		System.out.println(" \t\t ___|____|___ ");
		System.out.println("\t\t" + "    |  " + "  | ");
		System.out.println(" \t\t  " + posn[4] + " | " + posn[5] + "  | " + posn[6]);

		System.out.println(" \t\t ___|____|___ ");
		System.out.println("\t\t" + "    |  " + "  | ");
		System.out.println(" \t\t  " + posn[7] + " | " + posn[8] + "  | " + posn[9]);
		System.out.println(" \t\t    |    |   ");
		System.out.println(" \t\t    |    |   ");
		// System.out.println("\n\n");
		return "CurrentBoard";
	}

	public static void Game() {
		System.out.println("Welcome to Tici Tac");
		Random rn = new Random();
		Scanner input = new Scanner(System.in);
		int answer = rn.nextInt(2);
		if (answer < 1) {
			Turn = false;

		}

		while (Winner == false) {

			Refresh();

			if (Turn == true) {
				if (CheckWinner() == true) {
					playSoundWin();

					Winner = true;

				} else if (CheckDraw() == true) {
					Winner = true;
					playSoundLose();

				} else {

					System.out.println("Type what number to place X");

					int chose = input.nextInt();
					if (Occuipied(chose) == true) {
						System.out.println("Sorry that spot is already taken try again");
						chose = input.nextInt();
						posndef[chose] = 'X';
						ChangePlayer();
					} else {
						posndef[chose] = 'X';

						ChangePlayer();
					}
				}

			} else {
				if (CheckWinner() == true) {

					Winner = true;

				} else if (CheckDraw() == true) {
					Winner = true;

				} else {
					if (Aion == true) {
						int Ain = (Ai() + 1);
						
						System.out.println("Ai tal"+Ain);
						

						 
						
						if (Occuipied(Ain) == true) {
							//System.out.println("Sorry that spot is already taken try again");

							
							posndef[Ain] = 'O';
							ChangePlayer();
							}
						else {posndef[Ain] = 'O';
						ChangePlayer();}
						
						
					}

					 else  {
						
						 

					System.out.println("Type what number to place O");

					int chose = input.nextInt();
					if (Occuipied(chose) == true) {
						System.out.println("Sorry that spot is already taken try again");

						chose = input.nextInt();
						posndef[chose] = 'O';
						ChangePlayer();
					} else {
						posndef[chose] = 'O';

						ChangePlayer();
					}
				}

			}

		}}

	}

	public static void ChangePlayer() {
		if (Turn == true) {
			playSoundFlip();
			Turn = false;
		} else {
			playSoundFlip();
			Turn = true;
		}

	}
	public static void playSoundFlip() {
		try {
			Clip clip = AudioSystem.getClip();
			File ljudfil= new File("src/pickupCoin.wav");
			clip.open(AudioSystem.getAudioInputStream(ljudfil));
			clip.start(); 

		} catch (Exception exc) {
			System.out.println("The specified audio file is not supported.");

		}
		
	}
	public static void playSoundLose() {
		try {
			Clip clip = AudioSystem.getClip();
			File ljudfil= new File("src/Lose.wav");
			clip.open(AudioSystem.getAudioInputStream(ljudfil));
			clip.start(); 

		} catch (Exception exc) {
			System.out.println("The specified audio file is not supported.");

		}
		
	}
	public static void playSoundWin() {
		try {
			Clip clip = AudioSystem.getClip();
			File ljudfil= new File("src/Winning.wav");
			clip.open(AudioSystem.getAudioInputStream(ljudfil));
			clip.start(); 

		} catch (Exception exc) {
			System.out.println("The specified audio file is not supported.");

		}
		
	}

	public static boolean Occuipied(int chose) {
		System.out.println(chose);
		System.out.println(posndef[chose]);
		if (posndef[chose] == 'X' || posndef[chose] == 'O') {

			return true;
		} else {
			return false;
		}

	}

	public static String Refresh() {
		for (int i = 0; i < posn.length; i++) {
			posn[i] = posndef[i];

		}
		return CurrentBoard();

	}

	public static boolean CheckDraw() {
		if ((posn[1] != '1' && posn[2] != '2' && posn[3] != '3' && posn[4] != '4' && posn[5] != '5' && posn[6] != '6'
				&& posn[7] != '7' && posn[8] != '8' && posn[9] != '9')) {
			System.out.println("Draw");
			return true;
		} else {
			return false;
		}
	}

	public static boolean CheckWinner() {
		if ((posn[1] == 'X' && posn[2] == 'X' && posn[3] == 'X') || (posn[4] == 'X' && posn[5] == 'X' && posn[6] == 'X')
				|| (posn[7] == 'X' && posn[8] == 'X' && posn[9] == 'X')
				|| (posn[1] == 'X' && posn[4] == 'X' && posn[7] == 'X')
				|| (posn[2] == 'X' && posn[5] == 'X' && posn[8] == 'X')
				|| (posn[3] == 'X' && posn[6] == 'X' && posn[9] == 'X')
				|| (posn[1] == 'X' && posn[5] == 'X' && posn[9] == 'X')
				|| (posn[7] == 'X' && posn[5] == 'X' && posn[3] == 'X')

		) {
			System.out.println("X is the winner");

			return true;

		} else if ((posn[1] == 'O' && posn[2] == 'O' && posn[3] == 'O')
				|| (posn[4] == 'O' && posn[5] == 'O' && posn[6] == 'O')
				|| (posn[7] == 'O' && posn[8] == '0' && posn[9] == 'O')
				|| (posn[1] == 'O' && posn[4] == 'O' && posn[7] == 'O')
				|| (posn[2] == 'O' && posn[5] == 'O' && posn[8] == 'O')
				|| (posn[3] == 'O' && posn[6] == 'O' && posn[9] == 'O')
				|| (posn[1] == 'O' && posn[5] == 'O' && posn[9] == 'O')
				|| (posn[7] == 'O' && posn[5] == 'O' && posn[3] == 'O')) {
			System.out.println("O is the winner");
			return true;

		}

		else {
			return false;
		}

	}

}
