package fr.ecuriesduloup.secretflag.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface CssResources extends ClientBundle{
	
	 public static CssResources R = GWT.create(CssResources.class);

	 @Source("SecretFlag.css")
	 public SecretFlagCss SecretFlag();

}
