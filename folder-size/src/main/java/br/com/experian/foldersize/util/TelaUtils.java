package br.com.experian.foldersize.util;

import org.apache.commons.lang3.StringUtils;

public class TelaUtils {
	
	public static int LINE_LENGHT = 70;
	public static String MARGIN = "\t\t";
	
	public static String writeLineSeparator( Integer... lineLength ) {
		
		int auxLength = getLineLength(lineLength);
		
		return StringUtils.repeat("*", auxLength);
	}

	private static int getLineLength(Integer... lineLength) {
		
		int auxLength = LINE_LENGHT;
		
		if ( lineLength.length > 0 ) {
			auxLength = lineLength[ 0 ];
		}
		
		return auxLength;
	}
	
	public static String writeLine(String msg, Integer... lineLength ) {
		
		String auxMsg = StringUtils.stripEnd(msg, " ");
		
		String start = "* ";
		
		auxMsg = start + auxMsg;
		
		int auxLength = getLineLength(lineLength);
		
		int qtdSpaces = auxLength -auxMsg.length() -2;	// 2 chars ' *'
		
		String spaces = StringUtils.repeat( " ", qtdSpaces);
		
		return auxMsg + spaces + " *";
	}
	
	public static void writeToScreen( Object o ) {
		System.out.println( MARGIN + o.toString() );
	}
	
	public static void writeToScreen( Object o, Boolean... breakLine ) {
		
		if ( breakLine[0] ) {
			writeToScreen( o.toString() );
		} else {
			System.out.print( MARGIN + o.toString() );
		}
	}

}
