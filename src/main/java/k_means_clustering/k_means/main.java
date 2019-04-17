package k_means_clustering.k_means;

import java.io.IOException;


public class main {

	public static void main(String[] args) throws IOException {

		
		AlgorithmeClustering algo1 = new AlgorithmeClustering();
		String image = "src/main/resources/red-circle-30x30-hi.png";
		algo1.getRGBValue(image); //Lien de l'image � traiter 
		algo1.generateCluster(80); //Nombre de clusters souhait�s
		algo1.iteratorCluster(100); //Nombre d'it�ration
		algo1.setRGBValue(image);
		
	}
}