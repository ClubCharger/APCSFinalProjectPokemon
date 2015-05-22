
import javax.swing.*;
import java.awt.*;

public class Player {

    private String name;
    private Image img;
    private boolean isPlayer;
    private Pokemon current;
	
    public Player(String name, String trainerClass, boolean player) {
	this.name = name;
	img = new ImageIcon("./src/Images/Trainers/" + trainerClass + ".png").getImage();
	isPlayer = player;
	current = V.oppPokeParty[0];
    }

    String getName() {
	return name;
    }

    Image getImage() {
	return img;
    }

    Pokemon[] getParty() {
	if (isPlayer) {
	    return V.playerPokeParty;
	}
	return V.oppPokeParty;
    }

    int getNumPokemon() {
	int i = 0;
	for (Pokemon p : getParty()) {
	    try {
		p.getSpecies(); // Check if null
		i++;
	    } catch (NullPointerException e) {
		
	    }
	}
	return i;
    }

    Pokemon getCurrent() {
	return current;
    }

    boolean nextPokemon() {
	for (Pokemon p : getParty()) {
	    if (!p.equals(null) && !p.checkFainted()) {
		current = p;
		return true;
	    }
	}
	return false;
    }

    boolean checkLoss() {
	for (Pokemon p : getParty()) {
	    if (!p.equals(null) && !p.checkFainted()) {
		return false;
	    }
	}
	return true;
    }
    
    boolean isFullParty() {
	return getNumPokemon() == 6;
    }
    
    boolean addPokemon(Pokemon p) {
	if (isPlayer && !isFullParty()) {
	    V.playerPokeParty[getNumPokemon()] = p;
	    return true;
	} else if (!isPlayer && !isFullParty()) {
	    V.oppPokeParty[getNumPokemon()] = p;
	    return true;
	}
	return false;
    }
    
    void fillTeam(int n) {
	for(int i = 0; i < n; i++) {
	    addPokemon(new Pokemon(Pokemon.generateRandom(), 0, 0));
	}
    }
}
