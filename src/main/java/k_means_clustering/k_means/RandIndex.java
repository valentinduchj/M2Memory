package k_means_clustering.k_means;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/* 
 * Allows you to calculate the RI ( Rand Index)
 */

public class RandIndex {

	List<Integer> list = new ArrayList<Integer>();
	List<Integer> list2 = new ArrayList<Integer>();

	public void IntializeLists() {
		System.out.println("Initialisation de deux listes");
		for (int i = 0; i < 34; i++) {
			Random r = new Random();
			Random r2 = new Random();
			int valeur = 0 + r.nextInt(34 - 0);
			int valeur2 = 0 + r2.nextInt(34 - 0);
			list.add(valeur);
			list2.add(valeur2);
		}

		GeneratePairValues(list, list2);
	}

	public void GeneratePairValues(List<Integer> list, List<Integer> list2) {
		System.out.println("Initialisation des paires");
		float resultpair = 0;
		for (int i = 1; i < list.size() + 1; i++) {
			resultpair += (i - 1);
			System.out.println(i + "=" + resultpair);
		}
		System.out.println("Nombre de paires possibles: " + resultpair);
		float a = 0;
		float b = 0;
		float c = 0;
		float d = 0;
		for (int i = 0; i < list.size() - 1; i++) {
			for (int w = i + 1; w < list.size(); w++) {
				//System.out.println("liste une " + list.get(i) + ":" + list.get(w));
				//System.out.println("liste deux " + list2.get(i) + ":" + list2.get(w));
				if (list.get(i) == list.get(w) && list2.get(i) == list2.get(w)) {
					a += 1;
				}
				if (list.get(i) != list.get(w) && list2.get(i) == list2.get(w)) {
					b += 1;
				}
				if (list.get(i) == list.get(w) && list2.get(i) != list2.get(w)) {
					c += 1;
				}
				if (list.get(i) != list.get(w) && list2.get(i) != list2.get(w)) {
					d += 1;
				}
			}
		}
		float ri = (a+d)/(resultpair);
		System.out.println("RI " + ri);
	}
}
