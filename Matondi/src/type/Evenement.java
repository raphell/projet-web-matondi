package type;

import java.sql.Date;

public class Evenement {
	
	private int id;
	private String name ;
	private Date date;
	private String city;
	private String street;
	private int nbrStreet;
	private int nbrDay;
	
	public Evenement(int id, String name, Date date, String city, String street, int nbrStreet, int nbrDay){
		setId(id);
		setName(name);
		setDate(date);
		setCity(city);
		setStreet(street);
		setNbrStreet(nbrStreet);
		setNbrDay(nbrDay);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNbrStreet() {
		return nbrStreet;
	}

	public void setNbrStreet(int nbrStreet) {
		this.nbrStreet = nbrStreet;
	}

	public int getNbrDay() {
		return nbrDay;
	}

	public void setNbrDay(int nbrDay) {
		this.nbrDay = nbrDay;
	}
}
