package bd.edu.seu.managemeeting.repository;

import bd.edu.seu.managemeeting.model.Venue;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@JaversSpringDataAuditable
@Repository
public interface VenueRepository extends MongoRepository<Venue,String> {
}
