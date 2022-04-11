package br.com.experian.foldersize.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public class DiretorioUtils {
	
	public static double TO_MB = 1024d * 1024d;
	
	private static DecimalFormat df = new DecimalFormat("###,###.##");

	public static Set<String> readUserInputs() {
		
		Set<String> listaDiretorios = new HashSet<>();
		
		TelaUtils.writeToScreen( "Forneça os diretórios para análise: ", Boolean.FALSE );
		try ( BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in)) ){
			
			String read = buffReader.readLine();	
			
			List<String> auxFolders = Arrays.asList( StringUtils.splitByWholeSeparator(read, "\" \"") );
			
			listaDiretorios = auxFolders.stream()
										.map( i -> i.replace("\"", "") )
										.map( i -> StringUtils.strip( i ) )
										.collect( Collectors.toSet() );

		} catch (IOException e) {
			System.err.println("Ocorreu um erro ao ler os inputs do usuário " + e.getMessage() );
		}
		
		return listaDiretorios;
	}
	
	public static Set<String> validFolders( Set<String> lista ){
		return lista.stream().filter( f-> validateFolder( f ) ).collect(Collectors.toSet() );
	}
	
	public static Map<String, Double> checkFolderSize( Set<String> lista ){
		
		Map<String, Double> result = new HashMap<>();
		
		lista.forEach( f -> {
			
			TelaUtils.writeToScreen("Analisando o diretório: " + f );
			
			File file = new File( f );
			
			long sizeOfDirectory = FileUtils.sizeOfDirectory( file );
			
			double folderSize = sizeOfDirectory / TO_MB;
			
			result.put(f, folderSize);
		});
		
		return result;
	}
	

	public static void writeResults(Map<String, Double> checkFolderSize) {
		
		TelaUtils.writeToScreen("");
		TelaUtils.writeToScreen( TelaUtils.writeLineSeparator() );

		checkFolderSize.forEach( (k, v) -> {
			
			String auxStartMsg = k;
			
			String auxEndMsg = df.format( v ) +"MB " + "( " + v + " )";
					
			String start = "* ";
			
			String auxTofill = start + auxStartMsg + auxEndMsg;
			int lengthToFill = TelaUtils.LINE_LENGHT -auxTofill.length() -2;
			
			String spaces = StringUtils.repeat(" ", lengthToFill);
			
			String msg = start + auxStartMsg + spaces + auxEndMsg + " *";
			
			
			TelaUtils.writeToScreen( msg );
		});
		TelaUtils.writeToScreen( TelaUtils.writeLineSeparator() );
	}
	

	public static boolean validateFolder( String path ) {
		File file = new File( path );
		
		return file.isDirectory();
	}

}
