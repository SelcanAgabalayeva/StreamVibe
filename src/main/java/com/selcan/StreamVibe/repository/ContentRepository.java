package com.selcan.StreamVibe.repository;

import com.selcan.StreamVibe.entity.Content;
import com.selcan.StreamVibe.enums.ContentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {

    List<Content> findByIsFeaturedTrue();
    Page<Content> findByTypeAndIsTrendingTrue(
            ContentType type,
            Pageable pageable
    );

    Page<Content> findByTypeAndIsNewReleaseTrue(
            ContentType type,
            Pageable pageable
    );

    Page<Content> findByTypeAndIsMustWatchTrue(
            ContentType type,
            Pageable pageable
    );

    @Query("""
select c
from Content c
where c.type = :type
and c.topTenRank is not null
order by c.topTenRank
""")
    List<Content> getTopTen(@Param("type") ContentType type);
}
