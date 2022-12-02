package com.weesftw;

import com.weesftw.algorithms.impl.SHA256Digest;
import com.weesftw.consensus.impl.ProofOfWork;
import com.weesftw.service.BlockService;

import static java.lang.System.*;

public class Application {

    public static void main(String... args) {
        var hash = new SHA256Digest();
        var consensus = new ProofOfWork(hash);
        var service = new BlockService(hash, consensus, 3);

        service.getChain().forEach(out::println);
        out.println("Updating block id 2...");
        service.updateBlock(1, "Updated data");
        service.getChain().forEach(out::println); // throws InvalidChainException
    }
}
