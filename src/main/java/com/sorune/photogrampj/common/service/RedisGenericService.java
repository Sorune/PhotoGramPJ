import com.sorune.photogrampj.common.repository.RedisGenericRepository;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisGenericService<EntityType> {

    private final RedisGenericRepository<EntityType, String> redisGenericRepository;
    private final RedisTemplate<String, EntityType> redisTemplate;

    public RedisGenericService(RedisGenericRepository<EntityType, String> redisGenericRepository, RedisTemplate<String, EntityType> redisTemplate) {
        this.redisGenericRepository = redisGenericRepository;
        this.redisTemplate = redisTemplate;
    }

    // 필드별로 데이터를 저장하는 메서드
    public <S extends EntityType> void saveField(String key, String field, S entity) {
        redisTemplate.opsForHash().put(key, field, entity);
    }

    // 필드에서 데이터를 조회하는 메서드
    public EntityType getFieldById(String key, String field) {
        return (EntityType) redisTemplate.opsForHash().get(key, field);
    }

    // 필드에서 데이터를 삭제하는 메서드
    public void deleteFieldById(String key, String field) {
        redisTemplate.opsForHash().delete(key, field);
    }

    // 기본적으로 Key-Value로 데이터를 저장하는 메서드
    public <S extends EntityType> S save(String key, S entity) {
        redisTemplate.opsForValue().set(key, entity);
        return entity;
    }

    // 기본적으로 Key-Value로 데이터를 조회하는 메서드
    public EntityType findById(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // 기본적으로 Key-Value로 데이터를 삭제하는 메서드
    public void deleteById(String key) {
        redisTemplate.delete(key);
    }
}