package org.eclipse.jakarta.hello.employee.dao;

import org.eclipse.jakarta.hello.base.dao.BaseDAO;
import org.eclipse.jakarta.hello.department.entity.Department;
import org.eclipse.jakarta.hello.employee.dto.EmployeeDTO;
import org.eclipse.jakarta.hello.employee.entity.Employee;

import javax.ejb.Stateless;
import javax.persistence.Query;
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

}
