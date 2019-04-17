package k_means_clustering.k_means;

import java.io.IOException;


public class main {

	public static void main(String[] args) throws IOException {

		
		AlgorithmeClustering algo1 = new AlgorithmeClustering();
		String image = "src/main/resources/home-312x211.jpg";
		algo1.initializeManually(image, 100, 100, 20); // String, nombre de Clusters, nombre d'itérations, tolérance
		
	}
}