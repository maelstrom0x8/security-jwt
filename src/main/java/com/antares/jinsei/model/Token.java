package com.antares.jinsei.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "tokens")
public class Token {

	@Id
	@Column(length = 100)
	private String id;

	@JoinColumn(name = "user_id", nullable = false, updatable = false)
	@ManyToOne(optional = false)
	private User user;

	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime expiry;

	@ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "token_attributes", joinColumns = @JoinColumn(name = "token_id"))
    @MapKeyColumn(name = "attribute_name")
    @Column(name = "attribute_value")
    private Map<String, String> attributes = new HashMap<>();

	public Token() {}

	public Token(String id, User user, LocalDateTime expiry, Map<String, String> attributes) {
		this.id = id;
		this.user = user;
		this.expiry = expiry;
		this.attributes = attributes;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getExpiry() {
		return expiry;
	}

	public void setExpiry(LocalDateTime expiry) {
		this.expiry = expiry;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public String getAttribute(String name) {
		return attributes.get(name);
	}

	public void setAttribute(String name, String value) {
		attributes.put(name, value);
	}

}
