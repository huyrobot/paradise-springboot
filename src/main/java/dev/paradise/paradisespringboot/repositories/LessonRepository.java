package dev.paradise.paradisespringboot.repositories;

import dev.paradise.paradisespringboot.models.Lesson;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface LessonRepository extends Neo4jRepository<Lesson, Long> {
    //hàm truy vấn các khóa học có các bài học liên quan(dựa trên relationship BELONGS_TO
    @Query("MATCH (:Course {identifier: $identifier})<-[r:BELONGS_TO]-(lessons:Lesson) Return lessons")
    List<Lesson> findLessonsByCourseIdentifier(String identifier);
}
