package org.eclipse.jakarta.hello.project.dao;

import org.eclipse.jakarta.hello.assignment.entity.Assignment;
import org.eclipse.jakarta.hello.base.dao.BaseDAO;
import org.eclipse.jakarta.hello.employee.entity.Employee;
import org.eclipse.jakarta.hello.project.entity.Project;

import javax.ejb.Stateless;
import javax.persistence.EntityGraph;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class ProjectDAO extends BaseDAO<Project> {
    public ProjectDAO() {
        super(Project.class);
    }

    public List<Project> getListProjectByEmployeeId(Long employeeId) {
        EntityGraph entityGraph = em.getEntityGraph("project.assignment.entity.graph");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        //CriteriaQuery use type of what entity to return
        CriteriaQuery<Project> cq = cb.createQuery(Project.class);

        Root<Project> projectRoot = cq.from(Project.class);
        Join<Project, Assignment> projectAssignmentJoin = projectRoot.join("assignments");
        Join<Assignment, Employee> assignmentEmployeeJoin = projectAssignmentJoin.join("employee");

        cq.select(projectRoot).where(cb.equal(assignmentEmployeeJoin.get("id"), employeeId));

        TypedQuery<Project> query = em.createQuery(cq);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();


//        TypedQuery<Project> query = em.createQuery("SELECT p " +
//                "from Project p " +
//                "JOIN Assignment a ON a.project.id = p.id " +
//                "JOIN Employee e ON e.id = a.employee.id " +
//                "WHERE e.id = :employeeId", Project.class)
//                .setParameter("employeeId", employeeId);
//        return query.getResultList();
    }

    public List<Project> getListProjectByDeptId(Long deptId) {
        EntityGraph entityGraph = em.getEntityGraph("project.department.entity.graph");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Project> cq = cb.createQuery(Project.class);
        Root<Project> projectRoot = cq.from(Project.class);
        cq.select(projectRoot).where(cb.equal(projectRoot.get("managedDepartment"), deptId));
        return em.createQuery(cq)
                .setHint("javax.persistence.fetchgraph", entityGraph)
                .getResultList();
    }

    public List<Object[]> getTotalEmployeeAndTotalHoursOfProject(Long projId) {
        TypedQuery<Object[]> query = em.createNamedQuery("Project.getTotalEmployeeAndTotalNumberOfHours",
                Object[].class);
        query.setParameter("projectId", projId);
        return query.getResultList();
    }

    public List<Object[]> getTotalSalaryAndTotalHoursOfProject(Long projId) {
        TypedQuery<Object[]> query = em.createNamedQuery("Project.getTotalSalaryAndTotalNumberOfHours",
                Object[].class);
        query.setParameter("projectId", projId);
        return query.getResultList();
    }

    public List<Project> getListProject() {
        return em.createQuery("SELECT p FROM Project p", Project.class)
                .setHint("javax.persistence.fetchgraph", em.getEntityGraph("project.department.entity.graph"))
                .getResultList();
    }
}
