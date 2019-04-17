package k_means_clustering.k_means;

import java.util.ArrayList;
import java.util.List;

public class Cluster {

	private int id;
	private float posX;
	private float posY;
	private int red;
	private int green;
	private int blue;
	private List<Pixel> listpixel = new ArrayList<Pixel>();

	public Cluster(int id, float posX, float posY) {
		this.id = id;
		this.posX = posX;
		this.posY = posY;
	}
	
	public Cluster(int id, float posX, float posY, int red, int green, int blue) {
		this.id = id;
		this.posX = posX;
		this.posY = posY;
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getPosX() {
		return posX;
	}

	public void setPosX(float posX) {
		this.posX = posX;
	}

	public float getPosY() {
		return posY;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}

	public void ajouterquestion(Pixel p) {
		listpixel.add(p);
	}

	public void viderquestion() {
		listpixel.clear();
		;
	}

	public List<Pixel> getListpixel() {
		return listpixel;
	}

	public void setListpixel(List<Pixel> listpixel) {
		this.listpixel = listpixel;
	}

}