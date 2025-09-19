# Notes des tests à faire 

## Unitaires

**Tests dans le fichier UnitTests.java**

### Classe Affichage

- "Affichage" : remplir inventaire pour afficher (Test : `Fill inventory then fetch`)

### Classe Player

- "Level" : Tester un joueur jusqu'au Level 5 
- "Add money" : Tester les ajouts d'argents pour le joueur (négatif, null, 0, positif)
- "Remove Money" : vérifie qu'on ne peut pas avoir de chiffre négatif en argent, retirer plus d'argent que ce que possède le joueur, retirer un nombre d'argent correct pour tester le `else` 
- "Player name" est correctement affecté 
- "Player class" vérifier que la classe choisi est une classe disponible (Tests possibles : null, " ", Typage incorrect, Aventurier, Nain, archer)


### Classe UpdatePlayer

- "Mise A jour" : Vérification des fin de tours (f° majFinDeTour)
- "Add Xp" : Tester les ajouts d'argents pour le joueur

### Classe Main

- Tester que l'application se lance correctement 