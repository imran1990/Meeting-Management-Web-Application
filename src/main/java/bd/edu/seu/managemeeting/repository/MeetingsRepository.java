package bd.edu.seu.managemeeting.repository;

import bd.edu.seu.managemeeting.model.Meetings;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@JaversSpringDataAuditable
@Repository
public interface MeetingsRepository extends MongoRepository<Meetings,String> {

}
