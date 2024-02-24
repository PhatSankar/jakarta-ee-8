package org.eclipse.jakarta.hello.assignment.dao;

import org.eclipse.jakarta.hello.assignment.entity.Assignment;
import org.eclipse.jakarta.hello.base.dao.BaseDAO;
import org.eclipse.jakarta.hello.project.entity.Project;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class AssignmentDAO extends BaseDAO<Assignment> {
    public AssignmentDAO() {
        super(Assignment.class);
    }

    public List<Object> getList() {
        Query query = em.createQuery("SELECT p" +
                "from Assignment a" +
                "JOIN a.project p" +
                "JOIN a.employee e");
        return query.getResultList();
    }

}
