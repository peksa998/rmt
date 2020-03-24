package ludo;

public class Pawn {

	private String naziv;
	private int color;
	private int index;
	private int coordinatePawn_x;
	private int coordinatePawn_y;	
	

	public Pawn(String naziv, int color, int index, int coordinatePawn_x, int coordinatePawn_y) {
		super();
		this.naziv = naziv;
		this.color = color;
		this.index = index;
		this.coordinatePawn_x = coordinatePawn_x;
		this.coordinatePawn_y = coordinatePawn_y;
	}
	

	public int getCoordinatePawn_x() {
		return coordinatePawn_x;
	}


	public void setCoordinatePawn_x(int coordinatePawn_x) {
		this.coordinatePawn_x = coordinatePawn_x;
	}


	public int getCoordinatePawn_y() {
		return coordinatePawn_y;
	}


	public void setCoordinatePawn_y(int coordinatePawn_y) {
		this.coordinatePawn_y = coordinatePawn_y;
	}



	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
