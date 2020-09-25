package kr.co.ex.domain.insinfo;

import org.springframework.data.jpa.repository.JpaRepository;


public interface InsInfoRepository extends JpaRepository<InsInfo, Long>, InsInfoRepositoryCustom {


}
