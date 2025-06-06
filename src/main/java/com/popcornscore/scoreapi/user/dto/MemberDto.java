package com.popcornscore.scoreapi.user.dto;

import com.popcornscore.scoreapi.user.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {

	private Long id;
	private String username;
	private String nickname;
	private String address;
	private String phone;
	private String profileImg;
	private String email;
	
	static public MemberDto toDto(Member member) {
		return MemberDto.builder()
				.id(member.getId())
				.username(member.getUsername())
				.nickname(member.getNickname())
				.address(member.getAddress())
				.phone(member.getPhone())
				.profileImg(member.getProfileImg())
				.email(member.getEmail())
				.build();
	}
	
	public Member toEntity() {
		return Member.builder()
				.id(id)
				.username(username)
				.nickname(nickname)
				.address(address)
				.phone(phone)
				.profileImg(profileImg)
				.email(email).build();
	}
	
}
