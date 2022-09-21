package jna.example.training.domain.service;

import jna.example.training.application.resource.AssigneeRequest;
import jna.example.training.application.resource.AssigneeResource;
import jna.example.training.application.resource.AssigneeResponseResource;
import jna.example.training.application.resource.ViewerResponseResource;
import jna.example.training.domain.object.*;
import jna.example.training.infrastructure.entity.AssigneeEntity;
import jna.example.training.infrastructure.entity.EmployeeEntity;
import jna.example.training.infrastructure.mapper.AssigneeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AssigneeService {

    private final AssigneeMapper assigneeMapper;

    /**
     *  配属先リスト検索
     */
    public List<AssigneeResponseResource> search() {
        List<AssigneeEntity> result = assigneeMapper.getAssigneeList();
        return result.stream().map(val -> searchFactory(val)).collect(Collectors.toList());
    }

    /**
     * 引数のentityからAssigneeResponseResource(クラス型のフィールドを持つ)へ変換する
     */
    private AssigneeResponseResource searchFactory(AssigneeEntity entity) {
        return AssigneeResponseResource.builder()
                .assigneeId(AssigneeId.of(Integer.toString(entity.getAssigneeId())))
                .assigneeName(AssigneeName.of(entity.getAssigneeName()))
                .build();
    }

    public void register(AssigneeResource assigneeResource) {
        AssigneeEntity entity = assigneeResource.toEntity();
        entity.insertData();

        assigneeMapper.save(entity);
    }

    public void delete(String assigneeId) {

        assigneeMapper.delete(Integer.parseInt(assigneeId));
    }

    public void update(AssigneeResource resource) {

        AssigneeEntity entity = resource.toEntity();
        assigneeMapper.update(entity);
    }


}
