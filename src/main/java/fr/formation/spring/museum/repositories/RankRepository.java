package fr.formation.spring.museum.repositories;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.formation.spring.museum.models.Rank;

@Repository
@Lazy(true)
@Transactional(readOnly = true)
public interface RankRepository extends JpaRepository<Rank, Integer>{
	@EntityGraph(value = "graph.Rank.withUserNames", type = EntityGraphType.FETCH)
	public Rank findByName(String username);
	
	@Override
	@EntityGraph(value = "graph.Rank.withUserNames", type = EntityGraphType.FETCH)
	public List<Rank> findAll();
	
	@Query("SELECT r.id, r.name, r.authority FROM Rank r")
	public List<Rank> minimalisticFindAll();
}
