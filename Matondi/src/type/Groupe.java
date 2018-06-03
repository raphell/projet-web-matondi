package type;

public class Groupe {
	int id;
	

	private String name ;
	private String style;
	private String mail;
	private String phone;
	private int nbrPeople;
	
	public Groupe(int id, String name, int nbrPeople, String style, String mail, String phone){
		setId(id);
		setName(name);
		setStyle(style);
		setMail(mail);
		setPhone(phone);
		setNbrPeople(nbrPeople);
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

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getNbrPeople() {
		return nbrPeople;
	}

	public void setNbrPeople(int nbrPeople) {
		this.nbrPeople = nbrPeople;
	}
}
