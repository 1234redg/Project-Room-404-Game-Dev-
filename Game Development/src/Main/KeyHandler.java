package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed,  enterPressed;
    public boolean  checkDrawTime = false;

    // Constructor
    public KeyHandler(GamePanel gp) {
       this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Usually left empty
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode(); // Get the key code of the pressed key
        
        	if(gp.gameState == gp.playState) {
        		
        		 if (code == KeyEvent.VK_W) {
        	            upPressed = true;
        	        }
        	        if (code == KeyEvent.VK_S) {
        	            downPressed = true;
        	        }
        	        if (code == KeyEvent.VK_A) {
        	            leftPressed = true;
        	        }
        	        if (code == KeyEvent.VK_D) {
        	            rightPressed = true;
        	        }
        	        if (code == KeyEvent.VK_P  ) {
        	        	gp.gameState = gp.pauseState;          
        	        }
        	        if (code == KeyEvent.VK_ENTER  ) {
        	         enterPressed = true;      
        	        }
        	        
        	        // Clue Tracker toggle (J key)
        	        if (code == KeyEvent.VK_J) {
        	            if (gp.clueTrackerUI != null) {
        	                gp.clueTrackerUI.toggleVisibility();
        	            }
        	        }
        	        
        	        //debug
        	            if(code == KeyEvent.VK_T) {
        	            	if(checkDrawTime == false) {
        	            		checkDrawTime = true;
        	            	}
        	            	else if(checkDrawTime == true) {
        	            		checkDrawTime = false;
        	            	}
        	       }
        	           		
        	}
        	
        	// pause state
        	if(gp.gameState == gp.pauseState);
        	if (code == KeyEvent.VK_P  ) {
	        	gp.gameState = gp.playState;          
	        }
        	
        	// dialogue state
        	if(gp.gameState == gp.dialogueState) {
        		if(code == KeyEvent.VK_ENTER) {
        			// Call ENTER handler on current NPC
        			if(gp.currentNPC >= 0 && gp.npc[gp.currentNPC] != null) {
        				gp.npc[gp.currentNPC].onEnterPressed();
        			}
        			enterPressed = false;
        		}
        	}
    }

    @Override
	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode();// Get key code when released
		
		// Reset key flags to false when keys are released
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;	
		}
	}
}
