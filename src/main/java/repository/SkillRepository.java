package repository;

import model.Skill;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by mmaheo on 19/03/2017.
 */
public interface SkillRepository extends MongoRepository<Skill, String> {

    public List<Skill> findByName(String name);

}