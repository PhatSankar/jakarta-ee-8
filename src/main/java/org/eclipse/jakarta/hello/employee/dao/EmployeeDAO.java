package org.eclipse.jakarta.hello.employee.dao;

import org.eclipse.jakarta.hello.assignment.entity.Assignment;
import org.eclipse.jakarta.hello.base.dao.BaseDAO;
import org.eclipse.jakarta.hello.department.entity.Department;
import org.eclipse.jakarta.hello.employee.dto.EmployeeDTO;
import org.eclipse.jakarta.hello.employee.entity.Employee;
import org.eclipse.jakarta.hello.project.entity.Project;

import javax.ejb.Stateless;
import javax.persistence.EntityGraph;
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
        EntityGraph entityGraph = em.getEntityGraph("employee.department.entity.graph");
        Query query = em.createQuery("SELECT e FROM Employee e WHERE e.department.id = :deptId")
                .setParameter("deptId", deptId);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    public List<Employee> getListEmployeeNotInProject() {
        return em.createNamedQuery("Employee.getListNotInAnyProject", Employee.class)
                .setHint("javax.persistence.fetchgraph", em.getEntityGraph("employee.assignment.entity.graph"))
                .getResultList();
    }

    public List<Employee> getListEmployeeWorkInProjectDifferentDepartment() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        EntityGraph entityGraph = em.getEntityGraph("employee.assignment.entity.graph");


        Root<Employee> employeeRoot = cq.from(Employee.class);
        Join<Assignment, Employee> assignmentJoin = employeeRoot.join("assignments");
        Join<Assignment, Project> projectJoin = assignmentJoin.join("project");
        cq.select(employeeRoot).where(cb.notEqual(employeeRoot.get("department"),
                projectJoin.get("managedDepartment")));
        return em.createQuery(cq)
                .setHint("javax.persistence.fetchgraph", entityGraph)
                .getResultList();
    }


}
