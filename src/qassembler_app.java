import javax.swing.JOptionPane;

import com.core.qassembler.QAssembler;

public class qassembler_app {

	private static final String FILESOURCE_FORMAT=".qasm";
	
	public static void messagebox(String title,String msg,int message_type,boolean close){
		JOptionPane.showMessageDialog(null,msg,title,message_type);
		if(close) System.exit(1);
	}
	
	public static void main(String[] args) {
		try {
			if(args.length!=1) messagebox("Quantum Assembler Error","Usage: java -jar qassembler.jar \"C:\\....\\filename"+FILESOURCE_FORMAT+"\"",JOptionPane.ERROR_MESSAGE,true);
			if(!args[0].contains(FILESOURCE_FORMAT)) messagebox("Quantum Assembler Error","The file format is wrong. Use '"+FILESOURCE_FORMAT+"'",JOptionPane.ERROR_MESSAGE,true);
			new QAssembler(args[0]);
		} catch (Exception e) { e.printStackTrace(); }
	}
}