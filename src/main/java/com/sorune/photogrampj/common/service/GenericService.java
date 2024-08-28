package com.sorune.photogrampj.common.service;

import com.sorune.photogrampj.common.entity.BaseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class GenericService<Entity extends BaseEntity, DTO> {

    private final JpaRepository<Entity,Long> repository;
    private final ModelMapper modelMapper;
    private final Class<Entity> entityClass;
    private final Class<DTO> dtoClass;

    @PersistenceContext
    private EntityManager em;  // EntityManager 주입

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

    // 중복 확인 후 저장 또는 기존 엔티티 반환
    // 저장하려는 데이터 객체(DTO)와 확인할 필드명, 값을 Map 타입으로 입력받아
    // 데이터베이스에서 조회 후 없을 경우 저장, 있을 경우 기존에 있던 데이터로 값을 저장
    public DTO findOrSave(DTO dto, Map<String, Object> fieldMap) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Entity> query = cb.createQuery(entityClass);
        Root<Entity> root = query.from(entityClass);

        Predicate[] predicates = fieldMap.entrySet().stream()
                .map(entry -> cb.equal(root.get(entry.getKey()), entry.getValue()))
                .toArray(Predicate[]::new);

        query.where(cb.and(predicates));

        Optional<Entity> existingEntity = em.createQuery(query).getResultStream().findFirst();

        Entity entity;

        if (existingEntity.isPresent()) {
            entity = existingEntity.get();
        } else {
            entity = modelMapper.map(dto, entityClass);
            entity = repository.save(entity);
        }

        return modelMapper.map(entity, dtoClass);
    }

}
