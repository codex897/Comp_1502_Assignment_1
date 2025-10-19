package mru.game.view;
import java.util.ArrayList;
import java.util.Scanner;

import mru.game.controller.Card;
import mru.game.model.Player;

public class gameplayMenu {
	
	private Scanner input;
	
	
	public gameplayMenu(){
		input = new Scanner(System.in);
	}
	
	public void showSearchTableLabel() {
		System.out.println("");
		System.out.println("                 - BLACKJACK -                 ");
		System.out.println("+======================+======================+");
		System.out.println("||PLAYER               |DEALER               ||");
		System.out.println("+======================+======================+");
		
	
		}
	public void showTableCard(String playerCard, String dealerCard) {
		System.out.println(String.format("|%-22s|%-22s|", playerCard, dealerCard));
		System.out.println("+----------------------+----------------------+");
		
	}
}

