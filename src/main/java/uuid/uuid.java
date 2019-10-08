package uuid;

import java.util.UUID;

public class uuid {
	public static void main(String[] args) {
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid.toString().replace("-", ""));
	}

}
