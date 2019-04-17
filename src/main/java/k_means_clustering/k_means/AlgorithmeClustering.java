package k_means_clustering.k_means;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AlgorithmeClustering {

	List<Pixel> listpixel = new ArrayList<Pixel>();
	List<Cluster> listcluster = new ArrayList<Cluster>();

	public void initializeManually(String lien, int numberCluster, int numberInterator, int tolerance) throws IOException {
		
		getRGBValue(lien);
		generateCluster(numberCluster);
		iteratorCluster(numberInterator);
		setRGBValue(lien, tolerance);
		
	}
	public void getRGBValue(String lien) throws IOException {

		BufferedImage bi = ImageIO.read(new File(lien));
		for (int x = 0; x < bi.getWidth(); x++) {
			for (int y = 0; y < bi.getHeight(); y++) {
				int pixel = bi.getRGB(x, y);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				System.out.println(
						"pixel en [" + x + ":" + y + "]   red: " + red + ", green: " + green + ", blue: " + blue);
				Pixel pixel2 = new Pixel(x, y, red, green, blue);
				listpixel.add(pixel2);

			}
		}

	}

	public void generateCluster(int numberCluster) {

		// Number clusters
		int numbercluster = numberCluster;
		System.out.println(numbercluster + " clusters souhait�s.");

		Random r = new Random();
		for (int i = 0; i < numbercluster; i++) {
			float posx = 1 + r.nextInt(20 - 1);
			float posy = 1 + r.nextInt(6 - 1);
			Cluster cluster1 = new Cluster(i, posx, posy, 122, 122, 122);
			listcluster.add(cluster1);
			System.out.println("[" + cluster1.getPosX() + " : " + cluster1.getPosY() + "]");
		}
	}

	public void iteratorCluster(int numberInterator) {
		int stop = 0;
		while (stop < numberInterator) {
			for (Cluster p : listcluster) {
				p.viderquestion();
			}

			for (Pixel p : listpixel) {
				LinkedList<Cluster> tmp = new LinkedList<Cluster>();
				float result2 = 10000;

				for (Cluster c : listcluster) {
					float result = Math.abs((p.getPosX() - c.getPosX())) + Math.abs((p.getPosY() - c.getPosY()))
							+ Math.abs((p.getRed() - c.getRed())) + Math.abs((p.getGreen() - c.getGreen()))
							+ Math.abs((p.getBlue() - c.getBlue()));
					if (result2 > result) {
						result2 = result;
						tmp.add(c);
					}
				}
				for (Cluster c : listcluster) {
					if (tmp.getLast().getId() == c.getId()) {
						c.ajouterquestion(p);
					}
				}
			}
			// CALCUL CLUSTER
			for (Cluster c : listcluster) {
				float sumPosX = 0;
				float sumPosY = 0;
				int sumRed = 0;
				int sumGreen = 0;
				int sumBlue = 0;

				for (Pixel p : c.getListpixel()) {
					sumPosX += p.getPosX();
					sumPosY += p.getPosY();
					sumRed += p.getRed();
					sumGreen += p.getGreen();
					sumBlue += p.getBlue();

				}
				if (c.getListpixel().size() != 0) {
					float tmpsumPosX = sumPosX / c.getListpixel().size();
					float tmpsumPosY = sumPosY / c.getListpixel().size();
					int tmpsumRed = sumRed / c.getListpixel().size();
					int tmpsumGreen = sumGreen / c.getListpixel().size();
					int tmpsumBlue = sumBlue / c.getListpixel().size();
					c.setPosX(tmpsumPosX);
					c.setPosY(tmpsumPosY);
					c.setRed(tmpsumRed);
					c.setGreen(tmpsumGreen);
					c.setBlue(tmpsumBlue);

				}

				System.out.println("Cluster n° " + c.getId() + " est en {" + c.getPosX() + ", " + c.getPosY()
						+ "} et contient " + c.getListpixel().size() + " pixels. Son RGB est de [" + c.getRed() + ","
						+ c.getGreen() + "," + c.getBlue() + "].");
			}

			System.out.println("ITERATOR " + stop);
			System.out.println("---------------------");
			stop++;

		}

		// SUPPRIMER LES CLUSTERS AVEC 0 PIXEL
		Iterator<Cluster> it = listcluster.iterator();
		while (it.hasNext()) {
			if (it.next().getListpixel().isEmpty()) {
				it.remove();
			}
		}

	}

	public void setRGBValue(String lien, int tolerance) throws IOException {
		BufferedImage bi = ImageIO.read(new File(lien));
		BufferedImage bi2 = ImageIO.read(new File(lien));

		for (Cluster c : listcluster) {
			for (Pixel p : c.getListpixel()) {
				p.setRed(c.getRed());
				p.setBlue(c.getBlue());
				p.setGreen(c.getGreen());
				// System.out.println(p.getPosX() + ":" + p.getPosY());
				bi2.setRGB(p.getPosX(), p.getPosY(), new Color(p.getRed(), p.getGreen(), p.getBlue()).getRGB());

			}
		}
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(new FlowLayout());
		frame.getContentPane().add(new JLabel(new ImageIcon(bi2)));
		frame.pack();
		frame.setVisible(true);
		MeasureQuality(bi, bi2, tolerance);
	}

	public void MeasureQuality(BufferedImage bi, BufferedImage bi2, int tolerance) {
		System.out.println("Mesure du taux d'erreur...");
		float result = 0;
		for (int x = 0; x < bi.getWidth(); x++) {
			for (int y = 0; y < bi.getHeight(); y++) {
				int pixel = bi.getRGB(x, y);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				Pixel pixel2 = new Pixel(x, y, red, green, blue);

				int pixel3 = bi2.getRGB(x, y);
				int red2 = (pixel3 >> 16) & 0xff;
				int green2 = (pixel3 >> 8) & 0xff;
				int blue2 = (pixel3) & 0xff;
				Pixel pixel4 = new Pixel(x, y, red2, green2, blue2);

				 float diff = ((Math.abs((pixel2.getRed() - pixel4.getRed()))
						+ Math.abs((pixel2.getGreen() - pixel4.getGreen()))
						+ Math.abs((pixel2.getBlue() - pixel4.getBlue()))));
				 //Tolérence
				 if(diff > tolerance) {
					 result += diff;
				 }
			}
		}
		
		float maximumErreur = (255*bi.getHeight()*bi.getWidth())/2;
		System.out.println(bi.getHeight()*bi.getWidth() + " pixels");
		System.out.println("donc " + 255*3*bi.getHeight()*bi.getWidth() + " erreurs possibles");
		System.out.println("Nombre d'erreurs " + result);
		System.out.println((result*100)/maximumErreur + " %");
	}

}
