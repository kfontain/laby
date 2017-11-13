# Laby

KENJI Fontaine - CHARLET Guillaume - ORDONEZ Romain - HALNAUT Adrien

### Sujet

[Lien](http://www.labri.fr/perso/clement/enseignements/ao/projet-2017-0.pdf).

Deadline : **Lundi 11 Décembre 2017**

### Clonage du projet (IDEA)

1. Créer un nouveau projet Java vierge.
2. Effacer le dossier `src` à la racine du projet.
3. Se placer à la racine du projet (par défaut : `C:\Users\<>\IdeaProjects\Laby`).
4. Y cloner le projet (`git clone https://github.com/kfontain/laby`)
5. Déplacer **tous** (y compris `.git`) les fichiers à l'intérieur du nouveau dossier `laby` vers la racine du projet créé en 1.
6. Effacer le dossier (maintenant vide) `laby`.
7. Indiquer à IDEA la racine du projet pour le VCS (si demandé, sinon IDEA l'a fait automatiquement).



Lors du premier commit réalisé, vérifier qu'aucun fichier de configuration n'est envoyé, au cas où.



### Structure suggérée

Modèle *MVC*

```
+ Model
| + Drawable / Objects (non statiques)
| | + Characters
| | | - Gentil
| | | - Mechant
| | + Static objects
| | | - Switches
| | | - Doors
| | | - Bonuses
| + Game core
| | - Laby/Graph
| | - Menu (?)

+ View
 (wait & see)
 
+ Controller
| - Main/Master
| + In-game
| | - Inputs
| | - Events (object interactions)
| | - GameManager (timing, etc ...)
```

