
import java.awt.*;
import javax.swing.*;

public class Pokemon extends Sprite {

    private int species;
    private int health;
    private int hp, att, def, spd;
    private int attMod, defMod, spdMod;
    private int[] moves;
    private int type1, type2;

    public Pokemon(int species, Image img, double x, double y) {
	super(img, x, y);

	this.species = species;

	hp = D.getStats(species)[0];
	att = D.getStats(species)[1];
	def = D.getStats(species)[3];
	spd = D.getStats(species)[5];

	moves = D.getMoves(species);
	health = hp;

	type1 = D.getTypes(species)[0];
	type2 = D.getTypes(species)[1];
    }

    public Pokemon(int species, double xPos, double yPos) {
	this(species, new ImageIcon("./src/Images/Pokemon/" + species + ".png").getImage(), xPos, yPos);
    }

    public void setPosition(double x, double y) {
	x = x;
	y = y;
    }

    public String getName() {
	return P.getName(getSpecies());
    }

    public void kill() {
	health = Integer.MIN_VALUE;
    }

    public int getSpecies() {
	return species;
    }

    public int getHealth() {
	return health;
    }

    public void lowerHealth(int damage) {
	health -= damage;
    }

    public int getHP() {
	return hp;
    }

    public int getAtt() {
	return (int) (att * getModRate(attMod));
    }

    public int getDef() {
	return (int) (def * getModRate(defMod));
    }

    public int getSpd() {
	return (int) (spd * getModRate(spdMod));
    }

    public double getModRate(double mod) {
	if (mod <= 0) {
	    return (2 / (-mod + 2));
	}
	return ((mod + 2) / 2);
    }

    public void lowerAtt() {
	if (attMod > -6) {
	    attMod--;
	}
    }

    public void lowerDef() {
	if (defMod > -6) {
	    defMod--;
	}
    }

    public void lowerSpd() {
	if (spdMod > -6) {
	    spdMod--;
	}
    }

    public void raiseAtt() {
	if (attMod < 6) {
	    attMod++;
	}
    }

    public void raiseDef() {
	if (defMod < 6) {
	    defMod++;
	}
    }

    public void raiseSpd() {
	if (spdMod < 6) {
	    spdMod++;
	}
    }

    public void resetStats() {
	attMod = 0;
	defMod = 0;
	spdMod = 0;
    }

    public int[] getMoves() {
	return moves;
    }

    public int getNumMoves() {
	return moves.length;
    }

    public int getType1() {
	return type1;
    }

    public int getType2() {
	return type2;
    }

    public boolean checkFainted() {
	return getHealth() <= 0;
    }

    static int generateRandom() {
	int pkmn = (int) (Math.random() * V.NUM_POKE) + 1;
	while (P.getName(pkmn) == null || P.getName(pkmn).equals("ARCEUS") || P.getName(pkmn).equals("VICTINI")) {
	    pkmn = (int) (Math.random() * V.NUM_POKE) + 1;
	}
	return pkmn;
    }
}
