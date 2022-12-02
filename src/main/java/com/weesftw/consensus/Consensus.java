package com.weesftw.consensus;

import com.weesftw.model.Block;

public interface Consensus {

    void execute(Block block);
}