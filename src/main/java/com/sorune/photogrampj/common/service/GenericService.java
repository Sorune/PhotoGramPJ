package com.sorune.photogrampj.common.service;

import com.sorune.photogrampj.common.entity.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenericService<Entity extends BaseEntity, DTO> {

    private final JpaRepository<Entity,Long> repository;
    private final ModelMapper modelMapper;
    private final Class<Entity> entityClass;
    private final Class<DTO> dtoClass;

    public DTO saveOrUpdate(DTO dto) {
        Entity entity = modelMapper.map(dto, entityClass);
        entity = repository.save(entity);
        return modelMapper.map(entity, dtoClass);
    }

    public DTO findById(Long id) {
        Entity entity = repository.findById(id).orElse(null);
        return modelMapper.map(entity, dtoClass);
    }

    public boolean delete(Long id) {
        try {
            return repository.findById(id).map(entity -> {
                entity.setDeleted(true);
                repository.save(entity);
                return true;
            }).orElse(false);
        } catch (Exception e) {
            // 여기에서 로깅 처리나 추가적인 예외 처리를 할 수 있습니다.
            return false;
        }
    }
}
