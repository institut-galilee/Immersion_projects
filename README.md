# Immersion_projects

## MEMBRES DE L'EQUIPE :
* Prenom: Heba Kaddouh [Hebakaddouh](https://github.com/Hebakaddouh)
  Email : heba.kaddouh@gmail.com
	  
* Prenom: Mohamed Ben Saad [Mohaabenz](https://github.com/Mohaabenz)
  Email : bensaad.mohamedamine@gmail.com

* Prenom: Jessy Colombo [Greyman93](https://github.com/Greyman93)
  Email : jessy.colombo31@gmail.com
	  
* Prenom: Khalid Barakat [Giitto](https://github.com/Giitto)
  Email : bckhalid@hotmail.fr
	  
## Nom du produit : Set-up Immersion 3.5D;

## Idée de base :

Le but de notre produit est une immersion dans une expérience multimédia s'approchant de la technologie de la 4DX à moindre coût.
Pour cela nous devons créer un set-up adaptable sur toutes les structures (télévisions, ordinateurs...). Le set-up est composé de la façon suivante :	
	
* LED : 
Il s'agit d'une bande LED connectée à votre smartphone en bluetooth que vous pourrez contrôler à votre guise. 
Mais la particularité de cette bande est son mode immersif : Elle s'adaptera automatiquement aux couleurs prédominantes,
que ce soit pour un jeu video, un film ou juste une image, l'expérience est garantie. 

* Fauteuil : 
Pour le moment, il s'agit d'une évolution du set-up qui permettrait une meilleure immersion quelque soit l'action. 
Pour cela deux enceintes connectées à votre périphérique en bluetooth ou en filaire ammeneront le son au plus proche de soit. 
Aux cotées de ces enceintes, nous allons intégrer des capteurs sonores liés à notre ESP32 qui permettront d'analyser le son. 
	Après analyse, des vibrations seront activé en fonction de l'action. Par exemple, si le bruit d'un balle de pistolet est entendu, 
	les moteurs vibrants situés sur le dos de la chaise s'activeronts. 

* Application : 
	L'application permettra de contrôler la bande LED. Elle pourra allumer ou éteindre la bande, activer les differents modes de 
celle-ci, augmenter ou diminuer la lumière ou bien changer la couleur de la bande.

En fonction de l'avancement du projet, nous pourrons ajouter d'autres amélioration, options, composant et objets connectés sur notre set-up

#### Composants suggerés :
Au départ, nous avons imaginé le set-up avec des composants facilement trouvable, manipulable et programmable.
* LED : ESP32, BANDE LED adressable, Capteurs couleur de lumière, cable USB, kit de connection (fil) ;
* Fauteuil : housse massage ou moteurs vibrants, enceinte bluetooth, ESP32, capteurs sonores; 

## TRAVAIL RÉALISÉ

### Analyse de l'éxistant
Au cours de notre analyse, nous avons pris connaissance de la plupart des technologies se rapprochant de nos objectifs. Pour la LED, les principales technologies sont intégré à l’intérieur même d'un téléviseur et d'autres si y connecte via HDMI ou USB. Toutes les recherches effectuées, nous ont amenés à la conclusion que notre méthode n'a pas encore été exploré. Il s'agit donc d'un défi que nous nous sommes fixé de réaliser. Le fauteuil quant à lui serait une version à moindre coût de la 4DX.

### Conception du prototype

#### LED
La conception de la LED a connu 3 versions :
* Version Alpha : Sur l'ESP32 les bibliothèques n’étaient pas compatibles nous sommes donc passé à l’Arduino pour avoir un prototype rapidement. Une fois la migration faite, la LED est capable de reconnaître les couleurs avec une reproduction approximative. 

* Version Beta : Le calibrage des couleurs est intégré afin de reproduire le plus fidèlement possible ce qui est émis par n'importe quel écran LED ou LCD (d'autres test pourront permettre une adaptation polyvalente).

* Version proto : Un ajout du Bluetooth liant application et micro-contrôleur afin de faciliter l'expérience utilisateur.  

#### Application

La conception de l'application a connu 2 versions :

* Version 0.1 : Une interface simple permettant de contrôler les couleurs de la LED via Bluetooth.

* Version 0.2 : Ajout d'une interface permettant le calibrage du capteur directement via l'application en suivant des intructions détaillées. 

### Perspectives
	  
#### Fauteuil
La conception du fauteuil est encore en cours

#### Evolution

* Passage à l'ESP32 qui serait un gain en coût, en énergie et en taille.

* Permettre à l'utilisateur de mettre à jour son micro-contrôleur directement à partir de l'application

* Création d'un prototype ergonomique, compacte et facile d'installation.
