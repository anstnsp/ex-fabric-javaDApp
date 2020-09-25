package kr.co.ex.domain.checkpoint;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CheckPointRepository extends JpaRepository<CheckPoint, Long> {
  Optional<CheckPoint> findByTxId(String txId);
  
  // select max(checkpoint0_.block_number) as col_0_0_ from checkpoint checkpoint0_
  // @Query("select max(c.BLOCK_NUMBER) from CHECKPOINT c")
  // Long getMaxBlockNumber(); 

  // @Query("select c.TX_ID from CHECKPOINT c where c.BLOCK_NUMBER = ?1")
  // Set<String> findByBlockNumber(Long blockNumber);
  @Query("select max(c.blockNumber) from CheckPoint c")
  Long getMaxBlockNumber(); 

  @Query("select c.txId from CheckPoint c where c.blockNumber = :blockNumber")
  Set<String> findByBlockNumber(@Param("blockNumber") Long blockNumber);

}