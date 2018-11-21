package sharkindream.gamecharacter;

public enum Type {

	None(0, "None", "#514f4f"),
	Fire(1, "Fire", "#ee6557"),
	Water(2, "Water", "#5b7ccc"),
	Leaf(3, "Leaf", "#5bcc80"),
	Light(4, "Light", "#f09613"),
	Dark(5, "Dark", "#150821");

	private int id;
	private String name;
	private String color;

	private Type(int id, String name, String color) {
		this.id = id;
		this.name = name;
		this.color = color;
	}

	public int getid() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getColor() {
		return color;
	}



	public static Type getClassbyID(int id) {
		for(Type type: values()) {
			if(type.getid() == id) return type;
		}
		return null;
	}

}
