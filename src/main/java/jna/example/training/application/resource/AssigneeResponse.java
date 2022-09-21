package jna.example.training.application.resource;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class AssigneeResponse {

    private List<Response> responseList;

    public void factory(List<AssigneeResponseResource> assigneeResponseResource) {
        this.responseList = assigneeResponseResource.stream().map(val -> factoryResponse(val)).collect(Collectors.toList());
    }

    private Response factoryResponse(AssigneeResponseResource resource) {
        return new Response(
                resource.getAssigneeId().getAssignee(),
                resource.getAssigneeName().getAssigneeName()
        );
    }

    @Data
    @AllArgsConstructor
    private class Response {
        private int assigneeId;
        private String assigneeName;
    }
}
