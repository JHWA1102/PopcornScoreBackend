package com.popcornscore.scoreapi.user.service;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.popcornscore.scoreapi.user.entity.Member;
import com.popcornscore.scoreapi.user.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		return memberRepository.findByUsername(username)
				.map(this::createUserDetails)
				.orElseThrow(() -> new UsernameNotFoundException("해당하는 회원을 찾을 수 없습니다."));
			
	}
	
	// 해당하는 User의 데이터가 존재한다면 UserDetails 객체로 만들어서 return
	private UserDetails createUserDetails(Member member) {
		System.out.println("create pass?");
		System.out.println(">>>> member value : " + member.getUsername());
		System.out.println(">>>> member value : " + member.getPassword());
		
		return User.builder()
				.username(member.getUsername())
				.password(passwordEncoder.encode(member.getPassword()))
				.roles(member.getRoles().toArray(new String[0]))
				.build();
	}
	
	

}
