package re.forestier.edu;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.player;

public class PlayerUnitTest {

    // Test de la méthode getXP() de la classe player
    @Test
    @DisplayName("XP getter")
    void testGetXP() {
        player p = new player("Maxou", "Le mec la avec ses cartes", "ADVENTURER", 100, new ArrayList<>());
        int xp = p.getXp();
        assertThat(xp, is(0));
    }   

    @Test
    @DisplayName("Impossible to have negative money")
    void testNegativeMoney() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());

        try {
            p.removeMoney(200);
        } catch (IllegalArgumentException e) {
            return;
        }
        fail();
    }

    // Test des différentes classes possibles pour un joueur
    @Test
    @DisplayName("player class")
    void testClass() {
        player p1 = new player("Pierre", "Pierro", "ARCHER", 101, new ArrayList<>());
        player p2 = new player("Paul", "Paulo", "ADVENTURER", 202, new ArrayList<>());
        player p3 = new player("Jacques", "Jaco", "DWARF", 303, new ArrayList<>());
        player p4 = new player("Machin", "Machino", "", 404, new ArrayList<>());
        assertThat(p1.getAvatarClass(), is("ARCHER"));
        assertThat(p2.getAvatarClass(), is("ADVENTURER"));
        assertThat(p3.getAvatarClass(), is("DWARF"));
        assertThat(p4.getAvatarClass(), is(nullValue()));
    }

    // Test pour enlever de l'argent à un joueur 
    @Test
    @DisplayName("Possible to have positive money")
    void testPositiveMoney() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());

        try {
            p.removeMoney(50);
        } catch (Exception e) {
            assertThat(e, is(instanceOf(IllegalArgumentException.class)));
        }
    }

     // Test d'ajouter de l'argent à un joueur
    @Test
    @DisplayName("Adding money")
    void testAddMoney() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());

        try {
            p.addMoney(50);
        } catch (Exception e) {
            assertThat(e, is(instanceOf(IllegalArgumentException.class)));
        }
    }

    // Test d'ajouter de l'argent null à un joueur ne change pas son argent
    @Test
    @DisplayName("addMoney(0) does not change money")
    void testAddNoMoney() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        int initialMoney = p.money;
        p.addMoney(0);
        assertThat(p.money, is(initialMoney));
    }

    @Test
    @DisplayName("addMoney with negative value decreases money")
    void testAddNegativeMoney() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        int initialMoney = p.money;
        
        p.addMoney(-25);
        
        assertThat(p.money, is(initialMoney - 25));
    }

    @Test
    @DisplayName("Test retrieve player level based on XP")
    void testRetrievePlayerLevel() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
 
        UpdatePlayer.addXp(p, 0);  
        assertThat(p.retrieveLevel(), is(1));
        
        UpdatePlayer.addXp(p, 10); 
        assertThat(p.retrieveLevel(), is(2));

        UpdatePlayer.addXp(p, 27); 
        assertThat(p.retrieveLevel(), is(3));
        
        UpdatePlayer.addXp(p, 57); 
        assertThat(p.retrieveLevel(), is(4));
        
        UpdatePlayer.addXp(p, 111); 
        assertThat(p.retrieveLevel(), is(5));
    }
}
