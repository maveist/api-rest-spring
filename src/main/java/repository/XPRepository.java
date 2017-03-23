package repository;

import model.XP;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by leopa on 23/03/2017.
 */
public interface XPRepository extends MongoRepository<XP, String> {

    public List<XP> findByName(String name);
}
