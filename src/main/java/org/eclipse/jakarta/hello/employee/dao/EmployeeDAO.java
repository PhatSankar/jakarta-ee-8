package org.eclipse.jakarta.hello.employee.dao;

import org.eclipse.jakarta.hello.assignment.entity.Assignment;
import org.eclipse.jakarta.hello.base.dao.BaseDAO;
import org.eclipse.jakarta.hello.department.entity.Department;
import org.eclipse.jakarta.hello.employee.dto.EmployeeDTO;
import org.eclipse.jakarta.hello.employee.entity.Employee;
import org.eclipse.jakarta.hello.project.entity.Project;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class EmployeeDAO extends BaseDAO<Employee> {
    public EmployeeDAO() {
        super(Employee.class);
    }

    public List<Employee> getListEmployeeFromDepartment(Long deptId) {
        Query query = em.createQuery("SELECT e FROM Employee e WHERE e.department.id = :deptId")
                .setParameter("deptId", deptId);
        return query.getResultList();
    }

    public List<Employee> getListEmployeeNotInProject() {
        return em.createNamedQuery("Employee.getListNotInAnyProject", Employee.class).getResultList();
    }

    public List<Employee> getListEmployeeWorkInProjectDifferentDepartment() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);

        Root<Assignment> assignmentRoot = cq.from(Assignment.class);
        Join<Assignment, Employee> employeeJoin = assignmentRoot.join("employee");
        Join<Assignment, Project> projectJoin = assignmentRoot.join("project");
        cq.select(employeeJoin).where(cb.notEqual(employeeJoin.get("department"), projectJoin.get("managedDepartment")));
        return em.createQuery(cq).getResultList();
    }


}
