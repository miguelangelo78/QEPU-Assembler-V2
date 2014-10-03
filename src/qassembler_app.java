import com.core.qassembler.QAssembler;

public class qassembler_app {

	public static void main(String[] args) {
		try {
			new QAssembler("C:\\Users\\Miguel\\Desktop\\QASM_Libs\\main.qasm");
		} catch (Exception e) { e.printStackTrace(); }
	}
}