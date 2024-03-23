package org.eclipse.jakarta.hello.relative.dao;

import org.eclipse.jakarta.hello.base.dao.BaseDAO;
import org.eclipse.jakarta.hello.relative.dto.RelativeDTO;
import org.eclipse.jakarta.hello.relative.entity.Relative;

import javax.ejb.Stateless;
import javax.persistence.EntityGraph;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class RelativeDAO extends BaseDAO<Relative> {
    public RelativeDAO() {
        super(Relative.class);
    }

    public List<Relative> getListRelativeInDTO(Long deptId) {
        EntityGraph entityGraph = em.getEntityGraph("relative.employee.entity.graph");
        TypedQuery<Relative> query = em.createNamedQuery
                ("Relative.findAllByEmployeeWorkInProjectOfDepartment",
                        Relative.class);

        query.setParameter("deptId", deptId);

        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }
}
