package com.selcan.StreamVibe.repository;

import com.selcan.StreamVibe.entity.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage,Integer> {
}
