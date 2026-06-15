package com.selcan.StreamVibe.repository;

import com.selcan.StreamVibe.entity.Faq;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FaqRepository extends JpaRepository<Faq,Integer> {

    List<Faq> findAllByOrderByOrderNumberAsc();
}
