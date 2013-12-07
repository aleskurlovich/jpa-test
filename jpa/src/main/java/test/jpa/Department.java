package test.jpa;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entity for Department.
 * @author aleskurlovich
 *
 */
@Entity
public class Department {
	
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
	 * Set of employees.
	 */
	@OneToMany(mappedBy = "departments")
	private Set<Employee> employee;

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
	 * Getter for set of employees.
	 * @return set of employees
	 */
	public Set<Employee> getEmployee() {
		return employee;
	}

	/**
	 * Setter for set of employees.
	 * @param employee set of employees
	 */
	public void setEmployee(Set<Employee> employee) {
		this.employee = employee;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Department other = (Department) obj;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
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
		return true;
	}

}
