package br.com.wryel.horas.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import br.com.wryel.horas.controller.AbstractController;
import br.com.wryel.horas.exception.HorasRuntimeException;

public final class FacesUtil {
	
	private static final FacesUtil INSTANCE = new FacesUtil();
	
	public static final FacesUtil getInstance() {
		return INSTANCE;
	}
	
	private FacesUtil() {
		
	}
	
	public String getLastViewId() {
		String lastViewId = getFacesContext().getViewRoot().getViewId();
		return lastViewId;
	}

	public Flash getFlashScope() {
		Flash flash = getFacesContext().getExternalContext().getFlash();
		return flash;
	}

	public void showInfo(String key) {
		String info = getResourceBundleMessages().getString(key);
		FacesMessage facesMessage = new FacesMessage(info);
		facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
		getFacesContext().addMessage(null, facesMessage);
	}
	
	public void showError(String key) {
		String info = getResourceBundleMessages().getString(key);
		FacesMessage facesMessage = new FacesMessage(info);
		facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		getFacesContext().addMessage(null, facesMessage);
	}
	
	public void showBusinessError(String error) {
		FacesMessage facesMessage = new FacesMessage(error);
		facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		getFacesContext().addMessage(null, facesMessage);
	}
	
	public HttpSession getSession() {
		HttpSession httpSession = getSession(true);
		return httpSession;
	}
	
	public HttpSession getSession(boolean create) {
		HttpSession httpSession = (HttpSession) getFacesContext().getExternalContext().getSession(create);
		return httpSession;
	}

//	private ResourceBundle getResourceBundle() {
//		ResourceBundle resourceBundle = getFacesContext().getApplication().getResourceBundle(getFacesContext(), "msg");
//		return resourceBundle;
//	}
	
	private ResourceBundle getResourceBundleMessages() {
		ResourceBundle resourceBundle = getFacesContext().getApplication().getResourceBundle(getFacesContext(), "messages");
		return resourceBundle;
	}
	
	private FacesContext getFacesContext() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return facesContext;
	}
	
	public <Controller extends AbstractController<?>> Controller getController(Class<Controller> controllerClass) {
		FacesContext facesContext = getFacesContext();
		String controllerName = StringUtils.uncapitalize(controllerClass.getSimpleName());
		Controller controller = facesContext.getApplication().evaluateExpressionGet(facesContext, "#{" + controllerName + "}", controllerClass);
		return controller;
	}
	
	public List<SelectItem> createSelectItem(Collection<?> items, String labelProperty) {
		List<SelectItem> selectItems = new ArrayList<>();
		Class<?> klass = null;
		Method method = null;
		for (Object object : items) {
			try {
				if (klass == null) {
					klass = object.getClass();
					method = klass.getDeclaredMethod("get" + StringUtils.capitalize(labelProperty), new Class[0]);				
				}				
				SelectItem selectItem = new SelectItem(object, method.invoke(object, new Object[0]).toString());	
				selectItems.add(selectItem);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new HorasRuntimeException("Erro ao tentar obter metodo", e);
			}
		}
		return selectItems;
	}
}
