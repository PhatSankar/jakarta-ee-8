package org.eclipse.jakarta.hello.project.dto;

import lombok.*;
import org.eclipse.jakarta.hello.project.entity.Area;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDTO {

    private Long id;

    private Area area;

    private String projectName;

}
