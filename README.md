

# Mémoire Valentin Duchemin
[![Build Status](https://travis-ci.com/valentinduchj/M2Memory.svg?branch=master)](https://travis-ci.com/valentinduchj/M2Memory)

Bonjour à tous, ici vous allez trouver tous les moyens nécessaires pour faire tourner mes programmes qui se divisent en 2 catégories: 

 1. L'Algorithme de Segmentation d'Image (**AlgorithmClustering**) 
 2. La mesure de Qualité en Rand Index (**RandIndex**)
 3. Eclairage Lightpack sur Image (**AlgorithmeEclairage**)

# Algorithme de Segmentation d'Image

Cet algorithme permet de segmenter une image (maximum 600x600) en plusieurs Clustering sous plusieurs itérations.
Il faut ajouter dans le main

    AlgorithmeClustering algo1 = new AlgorithmeClustering();
	String image = "lien image";
	algo1.initializeManually(image, int C, int I, int T);

 - **lien image** est le path associé à votre image que vous souhaitez clusteriser
 - **int C** est le nombre de clusters souhaité. **{** C ∈ ![\mathbb {N} ](https://wikimedia.org/api/rest_v1/media/math/render/svg/fdf9a96b565ea202d0f4322e9195613fb26a9bed) | 0 < C< ∞ **}** = ]0,∞[ 
 - **int I** est le nombre d'Itération souhaité. **{** I ∈ ![\mathbb {N} ](https://wikimedia.org/api/rest_v1/media/math/render/svg/fdf9a96b565ea202d0f4322e9195613fb26a9bed) | 0 < I< ∞ **}** = ]0,∞[ 
 - **int <img src="https://latex.codecogs.com/gif.latex?$\Gamma$"/>***   est la tolérance appliquée pour la mesure du Clustering **{** <img src="https://latex.codecogs.com/gif.latex?$\Gamma$"/> ∈ ![\mathbb {N} ](https://wikimedia.org/api/rest_v1/media/math/render/svg/fdf9a96b565ea202d0f4322e9195613fb26a9bed) | 0 < <img src="https://latex.codecogs.com/gif.latex?$\Gamma$"/> < 255 **}** = [0,255] 
 
*Exemple*
> AlgorithmeClustering algo1 = new AlgorithmeClustering(); 
>
> String image = "src/main/resources/google-chrome-300x300.png"; 
>
> algo1.initializeManually(image, 100, 300, 20);

**<img src="https://latex.codecogs.com/gif.latex?$\Gamma$"/>*** : La Tolérance permet d'obtenir une marge d'erreur négligeable au calcul de la qualité. 
La qualité <img src="https://latex.codecogs.com/gif.latex?$Q$"/> se mesure comme telle :
 - Nous allons comparer chaque pixel de la nouvelle image (*pn{r,g,b}*) par rapport à l'ancienne (*pa{r,g,b}*)
 - La différence <img src="https://latex.codecogs.com/gif.latex?$B$"/> de l'image chaque pixel  <img src="https://latex.codecogs.com/gif.latex?$P$"/>*{r,g,b}* se calcul : 
 
<img src="https://latex.codecogs.com/gif.latex?$B$"/> = <img src="https://latex.codecogs.com/gif.latex?$\sum_{i=1}^{Width*Height}"/>  ||<img src="https://latex.codecogs.com/gif.latex?$pn_{i}$r"/> - <img src="https://latex.codecogs.com/gif.latex?$pa_{i}r$"/> || + ||<img src="https://latex.codecogs.com/gif.latex?$pn_{i}g$"/> - <img src="https://latex.codecogs.com/gif.latex?$pa_{i}g$"/> || + || <img src="https://latex.codecogs.com/gif.latex?$pn_{i}b$"/> - <img src="https://latex.codecogs.com/gif.latex?$pa_{i}b$"/> || = {<img src="https://latex.codecogs.com/gif.latex?$B$"/>+= si **<img src="https://latex.codecogs.com/gif.latex?$\Gamma$"/>** < <img src="https://latex.codecogs.com/gif.latex?$P$"/>{r,g,b}}
- <img src="https://latex.codecogs.com/gif.latex?$Q$"/> = <img src="https://latex.codecogs.com/gif.latex?\frac{100B}{(255*Width*Height)/2}"/>

## Rand Index

L'**indice de Rand**  est une mesure de similarité entre deux partitions d'un ensemble. Il est principalement utilisé en catégorisation automatique. Son principe est de mesurer la consistance (le taux d'accord) entre deux partitions.

Il faut ajouter dans le main 

    	RandIndex algo1 = new RandIndex();
		algo1.IntializeLists();
	
L'indice de Rand est calculé comme suit :

<img src="https://latex.codecogs.com/gif.latex?$R$"/>(<img src="https://latex.codecogs.com/gif.latex?$S$"/>,<img src="https://latex.codecogs.com/gif.latex?$S'$"/>) = <img src="https://latex.codecogs.com/gif.latex?\frac{1}{Y}"/><img src="https://latex.codecogs.com/gif.latex?$\sum_{i,j}$"/>[<img src="https://latex.codecogs.com/gif.latex?$I$"/>(<img src="https://latex.codecogs.com/gif.latex?$l_{i}$"/> = <img src="https://latex.codecogs.com/gif.latex?$l_{j}$"/><img src="https://latex.codecogs.com/gif.latex?$\bigwedge$"/><img src="https://latex.codecogs.com/gif.latex?$l'_{i}$"/> = <img src="https://latex.codecogs.com/gif.latex?$l'_{j}$"/>) + <img src="https://latex.codecogs.com/gif.latex?$I$"/>(<img src="https://latex.codecogs.com/gif.latex?$l_{i}$"/> = <img src="https://latex.codecogs.com/gif.latex?$l_{j}$"/><img src="https://latex.codecogs.com/gif.latex?$\bigvee$"/><img src="https://latex.codecogs.com/gif.latex?$l'_{i}$"/> = <img src="https://latex.codecogs.com/gif.latex?$l'_{j}$"/>)]

 - avec Y : <img src="https://latex.codecogs.com/gif.latex?$$\binom{n}{2}$$"/>, le nombre de pair possibles et n le nombre de donnée d'une partition

*Exemple*

> S : [1,2,2,1,1]
>
> S' : [2,1,2,1,1]
> Nombre de pairs possible <img src="https://latex.codecogs.com/gif.latex?$$\binom{5}{2}$$"/> : 10
>
> <img src="https://latex.codecogs.com/gif.latex?$S_{1,2}$"/>[1,2] : 1 **≠** 2
>
> <img src="https://latex.codecogs.com/gif.latex?$S'_{1,2}$"/>[2,1] : 2 **≠** 1
>
>  **donc <img src="https://latex.codecogs.com/gif.latex?$I$"/>(<img src="https://latex.codecogs.com/gif.latex?$l_{i}$"/> = <img src="https://latex.codecogs.com/gif.latex?$l_{j}$"/><img src="https://latex.codecogs.com/gif.latex?$\bigvee$"/><img src="https://latex.codecogs.com/gif.latex?$l'_{i}$"/> = <img src="https://latex.codecogs.com/gif.latex?$l'_{j}$"/>)**
>  
>  <img src="https://latex.codecogs.com/gif.latex?$S_{1,3}$"/>[1,2] : 1 **≠** 2 
>
>  <img src="https://latex.codecogs.com/gif.latex?$S'_{1,3}$"/>[2,2] : 2 **=** 1
>
>  **donc 0**
>  
>  etc
>  
>  <img src="https://latex.codecogs.com/gif.latex?$I$"/>(<img src="https://latex.codecogs.com/gif.latex?$l_{i}$"/> = <img src="https://latex.codecogs.com/gif.latex?$l_{j}$"/><img src="https://latex.codecogs.com/gif.latex?$\bigvee$"/><img src="https://latex.codecogs.com/gif.latex?$l'_{i}$"/> = <img src="https://latex.codecogs.com/gif.latex?$l'_{j}$"/>) = 3
> 
>  <img src="https://latex.codecogs.com/gif.latex?$I$"/>(<img src="https://latex.codecogs.com/gif.latex?$l_{i}$"/> = <img src="https://latex.codecogs.com/gif.latex?$l_{j}$"/><img src="https://latex.codecogs.com/gif.latex?$\bigwedge$"/><img src="https://latex.codecogs.com/gif.latex?$l'_{i}$"/> = <img src="https://latex.codecogs.com/gif.latex?$l'_{j}$"/>) = 1 
>
>  donc
>
> <img src="https://latex.codecogs.com/gif.latex?\frac{1}{10}"/>*4 = 0.4
>
>  Le RandIndex est de 0.4

## Eclairage Lightpack sur Image

Le Lightpack est un procédé permettant de diffuser la lumière de la télévision vià des LEDS s'affichant sur le mur. C'est-à-dire une expension des couleurs dominantes. Cela a pour but :

-une meilleure immersion dans le film affiché. Dans le noir en particulier, il crée une atmosphère qui vous plonge encore davantage dans le film d’horreur, d’action ou le drame que vous êtes en train de visionner.

-l’image « sort » du cadre de l’écran, donnant l’impression d’une image libérée de ses contraintes et de ses bords. On a l’impression d’avoir une image plus grande.

-enfin, le halo lumineux adoucit l’image, en réduisant l’agressivité, et partant de là, la fatigue oculaire. Il réduit le contraste entre l’image du téléviseur et le mur qui supporte l’écran.

[exemple de Lightpack sur le marché](https://lh6.googleusercontent.com/-KkaS0cBMGAY/UWwV-DrYsYI/AAAAAAAAMew/YyF7ujFh0WI/s1338/IMG_0114.jpg)

![N|Solid](https://zupimages.net/up/19/17/yvbj.png)
>Lightpack sur trois images différentes en utilisant le **k-means Clustering** avec une segmentation <img src="https://latex.codecogs.com/gif.latex?$S$"/>=4

Chaque image est décomposée en plusieurs segments <img src="https://latex.codecogs.com/gif.latex?$S$"/> appelés blocs <img src="https://latex.codecogs.com/gif.latex?$W$"/> de dimensions équivalentes.
Un Cluster est associé par bloc et récupère la couleur <img src="https://latex.codecogs.com/gif.latex?$C$"/>{r,g,b} moyenne.

Les Blocs du haut (Bloc A) contient les blocs quand <img src="https://latex.codecogs.com/gif.latex?$W$"/> < <img src="https://latex.codecogs.com/gif.latex?$S$"/>

Les Blocs du bas (Bloc D) contient les blocs quand <img src="https://latex.codecogs.com/gif.latex?$W$"/> > (<img src="https://latex.codecogs.com/gif.latex?$S$"/>*<img src="https://latex.codecogs.com/gif.latex?$S$"/>)-<img src="https://latex.codecogs.com/gif.latex?$S$"/>

Les Blocs de gauche (Bloc Gauche) contient les blocs quand <img src="https://latex.codecogs.com/gif.latex?$W$"/><img src="https://latex.codecogs.com/gif.latex?$\bmod$"/><img src="https://latex.codecogs.com/gif.latex?$S$"/> = 1

Les Blocs de droite (Bloc Droite) contient les blocs quand <img src="https://latex.codecogs.com/gif.latex?$W$"/><img src="https://latex.codecogs.com/gif.latex?$\bmod$"/><img src="https://latex.codecogs.com/gif.latex?$S$"/> = 0

Il faut ajouter dans le main 

    		AlgorithmeEclairage algo1 = new AlgorithmeEclairage();
		String image = "src/main/resources/colorwheel-256x256.png";
		algo1.initializeManually(image, int S);
		
avec int <img src="https://latex.codecogs.com/gif.latex?$S$"/>, le nombre de Segmentation souhaité **{** <img src="https://latex.codecogs.com/gif.latex?$S$"/> ∈ ![\mathbb {N} ](https://wikimedia.org/api/rest_v1/media/math/render/svg/fdf9a96b565ea202d0f4322e9195613fb26a9bed) | 2 < <img src="https://latex.codecogs.com/gif.latex?$S$"/> < 16 **}** = [2,16] 

*Exemple* 

>AlgorithmeEclairage algo1 = new AlgorithmeEclairage();
>
>String image = "src/main/resources/colorwheel-256x256.png";
>
>algo1.initializeManually(image, 4);
>

## Eclairage Lightpack sur Vidéo
