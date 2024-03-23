package org.eclipse.jakarta.hello.relative.mapper;

import org.eclipse.jakarta.hello.relative.dto.RelativeDTO;
import org.eclipse.jakarta.hello.relative.entity.Relative;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface RelativeMapper {

    RelativeDTO toRelativeDTO(Relative relative);

    List<RelativeDTO> toRelativeDTOs(List<Relative> relative);
}
