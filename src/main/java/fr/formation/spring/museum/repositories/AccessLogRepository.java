package fr.formation.spring.museum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import fr.formation.spring.museum.models.AccessLog;

@Transactional
public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {

}
