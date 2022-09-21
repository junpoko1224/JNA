package jna.example.training.application.resource;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AssigneeRequest {
    @NotNull
    public String assigneeName;
}
