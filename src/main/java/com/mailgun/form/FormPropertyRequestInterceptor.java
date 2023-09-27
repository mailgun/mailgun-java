package com.mailgun.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.form.FormProperty;

/**
 * <p>
 * Implementation of the {@link RequestInterceptor} for a creating custom dynamic {@link FormProperty}
 * with allowed prefixes such as: {@code t:, o:, h:, v:} with the followed by any arbitrary value
 * </p>
 * @author Vitalii Chornobryvyi
 */
public class FormPropertyRequestInterceptor implements RequestInterceptor {
	private final Map<String, Collection<String>> properties;

	private FormPropertyRequestInterceptor(Map<String, Collection<String>> properties) {
		this.properties = properties;
	}

	public static Builder builder() {
		return new Builder();
	}

	@Override
	public void apply(RequestTemplate requestTemplate) {
		requestTemplate.queries(properties);
	}

	public static class Builder {
		private final Map<String, Collection<String>> properties = new HashMap<>();

		private Builder() {
		}

		public Builder addProperty(String propertyName, String propertyValue) {
			properties.computeIfAbsent(propertyName, key -> new ArrayList<>()).add(propertyValue);
			return this;
		}

		public FormPropertyRequestInterceptor build() {
			return new FormPropertyRequestInterceptor(properties);
		}
	}
}
