package be.vdab.valueobjects;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;


//variabelen
	@Entity
	@Table(name = "productlines")
	public class Productline implements Serializable {
		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;

		private String name;
		private String description;
		
		@Version
		private long version;
		
		
		// constructor
		protected Productline() {

		}

		public Productline(String name, String description) throws IllegalArgumentException {
			setName(name);
			setDescription(description);
		}

		// getters
		public long getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getDescription() {
			return description;
		}

		private void setName(String name)  {
			this.name = name;
		}

		private void setDescription(String description) {
			this.description = description; 
		}

		
		
		
		
		// hash & equals
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!(obj instanceof Productline))
				return false;
			Productline other = (Productline) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}

		//tostring
		@Override
		public String toString() {
			return "Productline [id=" + id + ", name=" + name + ", description=" + description + "]";
		}
		

	}

