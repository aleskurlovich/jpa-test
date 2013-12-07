package test.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Entity of Employee.
 * @author aleskurlovich
 *
 */
@Entity
public class Employee {
	
	/**
	 * Id.
	 */
	@Id
	private Long id;
	
	/**
	 * Name.
	 */
	@Column
	private String name;
	
	/**
	 * Surname.
	 */
	@Column
	private String surname;
	
	/**
	 * Department.
	 */
	@ManyToOne
	private Department department;

	/**
	 * Getter for id.
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter for id.
	 * @param id id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter for name.
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for name.
	 * @param name name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for surname.
	 * @return surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Setter for surname.
	 * @param surname surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Getter for department.
	 * @return department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Setter for department.
	 * @param department department
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

}
