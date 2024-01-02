package com.member.repository;

import com.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 첫번째 인자는 Entity클래스 두번째 인자는 Entity클래스의 pk의 타입
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // 이메일로 회원 정보 조회 (select * from member_table where membe_mail=?)
    Optional<MemberEntity> findByMemberEmail(String memberEmail);
}
