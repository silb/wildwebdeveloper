package org.eclipse.wildwebdeveloper.angular;

import java.util.Map;

import org.eclipse.lsp4e.LanguageClientImpl;
import org.eclipse.lsp4j.MessageParams;
import org.eclipse.lsp4j.MessageType;
import org.eclipse.lsp4j.ProgressParams;
import org.eclipse.lsp4j.WorkDoneProgressNotification;
import org.eclipse.lsp4j.WorkDoneProgressReport;
import org.eclipse.lsp4j.jsonrpc.messages.Either;

import com.google.gson.JsonObject;

public class AngularClientImpl extends LanguageClientImpl implements AngularLanguageServerExtention {

	@Override
	public void projectLoadingFinish(Object object) {
		// TODO should this set some state because only now stuff will work like hover..
		// or maybe even after projectLanguageService "enabled" call
		logMessage(new MessageParams(MessageType.Info, "Angular project loading finished"));
	}
	
	@Override
	public void projectLoadingStart(Object object) {
		logMessage(new MessageParams(MessageType.Info, "Angular project loading started"));
	}
	
	@Override
	public void projectLanguageService(Map<String,Object> data) {
		logMessage(new MessageParams(MessageType.Info, "Language Service is " + (((Boolean)data.get("languageServiceEnabled")).booleanValue()?"":"not yet ") + "enabled for project " + data.get("projectName")));
	}

	@Override
	public void suggestIvyLanguageServiceMode(Object o) {
		logMessage(new MessageParams(MessageType.Info, o.toString()));
	}

	@Override
	public void suggestStrictMode(Object o) {
		// this only says to the developer that they should enabled something in there tsconfig file (strictTemplates: true)
		// can't do any from there (like  suggestIvyLanguageServiceMode has to do)
		logMessage(new MessageParams(MessageType.Info, o.toString()));
	}
	
	@Override
	public void ngccProgressEnd(Object o) {
		logMessage(new MessageParams(MessageType.Info, o.toString()));
	}
	
	@Override
	public void notifyProgress(ProgressParams params) {
		String message = null;
		Either<WorkDoneProgressNotification, Object> either = params.getValue();
		if (either.isLeft()) {
			if (either.getLeft() instanceof WorkDoneProgressReport report)
				message = report.getMessage();
		}
		else if (either.getRight() instanceof JsonObject json)
		{
			if (json.has("message")) {
				message = json.get("message").getAsString();
			}
		}
		if (message != null) {
			logMessage(new MessageParams(MessageType.Info, params.getToken().getLeft()  + ": " + (message == null? "done":message)));
		}
	}

}
