package test.integration;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import test.jpa.Department;
import test.jpa.Employee;

/**
 * Test for {@link Department} searching.
 * @author aleskurlovich
 */
@RunWith(Parameterized.class)
public class DepartmentSearchTest {
	
	@Parameter
	public EntityManager em;
	
	/**
	 * Returns entity managers.
	 * @return entity managers
	 */
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{
						createOpenJPAEm()
				}
		});
	}
	
	/**
	 * Creates {@link EntityManager} using OpenJPA.
	 * @return {@link EntityManager} instance
	 */
	public static EntityManager createOpenJPAEm() {
		return Persistence.createEntityManagerFactory("openjpa").createEntityManager();
	}
	
	/**
	 * Initialization method for database.
	 */
	@Before
	public void prepareDatabase() {
		em.getTransaction().begin();
		Department d = new Department();
		d.setName("Department1");
		Employee e1 = new Employee();
		e1.setName("Name1");
		e1.setSurname("Surname1");
		e1.setDepartment(d);
		d.addEmployee(e1);
		Employee e2 = new Employee();
		e2.setName("Name2");
		e2.setSurname("Surname2");
		e2.setDepartment(d);
		d.addEmployee(e2);
		em.persist(d);
		em.getTransaction().commit();
	}
	
	/**
	 * Method for cleaning database.
	 */
	@After
	public void cleanDatabase() {
		em.getTransaction().begin();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Department> query = cb.createQuery(Department.class);
		Root<Department> from = query.from(Department.class);
		query.where(cb.equal(from.get("name"), 
							 "Department1"));
		Department d = em.createQuery(query).getSingleResult();
		em.remove(d);
		em.getTransaction().commit();
	}
	
	/**
	 * Testing duplication of {@link Department} objects
	 * when they are searched by something from
	 * objects which are related to {@link Department} object
	 * by {@link OneToMany} relation.
	 */
	@Test
	public void duplDepartSearch() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Long> eQuery = cb.createQuery(Long.class);
		Root<Employee> eFrom = eQuery.from(Employee.class);
		eQuery.where(cb.equal(eFrom.get("department")
								  	.get("name"),
							  "Department1"));
		eQuery.select(cb.count(eFrom));
		
		CriteriaQuery<Long> dQuery = cb.createQuery(Long.class);
		Root<Department> dFrom = dQuery.from(Department.class);
		dQuery.where(dFrom.get("employees")
						  .get("name")
						  .in("Name1", 
							  "Name2"));
		dQuery.select(cb.count(dFrom));
		
		Assert.assertEquals(em.createQuery(eQuery)
							  .getSingleResult(), 
							em.createQuery(dQuery)
							  .getSingleResult());
	}

}
