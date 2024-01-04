package com.member.service;

import com.member.dto.MemberDTO;

public interface LoginService {
    MemberDTO login(MemberDTO memberDTO);

    void save(MemberDTO memberDTO);
}
