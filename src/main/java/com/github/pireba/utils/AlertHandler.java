package com.github.pireba.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class AlertHandler extends Handler {
	
	@Override
	public void publish(LogRecord record) {
		if ( ! this.isLoggable(record) ) {
			return;
		}
		
		Alert alert = this.getAlert(record);
		alert.showAndWait();
		this.flush();
	}
	
	private Alert getAlert(LogRecord record) {
		Alert alert = new Alert(AlertType.ERROR);
		
		String[] messageSplit = record.getMessage().split("\n", 2);
		String header = messageSplit.length > 0 ? messageSplit[0] : null;
		String content = messageSplit.length > 1 ? messageSplit[1] : null;
		alert.setHeaderText(header);
		alert.setContentText(content);
		
		if ( record.getThrown() == null ) {
			return alert;
		}
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		record.getThrown().printStackTrace(pw);
		String stackTrace = sw.toString();
		
		TextArea textArea = new TextArea(stackTrace);
		textArea.setEditable(false);
		textArea.setWrapText(true);
		VBox.setVgrow(textArea, Priority.ALWAYS);
		
		VBox vbox = new VBox();
		vbox.getChildren().add(textArea);
		alert.getDialogPane().setExpandableContent(vbox);
		
		return alert;
	}
	
	@Override
	public void flush() {
		
	}
	
	@Override
	public void close() {
		
	}
}