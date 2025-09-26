package re.forestier.edu;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.player;

public class UpdatePlayerUnitTests {
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
