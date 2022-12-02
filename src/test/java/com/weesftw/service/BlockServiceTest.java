package com.weesftw.service;

import com.weesftw.algorithms.impl.SHA256Digest;
import com.weesftw.consensus.impl.ProofOfWork;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BlockServiceTest {

    private static BlockService unit;

    @BeforeAll
    static void setUp() {
        var hash = new SHA256Digest();
        var consensus = new ProofOfWork(hash);
        unit = new BlockService(hash, consensus, 3);
    }

    @Test
    @Order(1)
    void chain() {
        assertDoesNotThrow(() -> unit.getChain());
    }

    @Test
    @Order(2)
    void chainSize() {
        assertSame(3, unit.getChain().size());
    }

    @Test
    @Order(3)
    void createBlock() {
        unit.createBlock(1);
        assertSame(4, unit.getChain().size());
    }

    @Test
    @Order(4)
    void updateBlock() {
        unit.updateBlock(1, "Updated");
        assertSame("Updated", unit.getChain().get(1).getData());
    }

    @Test
    @Order(5)
    void verifyInvalidHashes() {
        var block1 = unit.getChain().get(1);
        var block2 = unit.getChain().get(2);

        assertNotSame(block1.getHash(), block2.getPreviousHash());
    }

    @Test
    @Order(6)
    void invalidBlock() {
        assertFalse(unit.isValidChain());
    }
    
    @Test
    @Order(7)
    void fixChain() {
        assertTrue(unit.fixChain(1)); // check from 1 block and correct forward
    }

    @Test
    @Order(8)
    void verifyValidHashes() {
        var block1 = unit.getChain().get(1);
        var block2 = unit.getChain().get(2);

        assertSame(block1.getHash(), block2.getPreviousHash());
    }
}