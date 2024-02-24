package org.eclipse.jakarta.hello.department.dao;

import org.eclipse.jakarta.hello.base.dao.BaseDAO;
import org.eclipse.jakarta.hello.department.dto.DepartmentDTO;
import org.eclipse.jakarta.hello.department.entity.Department;
import org.eclipse.jakarta.hello.employee.dao.EmployeeDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class DepartmentDAO extends BaseDAO<Department> {

    public DepartmentDAO() {
        super(Department.class);
    }

    public List<Department> getNewList() {
        Query query = em.createQuery("SELECT d from Department d");
        return query.getResultList();
    }


}
