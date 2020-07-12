package bd.edu.seu.managemeeting.repository;

import bd.edu.seu.managemeeting.model.Objection;
import org.bson.types.ObjectId;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@JaversSpringDataAuditable
@Repository
public interface ObjectionRepository extends MongoRepository<Objection, ObjectId> {
}
