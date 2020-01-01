package org.houqian.springbootdemo.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.houqian.springbootdemo.dto.Block;
import org.houqian.springbootdemo.dto.BlockChain;
import org.houqian.springbootdemo.dto.Transaction;
import org.houqian.springbootdemo.service.BlockChainService;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2018/8/30
 */
public class BlockChainServiceImpl implements BlockChainService {

  @Override
  public boolean registerNode(String address) {
    return false;
  }

  @Override
  public boolean validChain(BlockChain chain) {
    return false;
  }

  @Override
  public boolean resolveConflicts() {
    return false;
  }

  @Override
  public Block newBlock(long proof, String previousHash) {
    String preHash = previousHash;

    Block      preBlock;
    BlockChain bc = BlockChain.builder().build();
    if (StringUtils.isEmpty(previousHash)) {
      preBlock = bc.getChain()[bc.getChain().length - 1];
      preHash = this.computeHashForBlock(preBlock);
    }

    Block newBlock = Block.builder()
            .index(bc.getChain().length + 1)
            .previousHash(preHash)
            .proof(proof)
            .timestamp(System.currentTimeMillis())
            .transactions(bc.getTransactions())
            .build();

    bc.setTransactions(null);
//    bc.setChain(();
    return newBlock;
  }

  private String computeHashForBlock(Block block) {
    return DigestUtils.sha256Hex(block.toString());
  }

  @Override
  public long newTransaction(Transaction transaction) {
    return 0;
  }

  @Override
  public Block lastBlock() {
    return null;
  }

  @Override
  public void proofOfWork(long lastProof) {

  }

  @Override
  public boolean verifyProof(long lastProof, long proof) {
    return false;
  }
}
