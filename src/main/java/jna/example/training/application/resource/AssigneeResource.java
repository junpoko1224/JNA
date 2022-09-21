package jna.example.training.application.resource;

import jna.example.training.domain.object.AssigneeId;
import jna.example.training.domain.object.AssigneeName;
import jna.example.training.infrastructure.entity.AssigneeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "create")
public class AssigneeResource {

    private AssigneeName assigneeName;
    private AssigneeId assigneeId;

    public AssigneeEntity toEntity() {
        return AssigneeEntity.builder()
                .assigneeName(assigneeName.getAssigneeName())
                .assigneeId(assigneeId.getAssignee())
                .build();
    }
}
