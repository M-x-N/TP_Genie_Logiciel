package re.forestier.edu;

import org.junit.jupiter.api.*;

import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.player;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

public class UnitTests {

    @Test
    @DisplayName("Sample test")
    void testPlayerName() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(player.playerName, is("Florian"));
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

    // Ajoute "epee" dans l'inventaire et vérifie que l'inventaire contient "epee"
    @Test
    @DisplayName("Fill inventory then fetch")
    void testInventory() {
        player p = new player("Maxou", "Le mec la avec ses cartes", "ADVENTURER", 100, new ArrayList<>());
        p.inventory.add("epee");
        Affichage.afficherJoueur(p);
        assertThat(p.inventory, hasItem("epee"));
    }

    // Test de la classe Affichage par instaciation
    @Test
    @DisplayName("Test Affichage")
    void testAffichage() {
        new Affichage();
    } 

    // Test de la méthode getXP() de la classe player
    @Test
    @DisplayName("XP getter")
    void testGetXP() {
        player p = new player("Maxou", "Le mec la avec ses cartes", "ADVENTURER", 100, new ArrayList<>());
        int xp = p.getXp();
        assertThat(xp, is(0));
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

}
