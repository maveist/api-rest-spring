package repository;

import model.Worker;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by mmaheo on 19/03/2017.
 */
public interface WorkerRepository extends MongoRepository<Worker, String> {
}