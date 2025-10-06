package re.forestier.edu;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.player;

public class UpdatePlayerUnitTests {
    // Test de la classe UpdatePlayer par instaciation
    @Test
    @DisplayName("Test UpdatePlayer")
    void testUpdatePlayer() {
      UpdatePlayer updatePlayer = new UpdatePlayer();
      assertThat(updatePlayer, is(notNullValue()));
    }

    // Test pour voir si le joueur est mort (0 point de vie)
    @Test
    @DisplayName("Test Dead Player")
    void testDeadPlayer(){
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.currenthealthpoints, is(0));
    }

    @Test
    @DisplayName("Test DWARF avec 'Holy Elixir' quand les hp sont en dessous de la moitié")
    void testDwarfWithHolyElixirBelowHalf() {
        player dwarf = new player("Pierre", "Pierro", "DWARF", 100, new ArrayList<>());
        dwarf.currenthealthpoints = 400;
        dwarf.healthpoints = 1000;
        dwarf.inventory.add("Holy Elixir");

        UpdatePlayer.majFinDeTour(dwarf);

        // DWARF +1 de base +1 pour l'objet Holy Elixir = +2 au total
        assertThat(dwarf.currenthealthpoints, is(402));
    }

    @Test
    @DisplayName("Test DWARF sans 'Holy Elixir' quand les hp sont en dessous de la moitié")
    void testDwarfWithoutHolyElixirBelowHalf() {
        player dwarf = new player("Pierre", "Pierro", "DWARF", 100, new ArrayList<>());
        dwarf.currenthealthpoints = 400;
        dwarf.healthpoints = 1000;

        UpdatePlayer.majFinDeTour(dwarf);

        // DWARF +1 seulement
        assertThat(dwarf.currenthealthpoints, is(401));
    }

    @Test
    @DisplayName("Test ARCHER avec 'Magic Bow' quand les hp sont en dessous de la moitié")
    void testArcherWithMagicBowBelowHalf() {
        player archer = new player("Legolas", "Elf Archer", "ARCHER", 100, new ArrayList<>());
        archer.currenthealthpoints = 400;
        archer.healthpoints = 1000;
        archer.inventory.add("Magic Bow");

        UpdatePlayer.majFinDeTour(archer);

        // ARCHER +1 de base, et le 'Magic Bow' ajoute currenthealth/8-1
        // 401/8-1 = 50-1 = 49, so total should be 401+49 = 450
        assertThat(archer.currenthealthpoints, is(450));
    }

    @Test
    @DisplayName("Test ARCHER sans 'Magic Bow' quand les hp sont en dessous de la moitié")
    void testArcherWithoutMagicBowBelowHalf() {
        player archer = new player("Paul", "Paulo", "ARCHER", 100, new ArrayList<>());
        archer.currenthealthpoints = 400;
        archer.healthpoints = 1000;

        UpdatePlayer.majFinDeTour(archer);

        // ARCHER +1 seulement
        assertThat(archer.currenthealthpoints, is(401));
    }

    @Test
    @DisplayName("Test ADVENTURER quand les hp sont en dessous de la moitié")
    void testAdventurerBelowHalf() {
        player adventurer = new player("Jacques", "Jaco", "ADVENTURER", 100, new ArrayList<>());
        adventurer.currenthealthpoints = 400;
        adventurer.healthpoints = 1000;

        UpdatePlayer.majFinDeTour(adventurer);

        // ADVENTURER +2 de base, -1 si niveau < 3 (nouveau joueur commence au niveau 1)
        assertThat(adventurer.currenthealthpoints, is(401));
    }

    @Test
    @DisplayName("Effets d'inventaire ne s'appliquent pas lorsque les hp sont à la moitié ou au-dessus")
    void testNoInventoryEffectsAtHalfHealth() {
        player dwarf = new player("Pierre", "Pierro", "DWARF", 100, new ArrayList<>());
        dwarf.currenthealthpoints = 500;
        dwarf.healthpoints = 1000;
        dwarf.inventory.add("Holy Elixir");

        UpdatePlayer.majFinDeTour(dwarf);

        // Pas de soin lorsqu'on est à exactement la moitié de la santé
        assertThat(dwarf.currenthealthpoints, is(500));
    }

     @Test
    @DisplayName("Effets d'inventaire ne s'appliquent pas lorsque les hp sont au-dessus de la moitié")
    void testNoInventoryEffectsAboveHalf() {
        player archer = new player("Paul", "Paulo", "ARCHER", 100, new ArrayList<>());
        archer.currenthealthpoints = 600;
        archer.healthpoints = 1000;
        archer.inventory.add("Magic Bow");

        UpdatePlayer.majFinDeTour(archer);

        // Pas de soin lorsqu'on est au-dessus de la moitié de la santé
        assertThat(archer.currenthealthpoints, is(600));
    }

    @Test
    @DisplayName("Test exactement à la limite de santé")
    void testExactlyAtHealthBoundary() {
        player dwarf = new player("Pierre", "Pierro", "DWARF", 100, new ArrayList<>());
        dwarf.currenthealthpoints = 499;
        dwarf.healthpoints = 1000; // 499 < 500 (moitié des hp)
        dwarf.inventory.add("Holy Elixir");

        UpdatePlayer.majFinDeTour(dwarf);

        // Devrait être soigné car 499 < 500
        assertThat(dwarf.currenthealthpoints, is(501));
    }

    @Test
    @DisplayName("Test ARCHER avec 1 hp (Low Life)")
    void testArcherLowLife() {
        player archer = new player("Pierre", "Pierro", "ARCHER", 101, new ArrayList<>());
        archer.currenthealthpoints = 1;
        archer.healthpoints = 1000;

        UpdatePlayer.majFinDeTour(archer);

        // ARCHER avec 1 hp (< 500) devrait gagner +1 hp
        assertThat(archer.currenthealthpoints, is(2));
    }

    @Test
    @DisplayName("Test ADVENTURER avec 1 hp (Low Life)")
    void testAdventurerLowLife() {
        player adventurer = new player("Paul", "Paulo", "ADVENTURER", 202, new ArrayList<>());
        adventurer.currenthealthpoints = 1;
        adventurer.healthpoints = 100;

        UpdatePlayer.majFinDeTour(adventurer);

        // ADVENTURER avec 1 hp (< 50) devrait gagner +2 hp puis -1 (niveau < 3) = +1 total
        assertThat(adventurer.currenthealthpoints, is(2));
    }

    @Test
    @DisplayName("Test DWARF avec 1 hp (Low Life)")
    void testDwarfLowLife() {
        player dwarf = new player("Jacques", "Jaco", "DWARF", 303, new ArrayList<>());
        dwarf.currenthealthpoints = 1;
        dwarf.healthpoints = 1000;

        UpdatePlayer.majFinDeTour(dwarf);

        // DWARF avec 1 hp (< 500) devrait gagner +1 hp
        assertThat(dwarf.currenthealthpoints, is(2));
    }

    @Test
    @DisplayName("Test ARCHER avec Mid Life (500 hp)")
    void testArcherMidLife() {
        player archer = new player("Pierre", "Pierro", "ARCHER", 101, new ArrayList<>());
        archer.currenthealthpoints = 500;
        archer.healthpoints = 1000;

        UpdatePlayer.majFinDeTour(archer);

        // ARCHER avec 500 hp (= 50% de 1000) - pas de soin car >= moitié
        assertThat(archer.currenthealthpoints, is(500));
    }

    @Test
    @DisplayName("Test ADVENTURER avec Mid Life (500 hp sur max 100)")
    void testAdventurerMidLifeOverMax() {
        player adventurer = new player("Paul", "Paulo", "ADVENTURER", 202, new ArrayList<>());
        adventurer.currenthealthpoints = 500;
        adventurer.healthpoints = 100;

        UpdatePlayer.majFinDeTour(adventurer);

        // ADVENTURER avec 500 hp mais max 100 - devrait être plafonné à 100
        assertThat(adventurer.currenthealthpoints, is(100));
    }

    @Test
    @DisplayName("Test DWARF avec Mid Life (500 hp)")
    void testDwarfMidLife() {
        player dwarf = new player("Jacques", "Jaco", "DWARF", 303, new ArrayList<>());
        dwarf.currenthealthpoints = 500;
        dwarf.healthpoints = 1000;

        UpdatePlayer.majFinDeTour(dwarf);

        // DWARF avec 500 hp (= 50% de 1000) - pas de soin car >= moitié
        assertThat(dwarf.currenthealthpoints, is(500));
    }

    @Test
    @DisplayName("Test ARCHER avec Full Life (1000 hp)")
    void testArcherFullLife() {
        player archer = new player("Pierre", "Pierro", "ARCHER", 101, new ArrayList<>());
        archer.currenthealthpoints = 1000;
        archer.healthpoints = 1000;

        UpdatePlayer.majFinDeTour(archer);

        // ARCHER avec hp max - pas de changement
        assertThat(archer.currenthealthpoints, is(1000));
    }

    @Test
    @DisplayName("Test ADVENTURER avec Full Life (1000 hp sur max 100)")
    void testAdventurerFullLifeOverMax() {
        player adventurer = new player("Paul", "Paulo", "ADVENTURER", 202, new ArrayList<>());
        adventurer.currenthealthpoints = 1000;
        adventurer.healthpoints = 100;

        UpdatePlayer.majFinDeTour(adventurer);

        // ADVENTURER avec 1000 hp mais max 100 - devrait être plafonné à 100
        assertThat(adventurer.currenthealthpoints, is(100));
    }

    @Test
    @DisplayName("Test DWARF avec Full Life (1000 hp)")
    void testDwarfFullLife() {
        player dwarf = new player("Jacques", "Jaco", "DWARF", 303, new ArrayList<>());
        dwarf.currenthealthpoints = 1000;
        dwarf.healthpoints = 1000;

        UpdatePlayer.majFinDeTour(dwarf);

        // DWARF avec hp max - pas de changement
        assertThat(dwarf.currenthealthpoints, is(1000));
    }

    @Test
    @DisplayName("Test ADVENTURER niveau élevé avec Low Life")
    void testAdventurerHighLevelLowLife() {
        player adventurer = new player("Paul", "Paulo", "ADVENTURER", 202, new ArrayList<>());
        adventurer.currenthealthpoints = 1;
        adventurer.healthpoints = 100;

        // Monter le niveau à 3 ou plus
        UpdatePlayer.addXp(adventurer, 37);

        UpdatePlayer.majFinDeTour(adventurer);

        // ADVENTURER niveau >= 3 avec 1 hp devrait gagner +2 hp (pas de malus)
        assertThat(adventurer.currenthealthpoints, is(3));
    }

    @Test
    @DisplayName("Test edge case: exactement à la moitié moins 1")
    void testExactlyBelowHalf() {
        player archer = new player("Pierre", "Pierro", "ARCHER", 101, new ArrayList<>());
        archer.currenthealthpoints = 499;
        archer.healthpoints = 1000;

        UpdatePlayer.majFinDeTour(archer);

        // 499 < 500 (moitié) donc devrait être soigné
        assertThat(archer.currenthealthpoints, is(500));
    }
}
