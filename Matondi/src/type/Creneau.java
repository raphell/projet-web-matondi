package type;

public class Creneau {
	int idCreneau;
	String style;
	int hBegin;
	int mBegin;
	int hEnd;
	int mEnd;
	int idDay;
	int idBand;
	
	public Creneau(int id, String style, int idDay, int idBand, int hbegin, int mbegin,int hend, int mend){
		setIdCreneau(id);
		setStyle(style);
		setIdDay(idDay);
		setIdBand(idBand);
		sethBegin(hbegin);
		setmBegin(mbegin);
		sethEnd(hend);
		setmEnd(mend);
	}
	
	public int getIdBand() {
		return idBand;
	}

	public void setIdBand(int idBand) {
		this.idBand = idBand;
	}
	public int getIdCreneau() {
		return idCreneau;
	}
	public void setIdCreneau(int idCreneau) {
		this.idCreneau = idCreneau;
	}
	public int getIdDay() {
		return idDay;
	}
	public void setIdDay(int idDay) {
		this.idDay = idDay;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public int gethBegin() {
		return hBegin;
	}
	public void sethBegin(int hBegin) {
		this.hBegin = hBegin;
	}
	public int getmBegin() {
		return mBegin;
	}
	public void setmBegin(int mBegin) {
		this.mBegin = mBegin;
	}
	public int gethEnd() {
		return hEnd;
	}
	public void sethEnd(int hEnd) {
		this.hEnd = hEnd;
	}
	public int getmEnd() {
		return mEnd;
	}
	public void setmEnd(int mEnd) {
		this.mEnd = mEnd;
	}
	
}
