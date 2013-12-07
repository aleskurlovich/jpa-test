package test.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entity for Department.
 * @author aleskurlovich
 */
@Entity
public class Department {
	
	/**
	 * Id.
	 */
	@Id
	@GeneratedValue
	private Long id;
	
	/**
	 * Name.
	 */
	@Column(unique = true, nullable = false)
	private String name;
	
	/**
	 * Set of employees.
	 */
	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
	private Set<Employee> employees;

	/**
	 * Getter for id.
	 * @return id
	 */
	public Long getId() {
		return id;
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
	public Set<Employee> getEmployees() {
		return employees;
	}
	
	/**
	 * Adds employee.
	 * @param employee employee
	 */
	public void addEmployee(Employee employee) {
		if (getEmployees() == null) {
			employees = new HashSet<Employee>();
		}
		employees.add(employee);
	}
	
	/**
	 * Removes employee.
	 * @param employee employee
	 */
	public void removeEmployee(Employee employee) {
		if (getEmployees() != null) {
			employees.remove(employee);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getEmployees() == null) ? 0 : getEmployees().hashCode());
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
		if (getEmployees() == null) {
			if (other.getEmployees() != null)
				return false;
		} else if (!getEmployees().equals(other.getEmployees()))
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
