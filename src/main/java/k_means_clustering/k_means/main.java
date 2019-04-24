package k_means_clustering.k_means;

import java.io.IOException;


public class main {

	public static void main(String[] args) throws IOException {

		
		AlgorithmeClustering algo1 = new AlgorithmeClustering();
		String image = "src/main/resources/google-chrome-300x300.png";
		algo1.initializeManually(image, 100, 300, 20); // String, nombre de Clusters, nombre d'itérations, tolérance
		//RandIndex algo1 = new RandIndex();
		//algo1.IntializeLists();
		
	}
}