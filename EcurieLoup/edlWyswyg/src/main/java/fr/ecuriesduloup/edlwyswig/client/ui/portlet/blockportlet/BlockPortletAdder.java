package fr.ecuriesduloup.edlwyswig.client.ui.portlet.blockportlet;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;

import fr.ecuriesduloup.edlwyswig.client.ui.portlet.Portlet;
import fr.ecuriesduloup.edlwyswig.client.ui.portlet.PortletAdder;
import fr.ecuriesduloup.edlwyswig.client.ui.portlet.textportlet.TextPortlet;

public class BlockPortletAdder extends PortletAdder{

	@Override
	public Portlet createPorlet() {
		return new TextPortlet();
	}

	@Override
	protected  Image getImage() {
		ImageResource img = portletResources.textPortletAdder();
		return new Image(img);
	}

}
