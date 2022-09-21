package jna.example.training.infrastructure.mapper;

import jna.example.training.application.resource.EditorRequest;
import jna.example.training.application.resource.EditorResponseResource;
import jna.example.training.application.resource.RegisterResource;
import jna.example.training.infrastructure.entity.EmployeeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    List<EmployeeEntity> search(EmployeeEntity entity);

    EmployeeEntity searchById(String empNo);

    void save(EmployeeEntity entity);

    void deleteById(String empNo);

    void update(EmployeeEntity entity);

}
