package jna.example.training.domain.service;

import jna.example.training.application.resource.EditorRequest;
import jna.example.training.application.resource.EditorResponse;
import jna.example.training.application.resource.EditorResponseResource;
import jna.example.training.application.resource.RegisterResource;
import jna.example.training.infrastructure.entity.EmployeeEntity;
import jna.example.training.infrastructure.mapper.EmployeeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EditorService {
    private final EmployeeMapper employeeMapper;

    /**
     * 社員情報更新
     */
    public void editor(RegisterResource resource) {

        EmployeeEntity entity = resource.toEntity();
        entity.insertData();
        employeeMapper.update(entity);
    }
}