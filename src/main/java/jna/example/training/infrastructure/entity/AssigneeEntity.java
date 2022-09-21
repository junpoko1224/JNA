package jna.example.training.infrastructure.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AssigneeEntity extends CommonEntity {
    private int assigneeId;
    private String assigneeName;
}
