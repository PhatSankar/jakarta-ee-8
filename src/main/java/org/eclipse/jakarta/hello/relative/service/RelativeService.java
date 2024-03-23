package org.eclipse.jakarta.hello.relative.service;

import org.eclipse.jakarta.hello.base.exception.BadRequestException;
import org.eclipse.jakarta.hello.department.dao.DepartmentDAO;
import org.eclipse.jakarta.hello.department.entity.Department;
import org.eclipse.jakarta.hello.relative.dao.RelativeDAO;
import org.eclipse.jakarta.hello.relative.dto.RelativeDTO;
import org.eclipse.jakarta.hello.relative.entity.Relative;
import org.eclipse.jakarta.hello.relative.mapper.RelativeMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Stateless
public class RelativeService {

    @Inject
    private RelativeDAO relativeDAO;

    @Inject
    private RelativeMapper relativeMapper;

    public List<RelativeDTO> getListRelativeInDepartment(Long deptId) {
        return relativeMapper.toRelativeDTOs(relativeDAO.getListRelativeInDTO(deptId));
    }
}
