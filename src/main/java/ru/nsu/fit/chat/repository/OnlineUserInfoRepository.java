package ru.nsu.fit.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.chat.domain.OnlineUserInfo;

@Repository
public interface OnlineUserInfoRepository extends JpaRepository<OnlineUserInfo, Long> {
    OnlineUserInfo findByUsername(String username);
}
