package fr.formation.spring.museum.repositories;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.formation.spring.museum.models.Locale;

@Repository
@Lazy(true)
@Transactional(readOnly = true)
public interface LocaleRepository extends JpaRepository<Locale, Integer> {
	public Locale findByKey(String key);
}
