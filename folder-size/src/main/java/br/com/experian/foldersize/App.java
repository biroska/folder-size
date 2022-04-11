package br.com.experian.foldersize;

import java.util.Map;
import java.util.Set;

import br.com.experian.foldersize.util.DiretorioUtils;
import br.com.experian.foldersize.util.TelaUtils;

public class App {
	
	public static void main(String[] args) {
		
		TelaUtils.writeToScreen( TelaUtils.writeLineSeparator() );
		TelaUtils.writeToScreen( TelaUtils.writeLine("Quebra galho para identificar o tamanho dos diretorios") );
		TelaUtils.writeToScreen( TelaUtils.writeLine(" ") );
		TelaUtils.writeToScreen( TelaUtils.writeLine("Informe o caminho completo dos diretorios entre aspas") );
		TelaUtils.writeToScreen( TelaUtils.writeLine("Para informar varios diretorios, separe com espaco") );
		TelaUtils.writeToScreen( TelaUtils.writeLineSeparator() );
		
		TelaUtils.writeToScreen("");
		TelaUtils.writeToScreen("");
		
		Set<String> readUserInputs = DiretorioUtils.readUserInputs();
		
		Set<String> validFolders = DiretorioUtils.validFolders( readUserInputs );
		
		TelaUtils.writeToScreen("");
		TelaUtils.writeToScreen( "Diretórios que serão analisados: " );
		TelaUtils.writeToScreen( validFolders );
		TelaUtils.writeToScreen("");
		
		Map<String,Double> checkFolderSize = DiretorioUtils.checkFolderSize(validFolders);
		
		
		DiretorioUtils.writeResults(checkFolderSize);
	}
}
