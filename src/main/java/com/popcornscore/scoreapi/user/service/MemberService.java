package com.popcornscore.scoreapi.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.popcornscore.scoreapi.user.config.jwt.JwtToken;
import com.popcornscore.scoreapi.user.config.jwt.JwtTokenProvider;
import com.popcornscore.scoreapi.user.dto.MemberDto;
import com.popcornscore.scoreapi.user.dto.SignInDto;
import com.popcornscore.scoreapi.user.dto.SignUpDto;
import com.popcornscore.scoreapi.user.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService {
	private final MemberRepository memberRepository;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final JwtTokenProvider jwtTokenProvider;
	private final PasswordEncoder passwordEncoder;
	
	@Transactional
	public JwtToken signIn(String username, String password) {
		
		System.out.println("Member Service Pass!");
		// 1. username + password 를 기반으로 Authentication 객체 생성
		// 이때 authentication 은 인증 여부를 확인하는 authenticated 값이 false
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		
		System.out.println("Member Service Pass2!");
		System.out.println("authenticationToken value + " + authenticationToken);
		// 2. 실제 검증. authenticate() 메서드를 통해 요청된 Member에 대한 검증 진행
		// authenticate 메서드가 실행될 때 CustomUserDetailService 에서 만든 loadUserByUsername 메서드 실행
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		
		System.out.println("Member Service Pass3!");
		// 3. 인증 정보를 기반으로 JWT 토큰 생성
		JwtToken jwtToken = jwtTokenProvider.generateToken(authentication, username);
		
		return jwtToken;
	}
	
	@Transactional
	public MemberDto signUp(SignUpDto signUpDto) {
		
		System.out.println(signUpDto);
		
//		if(memberRepository.existsByUsername(signUpDto.getUsername())) {
//			throw new IllegalArgumentException("이미 사용 중인 사용자 이름입니다.");
//		}
		
		// Password 암호화
		String encodedPassword = passwordEncoder.encode(signUpDto.getPassword());
		List<String> roles = new ArrayList<>();
		roles.add("USER"); // USER 권한 부여
		signUpDto.toEntity(encodedPassword, roles);
		return MemberDto.toDto(memberRepository.save(signUpDto.toEntity(encodedPassword, roles)));
	}


}
