package kr.co.ex.domain.checkpoint;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import kr.co.ex.domain.BaseTimeEntity;
import kr.co.ex.util.BooleanToYNConverter;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
// @Convert(converter=BooleanToYNConverter.class , attributeName = "isValid") //해당타입에 컨버터 적용. 
@Table(name = "CHECKPOINT")
public class CheckPoint extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CHECKPOINT_ID", nullable = false)
  private Long id; 

  @Column(name = "BLOCK_NUMBER", nullable = false)
  private Long blockNumber; 

  @Column(name = "TX_ID", nullable = false)
  private String txId;

  @Convert(converter = BooleanToYNConverter.class)
  @Column(name = "IS_VALID", nullable = false)
  private boolean isValid; 

  @Column(name = "FROM_PEER", nullable = false)
  private String peer; 
  

  @Builder
  public CheckPoint(Long blockNumber, String txId, boolean isValid, String peer) {
    this.blockNumber = blockNumber;
    this.txId = txId;
    this.isValid = isValid;
    this.peer = peer; 
  }

}