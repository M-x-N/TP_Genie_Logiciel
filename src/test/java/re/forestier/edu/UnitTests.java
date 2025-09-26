package re.forestier.edu;

import org.junit.jupiter.api.*;

import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.UpdatePlayer;
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

    // Test de la classe UpdatePlayer par instaciation
    @Test
    @DisplayName("Test UpdatePlayer")
    void testUpdatePlayer() {
        new UpdatePlayer();
    } 


    // Test pour voir si le joueur est vivant 1 point de vie ou plus
    @Test
    @DisplayName("Test Player is Alive")
    void testAlivePlayer(){
        player p1 = new player("Pierre", "Pierro", "ARCHER", 101, new ArrayList<>());
        player p2 = new player("Paul", "Paulo", "ADVENTURER", 202, new ArrayList<>());
        player p3 = new player("Jacques", "Jaco", "DWARF", 303, new ArrayList<>());

        // Low Life
        p1.currenthealthpoints = 1;
        p1.healthpoints = 1000;
        p2.currenthealthpoints = 1;
        p2.healthpoints = 100;
        p3.currenthealthpoints = 1;
        p3.healthpoints = 1000;
        UpdatePlayer.majFinDeTour(p1);
        UpdatePlayer.majFinDeTour(p2);
        UpdatePlayer.majFinDeTour(p3);

        // Mid Life
        p1.currenthealthpoints = 500;
        p1.healthpoints = 1000;
        p2.currenthealthpoints = 500;
        p2.healthpoints = 100;
        p3.currenthealthpoints = 500;
        p3.healthpoints = 1000;
        UpdatePlayer.majFinDeTour(p1);
        UpdatePlayer.majFinDeTour(p2);
        UpdatePlayer.majFinDeTour(p3);

        // Full Life
        p1.currenthealthpoints = 1000;
        p1.healthpoints = 1000;
        p2.currenthealthpoints = 1000;
        p2.healthpoints = 100;
        p3.currenthealthpoints = 1000;
        p3.healthpoints = 1000;
        UpdatePlayer.majFinDeTour(p1);
        UpdatePlayer.majFinDeTour(p2);
        UpdatePlayer.majFinDeTour(p3);
    }

    // Test pour voir si le joueur est mort (0 point de vie)
    @Test
    @DisplayName("Test Dead Player")
    void testDeadPlayer(){
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.majFinDeTour(p);
    }

    // Test des inventaires pour les classes DWARF et ARCHER
    @Test
    @DisplayName("Test Inventory for DWARF and ARCHER")
    void testInventoryPlayer(){
        player p1 = new player("Pierre", "Pierro", "DWARF", 101, new ArrayList<>());
        player p2 = new player("Paul", "Paulo", "ARCHER", 202, new ArrayList<>());
        player p3 = new player("Jacques", "Jaco", "ADVENTURER", 303, new ArrayList<>());

        p1.currenthealthpoints = 400;
        p1.healthpoints = 1000;
        p2.currenthealthpoints = 400;
        p2.healthpoints = 1000;

        p3.currenthealthpoints = 1000;
        p3.healthpoints = 400;

        p1.inventory.add("Holy Elixir");
        p2.inventory.add("Magic Bow");

        UpdatePlayer.majFinDeTour(p1);
        UpdatePlayer.majFinDeTour(p2);
        UpdatePlayer.majFinDeTour(p3);
    }    

}
    