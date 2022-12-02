package com.weesftw;

import com.weesftw.model.Block;

import java.util.List;

public class InvalidChainException extends RuntimeException {

    public InvalidChainException(List<Block> chain) {
        super(chain.toString());
    }

    public InvalidChainException(String message) {
        super(message);
    }
}
