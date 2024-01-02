package com.member.service;

import com.member.dto.MemberDTO;
import com.member.entity.MemberEntity;
import com.member.repository.MemberRepository;
import com.member.util.EncryptionUtil;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Encryption;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        String salt = EncryptionUtil.getSalt();
        String encryptedPassword = EncryptionUtil.sha256(memberDTO.getMemberPassword(), salt);

        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberEntity.setMemberPassword(encryptedPassword); // Set encrypted password
        memberEntity.setSalt(salt); // Store salt
        memberRepository.save(memberEntity);
    }


//    public MemberDTO login(MemberDTO memberDTO) {
//        /*
//            1. 회원이 입력한 이메일로 DB에서 조회를 함
//            2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
//         */
//        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
//        if (byMemberEmail.isPresent()) {
//            System.out.println("Email Success");
//            // 조회 결과가 있음
//            MemberEntity memberEntity = byMemberEmail.get();
//            if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
//                System.out.println("password Success");
//                // 비밀번호 일치
//                // entity -> DTO로 바꾼후 변환
//                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
//                return dto;
//            } else {
//                return null;
//            }
//        } else {
//            return null;
//        }
//    }
    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if (byMemberEmail.isPresent()) {
            MemberEntity memberEntity = byMemberEmail.get();
            String encryptedInputPassword = EncryptionUtil.sha256(memberDTO.getMemberPassword(), memberEntity.getSalt());

            if (memberEntity.getMemberPassword().equals(encryptedInputPassword)) {
                // Correct password
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            }
        }
        return null;
    }
}
