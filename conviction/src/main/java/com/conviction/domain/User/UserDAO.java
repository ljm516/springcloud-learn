package com.conviction.domain.User;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserDAO extends JpaRepository<User, Long>{

}
