public class MoveToFront {
	public static void encode() {
		char[] ascii = new char[256];
		for (char i = 0; i < 256; i++) {
			ascii[i] = i;
		}
		while (!BinaryStdIn.isEmpty()) {
			char c = BinaryStdIn.readChar();
			char front;
			char back;
			char j;
			for (j = 0, back = ascii[0]; c != ascii[j]; j++) {
				front = ascii[j];
				ascii[j] = back;
				back = front;
			}
			ascii[j] = back;
			BinaryStdOut.write(j);
			ascii[0] = c;
		}
		BinaryStdOut.close();
	}
	public static void decode() {

		char[] ascii = new char[256];
		for (char i = 0; i < 256; i++) {
			ascii[i] = i;
		}
		while (!BinaryStdIn.isEmpty()) {
			char j = BinaryStdIn.readChar();
			BinaryStdOut.write(ascii[j]);
			char index = ascii[j];
			while (j > 0) {
				ascii[j] = ascii[--j];
			}
			ascii[0] = index;
		}
		BinaryStdOut.close();
	}
	public static void main(String[] args) {
		if (args[0].equals("-")) {
			encode();
		}
		else if (args[0].equals("+")) {
			decode();
		}
	}
}