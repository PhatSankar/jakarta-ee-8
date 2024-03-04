package org.eclipse.jakarta.hello.relative.dao;

import org.eclipse.jakarta.hello.base.dao.BaseDAO;
import org.eclipse.jakarta.hello.relative.dto.RelativeDTO;
import org.eclipse.jakarta.hello.relative.entity.Relative;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class RelativeDAO extends BaseDAO<Relative> {
    public RelativeDAO() {
        super(Relative.class);
    }

    public List<RelativeDTO> getListRelativeInDTO(Long deptId) {
        TypedQuery<RelativeDTO> query = em.createNamedQuery("Relative.findAllByEmployeeWorkInProjectOfDepartment", RelativeDTO.class);
        query.setParameter("deptId", deptId);
        return query.getResultList();
    }
}
