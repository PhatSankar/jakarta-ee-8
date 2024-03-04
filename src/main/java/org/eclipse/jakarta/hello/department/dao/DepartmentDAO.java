package org.eclipse.jakarta.hello.department.dao;

import org.eclipse.jakarta.hello.base.dao.BaseDAO;
import org.eclipse.jakarta.hello.department.dto.DepartmentDTO;
import org.eclipse.jakarta.hello.department.entity.Department;
import org.eclipse.jakarta.hello.employee.dto.EmployeeDTO;
import org.eclipse.jakarta.hello.project.dto.ProjectDTO;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import java.util.*;

@Stateless
public class DepartmentDAO extends BaseDAO<Department> {

    public DepartmentDAO() {
        super(Department.class);
    }

    public List<Department> getNewList() {
        Query query = em.createQuery("SELECT d from Department d");
        return query.getResultList();
    }

//    public List<Object[]> getDepartmentWithEmployeeAndName() {
//        return em.createNamedQuery("Department.findDepartmentWithEmployeeAndProjectEmployeeWork", Object[].class).getResultList();
//    }

    public List getDepartmentWithEmployeeAndName() {
        Query query = em.createNamedQuery("Department.findDepartmentWithEmployeeAndProjectEmployeeWork");
        return query.getResultList();
    }
}
