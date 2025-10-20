package mru.test.view;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import mru.game.view.gameplayMenu;
class GameplayMenuTest {

	//not much automatic testing to do with menu display classes
	@Test
	void testWinRounding() {
		gameplayMenu menu = new gameplayMenu();
		menu.showWin(12.124) ;
		//should display " You WON $12.12"
		
		menu.showWin(12.12) ;
		//should display " You WON $12.12"
		
		menu.showWin(12.125) ;
		//should display " You WON $12.13"

		
	}
	@Test
	void testLossRounding() {
		gameplayMenu menu = new gameplayMenu();
		menu.showLoss(99.124) ;
		//should display " You WON $99.12"
		
		menu.showLoss(99.12) ;
		//should display " You WON $99.12"
		
		menu.showLoss(99.125) ;
		//should display " You WON $99.13"

		
	}
	
	

}