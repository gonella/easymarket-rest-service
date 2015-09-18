package com.easymarket.core;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "productcategory")
@NamedQueries({
		@NamedQuery(name = "com.easymarket.core.ProductCategory.findAll", query = "SELECT p FROM ProductCategory p") })
public class ProductCategory extends BaseResource implements IBaseResource {

	public ProductCategory() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Product)) {
			return false;
		}

		final Product that = (Product) o;

		return Objects.equals(this.getId(), that.getId()) && Objects.equals(this.getName(), that.getName())
				&& Objects.equals(this.getDescription(), that.getDescription());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName(), getDescription());
	}
}
