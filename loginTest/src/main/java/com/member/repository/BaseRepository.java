package com.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

// 이 인터페이스에 @NoRepositoryBean을 표시하여 Spring Data JPA가 이를 인스턴스화하지 않도록 합니다
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
}
