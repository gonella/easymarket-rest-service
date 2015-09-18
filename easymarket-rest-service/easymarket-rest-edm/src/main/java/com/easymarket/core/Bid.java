package com.easymarket.core;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "bid")
@NamedQueries({
        @NamedQuery(
                name = "com.easymarket.core.Bid.findAll",
                query = "SELECT p FROM Bid p"
        )
})
public class Bid implements java.io.Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="name", nullable = false)
	private String name;

	public Bid() {
	}

	public Bid(String name) {
		this.setName(name);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return getName();
	}

	public void setFullName(String fullName) {
		this.setName(fullName);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Bid)) {
			return false;
		}

		final Bid that = (Bid) o;

		return Objects.equals(this.id, that.id)
				&& Objects.equals(this.getName(), that.getName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, getName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
