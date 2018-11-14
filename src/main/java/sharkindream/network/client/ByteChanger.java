package sharkindream.network.client;

public class ByteChanger{

	public byte[] toStream(boolean flag) {
		byte[] f = {(byte)((flag) ? 1 : 0)};

		 return f;
	}

	public boolean toBool(int f) {
		boolean flag = (f == 1) ? true : false;

		return flag;
	}
}
