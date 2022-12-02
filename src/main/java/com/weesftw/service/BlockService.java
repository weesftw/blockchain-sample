package com.weesftw.service;

import com.weesftw.algorithms.Hash;
import com.weesftw.consensus.Consensus;
import com.weesftw.model.Block;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static java.lang.String.format;

public final class BlockService {

    private final Hash hash;
    private final Consensus consensus;
    private final List<Block> chains = new LinkedList<>();

    public BlockService(Hash hash, Consensus consensus, int amount) {
        this.hash = hash;
        this.consensus = consensus;
        createBlock(amount);
    }

    public void updateBlock(int id, String data) {
        var block = chains.get(id);
        block.setData(data);
        block.setHash(hash.calculate(block.parametersToCalculateHash()));
    }

    public void createBlock(int amount) {
        while (amount-- > 0) {
            var block = generateBlock();
            consensus.execute(block); // "miner" is working
        }
    }

    public List<Block> getChain() {
        return chains;
    }

    private Block generateBlock() {
        var id = chains.size() + 1;
        var block = Block.builder()
                .id(id)
                .nonce(0)
                .previousHash(id > 1 ? chains.get(chains.size() - 1).getHash() : null)
                .data(chains.isEmpty() ? "Genesis Block" : format("#%s Block", id))
                .createdAt(LocalDateTime.now())
                .build();

        block.setHash(hash.calculate(block.parametersToCalculateHash()));
        chains.add(block);
        return block;
    }

    public boolean fixChain(int id) {
        return validateChain(id, true);
    }

    public boolean isValidChain() {
        return validateChain(0, false);
    }

    private boolean validateChain(int id, boolean fixChainIfInvalid) {
        var value = id > 0 ? id - 1 : 0;
        var previousBlock = chains.get(value);
        for (int i = value + 1; i < chains.size(); i++) {
            var currentBlock = chains.get(i);

            // check if the hash of the previous block is equal to the previousHash of the current block
            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash()) ||
                    // check if the hash of the current block is equal to the calculated hash
                    !currentBlock.getHash().equals(hash.calculate(currentBlock.parametersToCalculateHash()))) {
                if(!fixChainIfInvalid) return false;
                consensus.execute(currentBlock);
                currentBlock.setPreviousHash(previousBlock.getHash());
            }

            previousBlock = currentBlock;
        }

        return true;
    }
}
