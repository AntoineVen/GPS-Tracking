# Monuments Tracking App

## Présentation Générale

Notre projet est une application pour smartwatch permettant à l’utilisateur de localiser et afficher les monuments autour de lui grâce à sa position GPS et à la base de données Wiki Loves Monuments. C’est une application destinée aussi bien aux touristes en visite qu’aux citadins souhaitant en découvrir plus sur leur propre ville. Le choix d’une application smartwatch permet d’être guidé sans avoir à se soucier de sortir son téléphone, et permet une découverte plus naturelle.

Lors de l’ouverture de l’application, celle-ci vérifie que les autorisations de géolocalisation sont bien attribuées, puis récupère la localisation de l’utilisateur. Cette localisation est ensuite utilisée comme paramètre pour récupérer la liste des monuments environnants, grâce à la base de données fournie par l’API Wiki Loves Monuments.

Une fois la liste des monuments et de leurs caractéristiques récupérés, ceux-ci sont affichés sous forme de liste.
Le nom du monument, sa distance de l’utilisateur, ainsi que son image sont les éléments affichés à l’utilisateur. Un symbole de coche est aussi affiché si l’utilisateur a déjà par le passé visité ce monument, information stockée dans une base de données en ligne.
L’utilisateur peut cliquer sur un monument afin de directement lancer la navigation vers celui-ci depuis sa position avec Google Map si cette application est installée sur l’appareil.

L’utilisateur peut défiler dans la liste des monuments environnants simplement en montant ou descendant grâce à l'écran tactile. La liste est triée par distances croissantes, permettant à l’utilisateur de voir au plus vite les monuments les plus proches.

## Besoins spécifiques de l’application

Afin d’assurer le bon fonctionnement de l’application, le contexte de déploiement aura besoin de fournir:
- Une connection GPS mise à disposition par la montre ou le téléphone associé. Les besoins de stabilité et de précision de la localisation GPS sont faibles dans notre cas. En effet l’application n’est pas soumise à des contraintes d’urgence temporelles importantes, et étant donné l’échelle de distance avec les bâtiments (0 à 4000 m) la localisation GPS reste pertinente même dans le cas d’une imprécision d’une centaine de mètres.
- Une connexion Internet mise à disposition par la montre ou le téléphone associé. Cette connexion ne nécessite pas une grande stabilité ni puissance pour le fonctionnement minimum de l’application, mais une bonne passante relativement conséquente sera consommée occasionnellement lors de l’import des images des monuments. Tout besoin de connexion internet est uniquement dédié à la récupération d’informations stockées ailleurs, de façon asynchrone, et peut être délayé sans problème de fonctionnement.
- Dans le cas où la montre connectée utilise la connexion GPS ou internet d’un téléphone, une connexion Bluetooth sera nécessaire au pairage de la montre avec celui-ci. Cependant, l’application peut aussi être utilisée en mode standalone si la montre possède une connectique GPS et internet, et dans ce cas ne nécessitera pas de connexion Bluetooth.

## Utilisation et Installation
Pour utiliser l’application, il est nécessaire d’avoir une montre connecté possédant un GPS et une connexion internet si l’usage est fait sans appareillage avec un téléphone. Dans le cas contraire, une connexion Bluetooth avec un téléphone possédant la ou les connexions manquantes à la montre est nécessaire.

L’application n’est actuellement pas publiée sur le Play Store compte tenu des restrictions de temps sur le projet. Il est en conséquence nécessaire de “Sideload” le fichier APK depuis un ordinateur vers la montre connectée afin de télécharger l’application.

L’APK de l’application est disponible à la racine du dossier Github de l’application: https://github.com/AntoineVen/GPS-Tracking

Pour déployer une application Android (APK) depuis votre ordinateur vers votre montre connectée Wear OS, suivez ces étapes :
1. Assurez-vous que votre montre connectée est connectée à votre ordinateur via Bluetooth.
2. Sur votre ordinateur, ouvrez l'outil de ligne de commande Android Debug Bridge (ADB). Vous pouvez trouver cet outil dans le dossier Android SDK de votre ordinateur.
3. Dans l'outil ADB, exécutez la commande adb devices pour afficher une liste des périphériques connectés. Vous devriez voir votre montre connectée dans cette liste.
4. Dans l'outil ADB, exécutez la commande adb install suivie du chemin vers le fichier APK que vous souhaitez installer sur votre montre connectée. Par exemple : adb install /chemin/vers/l'application.apk
5. Attendre que le fichier APK soit transféré vers votre montre connectée et installé.
6. Une fois l'installation terminée, vous devriez voir un message indiquant que l'application a été installée avec succès. Sur votre montre connectée, accédez à la liste des applications installées et appuyez sur l'application que vous venez d'installer pour lancer l'application.

Il se peut que vous ayez besoin d'activer le mode développeur sur votre montre connectée pour pouvoir installer des applications à l'aide de l'outil ADB. Consultez la documentation de votre montre connectée pour obtenir des instructions sur la façon de procéder.
