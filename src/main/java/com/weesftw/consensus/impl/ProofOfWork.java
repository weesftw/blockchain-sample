package com.weesftw.consensus.impl;

import com.weesftw.algorithms.Hash;
import com.weesftw.consensus.Consensus;
import com.weesftw.model.Block;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProofOfWork implements Consensus {

    private final Hash hash;

    @Override
    public void execute(Block block) {
        final int difficulty = 4; // 4 leading zeros, more zeros = more difficulty and more time to mine
        final var compare = new String(new char[difficulty]).replace('\0', '0');

        while (!block.getHash().substring(0, difficulty).equals(compare)) {
            block.setNonce(block.getNonce() + 1);
            block.setHash(hash.calculate(block.parametersToCalculateHash()));
        }
    }
}
