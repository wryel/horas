package br.com.wryel.horas.dao;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.lang.StringUtils;

import br.com.wryel.horas.entity.filter.EntityFilter;
import br.com.wryel.horas.exception.HorasRuntimeException;


/**
 * @author wryel @ fornax
 */
public final class DAOHelper {
	
	static {
		
	}
	
	/**
	 * 
	 * @param destino destino
	 * @param origem origem
	 */
	public static void copyProperties(final Object destino, final Object origem) {
		try {
			IntegerConverter integerConverter = new IntegerConverter(null);
			DateConverter dateConverter = new DateConverter(null);
			BeanUtilsBean beanUtilsBean = BeanUtilsBean.getInstance();
			beanUtilsBean.getConvertUtils().register(integerConverter, Integer.class);
			beanUtilsBean.getConvertUtils().register(dateConverter, Date.class);
			beanUtilsBean.copyProperties(destino, origem);
		} catch (Exception e) {
			throw new HorasRuntimeException("Erro ao copiar proriedades de beans " + destino +" e " + origem, e);
		}
	}
	
	/**
	 * 
	 * @param <Filter> filter
	 * @param filterClass filterClass
	 * @return Filter
	 */
	static <Filter extends EntityFilter<?>> Filter createFilter(final Class<Filter> filterClass) {
		Filter filter;
		try {
			filter = filterClass.newInstance();
		} catch (Exception e) {
			throw new HorasRuntimeException("Nao foi possivel instanciar filtro de pesquisa " + filterClass.getName(), e);
		}
		return filter;
	}
	
	/**
	 * 
	 * @param bean bean
	 * @return object
	 */
	public static Object getKey(final Object bean) {
		try {
			List<Field> fields = getField(bean, Id.class);
			if (fields.isEmpty()) {
				fields = getField(bean, EmbeddedId.class);
			}
			if (fields.isEmpty()) {
				throw new HorasRuntimeException("O bean nao tem chave configurada " + bean.getClass().getName());
			} else {
				Object result = getObjectProprety(bean, fields.get(0).getName());
				if (result instanceof Number && ((Number) result).doubleValue() == 0) {
					result = null;
				}
				return result;
			}
		} catch (Exception exception) {
			throw new HorasRuntimeException("Nao foi possivel recuperar a chave do objeto.", exception);
		}
	}
	
	/**
	 * 
	 * @param sql sql
	 * @return sql
	 */
	static String createCountSql(final String sql) {
		Pattern pattern = Pattern.compile("(SELECT) [^ ]+ (FROM.+)", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(sql);
		String countSql = null;
		if (matcher.matches()) {
		    countSql = matcher.group(1) + " COUNT(1) " + matcher.group(2);
		} else {
			throw new HorasRuntimeException("A sql informada nao obedece o padrao SELECT ? FROM ?...");
		}
		return countSql;
	}
	
	/**
	 * 
	 * @param bean bean
	 * @param propertyName propertyName
	 * @return value of propertyName
	 */
	private static Object getObjectProprety(final Object bean, final String propertyName) {
		try {
			Method method = findGetter(bean, propertyName);
			Object value = null;
			if (method != null) {
				value = method.invoke(bean);
			}
			return value;
		} catch (Exception e) {
			throw new HorasRuntimeException("Nao achou a propriedade " + propertyName, e);
		}
	}
	
	/**
	 * 
	 * @param bean bean
	 * @param propertyName propertyName
	 * @return Method method
	 */
	private static Method findGetter(final Object bean, final String propertyName) {
		Class<? extends Object> beanClass = bean.getClass();
		return findGetter(beanClass, propertyName);
	}
	
	/**
	 * 
	 * @param beanClass beanClass
	 * @param propertyName propertyName
	 * @return method
	 */
	private static Method findGetter(final Class<? extends Object> beanClass, 
			final String propertyName) {
		Method method = null;
		String capitalized = StringUtils.capitalize(propertyName);
		for (Class<?> current = beanClass; !current.equals(Object.class) && method == null; current = current.getSuperclass()) {
			try {
				method = current.getMethod("get" + capitalized);
			} catch (Exception e) {
				// neste caso, a excecao nao deve ser propagrada
				e.printStackTrace();
			}
		}
		return method;
	}
	
	/**
	 * 
	 * @param bean bean
	 * @param annotation annotation
	 * @return List fields
	 */
	private static List<Field> getField(final Object bean, final Class<? extends Annotation> annotation) {
		List<Field> fields = new ArrayList<Field>();
		for (Class<?> klass = bean.getClass(); !klass.equals(Object.class); klass = klass.getSuperclass()) {
			try {
				for (Field current : klass.getDeclaredFields()) {
					if (current.getAnnotation(annotation) != null) {
						fields.add(current);
					}
				}
			} catch (SecurityException e) {
				throw new HorasRuntimeException("Nao foi possivel acessar o campo escolhido", e);
			}
		}
		return fields;
	} 
	
	/**
	 * 
	 * @param string string
	 * @return string
	 */
	public static String replaceAllSpecialCharacters(final String string) {
		return string.replaceAll("[^a-zA-Z0-9\\s%]", "_");
	}
	
	/**
	 * 
	 */
	private DAOHelper() {
		
	}
}
