package com.mailgun.interceptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.form.FormProperty;

/**
 * <h3>
 * Implementation of the {@link RequestInterceptor}
 * </h3>
 * <p>
 * for a creating:
 * </p>
 * <ul>
 *     <li>
 *         {@link FormProperty} with allowed prefixes such as: {@code t:, o:, h:, v:} with the followed by any arbitrary value
 *     </li>
 *     <li>
 *         request header in format: {@code .addHeader(headerName, headerValue)}
 *     </li>
 * </ul>
 * @author Vitalii Chornobryvyi
 */
public class MailgunRequestInterceptor implements RequestInterceptor {
	private final Map<String, Collection<String>> headers;
	private final Map<String, Collection<String>> properties;

	private MailgunRequestInterceptor(Map<String, Collection<String>> headers, Map<String, Collection<String>> properties) {
		this.headers = headers;
		this.properties = properties;
	}

	public static Builder builder() {
		return new Builder();
	}

	@Override
	public void apply(RequestTemplate requestTemplate) {
		requestTemplate.headers(headers);
		requestTemplate.queries(properties);
	}

	public static class Builder {
		private final Map<String, Collection<String>> headers = new HashMap<>();
		private final Map<String, Collection<String>> properties = new HashMap<>();

		public Builder addHeader(String headerName, String headerValue) {
			headers.computeIfAbsent(headerName, key -> new ArrayList<>()).add(headerValue);
			return this;
		}

		public Builder addProperty(String propertyName, String propertyValue) {
			properties.computeIfAbsent(propertyName, key -> new ArrayList<>()).add(propertyValue);
			return this;
		}

		public MailgunRequestInterceptor build() {
			return new MailgunRequestInterceptor(headers, properties);
		}
	}
}
