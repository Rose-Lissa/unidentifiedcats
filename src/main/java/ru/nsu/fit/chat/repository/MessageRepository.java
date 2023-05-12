package ru.nsu.fit.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.chat.domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

}
