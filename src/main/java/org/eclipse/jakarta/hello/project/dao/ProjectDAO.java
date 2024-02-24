package org.eclipse.jakarta.hello.project.dao;

import org.eclipse.jakarta.hello.base.dao.BaseDAO;
import org.eclipse.jakarta.hello.employee.dto.EmployeeDTO;
import org.eclipse.jakarta.hello.project.entity.Project;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ProjectDAO extends BaseDAO<Project> {
    public ProjectDAO() {
        super(Project.class);
    }

    public List<Project> getListProjectByEmployeeId(Long employeeId) {
        Query query = em.createQuery("SELECT p " +
                "from Assignment a " +
                "JOIN a.project p " +
                "JOIN a.employee e " +
                "WHERE e.id = :employeeId")
                .setParameter("employeeId", employeeId);
        return query.getResultList();
    }
}
