package org.houqian.springbootdemo.service;

import org.houqian.springbootdemo.dto.Block;
import org.houqian.springbootdemo.dto.BlockChain;
import org.houqian.springbootdemo.dto.Transaction;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2018/8/30
 */
public interface BlockChainService {

  boolean registerNode(String address);

  boolean validChain(BlockChain chain);

  boolean resolveConflicts();

  Block newBlock(long proof, String previousHash);

  long newTransaction(Transaction transaction);

  Block lastBlock();

  void proofOfWork(long lastProof);

  boolean verifyProof(long lastProof, long proof);
}
