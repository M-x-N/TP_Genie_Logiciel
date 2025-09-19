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

    // Teste de la classe Affichage par instaciation
    @Test
    @DisplayName("Test Affichage")
    void testAffichage() {
        new Affichage();
    } 

    
}
