package com.github.pireba.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * A collection of File I/O utility functions.
 * 
 * @author Phillip Remmert
 */
public class IOUtils {
	
	private IOUtils() {}
	
	/**
	 * Creates a recursive list of files in a path.
	 * 
	 * @param path The path to be searched.
	 * @return The list of files.
	 */
	public static List<File> fileWalk(final File path) {
		List<File> files = new ArrayList<>();
		
		for ( File file : path.listFiles() ) {
			if ( file.isDirectory() ) {
				files.addAll(fileWalk(file));
			} else {
				files.add(file);
			}
		}
		
		return files;
	}
	
	/**
	 * Creates a recursive list of files in multiple paths.
	 * 
	 * @param path The paths to be searched.
	 * @return The list of files.
	 */
	public static List<File> fileWalk(final File... paths) {
		List<File> files = new ArrayList<>();
		
		for ( File file : paths ) {
			if ( file.isDirectory() ) {
				files.addAll(fileWalk(file));
			} else {
				files.add(file);
			}
		}
		
		return files;
	}
	
	/**
	 * Extracts the file extension (without the dot) from a given file.
	 * 
	 * @param file The file to extract the extension.
	 * @return The extracted file extension.
	 */
	public static String getFileExtension(final File file) {
		String fileName = file.getName();
		int index = fileName.lastIndexOf(".");
		
		if ( index == -1 ) {
			return "";
		}
		
		return fileName.substring(index+1);
	}
	
	/**
	 * Extracts the file name (without the dot) from a given file.
	 * 
	 * @param file The file to extract the file name.
	 * @return The extracted file name.
	 */
	public static String getFileName(final File file) {
		String fileName = file.getName();
		int index = fileName.lastIndexOf(".");
		
		if ( index == -1 ) {
			return "";
		}
		
		return fileName.substring(0, index);
	}
}