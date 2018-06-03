package type;

import java.sql.Date;

public class Jour {
	int idJour;
	int idEventJour;
	Date dateJour;
	
	
	public Jour(int idDay, int idEventDay, Date dateDay) {
		setIdJour(idDay);
		setIdEventJour(idEventJour);
		setDateJour(dateDay);
	}
	
	public int getIdJour() {
		return idJour;
	}
	public void setIdJour(int idJour) {
		this.idJour = idJour;
	}
	public int getIdEventJour() {
		return idEventJour;
	}
	public void setIdEventJour(int idEventJour) {
		this.idEventJour = idEventJour;
	}
	public Date getDateJour() {
		return dateJour;
	}
	public void setDateJour(Date dateJour) {
		this.dateJour = dateJour;
	}
	
	
}
