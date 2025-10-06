package re.forestier.edu;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.player;

public class AffichageUnitTests {
    // Ajoute "epee" dans l'inventaire et vérifie que l'inventaire contient "epee"
    @Test
    @DisplayName("Fill inventory then fetch")
    void testInventory() {
        player p = new player("Maxou", "Le mec la avec ses cartes", "ADVENTURER", 100, new ArrayList<>());
        p.inventory.add("epee");
        Affichage.afficherJoueur(p);
        assertThat(p.inventory, hasItem("epee"));
    }

    // Test de la classe Affichage par instaciation puis vérification que l'instance n'est pas nulle
    @Test
    @DisplayName("Test Affichage")
    void testAffichage() {
        Affichage affichage = new Affichage();
        assertThat(affichage, is(notNullValue()));
    }
}
