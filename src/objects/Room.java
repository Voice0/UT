package objects;

import java.util.Map;

import dao.RoomDAO;

public class Room extends BaseObject<RoomDAO>implements Printable {

	private String number;

	public Room(Map<String, Object> params) {
		super();
		setDao(new RoomDAO());
		this.number = (String) params.get(Constants.ROOMNUMBER);
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
