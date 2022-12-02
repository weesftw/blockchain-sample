package com.weesftw.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public final class Block {

    private int id;
    private int nonce;
    private String data; // payload or content
    private String previousHash;
    private String hash;
    private LocalDateTime createdAt;

    public String parametersToCalculateHash() {
        return previousHash + nonce + data;
    }
}
