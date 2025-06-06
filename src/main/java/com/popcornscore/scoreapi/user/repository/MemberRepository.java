package com.popcornscore.scoreapi.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.popcornscore.scoreapi.user.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	Optional<Member> findByUsername(String username);
	
	boolean existsByUsername(String username);
	
//	@Transactional
//	@Modifying
//	@Query("INSERT INTO member (username) VALUES (:#{#member.username})")
//	void singUp(@Param("member") Member member);
}
