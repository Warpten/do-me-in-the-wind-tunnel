package fr.formation.spring.museum.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.formation.spring.museum.models.Account;

/**
 * Spring automatically implements this interface into a bean called
 * `accountRepository`.
 */
@Repository
@Lazy(true)
@Transactional(readOnly = true)
public interface AccountRepository extends JpaRepository<Account, Integer> {
	@EntityGraph(value = "graph.Account.withLocaleRanks", type = EntityGraphType.LOAD)
	public Account findByUsername(String username);
	
	public boolean existsByEmail(String email);
	public boolean existsByUsername(String username);
	
	@Override
	@EntityGraph(value = "graph.Account.withLocaleRanks", type = EntityGraphType.LOAD)
	public List<Account> findAll();
	
	public Optional<Account> findByUsernameAndPassword(String username, String password);
	
	// This is completely equivalent to the above, but doesn't let Spring generate the query.
	// @Query(value = "SELECT user FROM Account AS user WHERE user.username = :username")
	// public Account findOne(@Param("username") String username);
}
