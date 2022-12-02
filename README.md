# blockchain-sample

Blockchain implementation using Java.

## About
- Is made up of blocks that store data.
- Has a digital signature that chains your blocks together.
- Requires proof of work mining to validate new blocks.
- Can be check to see if data in it is valid and unchanged.

## Output

```
Block(id=1, nonce=87667, data=Genesis Block, previousHash=null, hash=00007ac475a054c74adce2600d6b17b15b9b6e2f25cc238400d63d98fc350c59, createdAt=2022-12-05T10:16:24.066021717)
Block(id=2, nonce=90461, data=#2 Block, previousHash=00007ac475a054c74adce2600d6b17b15b9b6e2f25cc238400d63d98fc350c59, hash=0000c54e528ce21c03ecc982b6acc296484159227452d372b3b7e2c84cf1aa71, createdAt=2022-12-05T10:16:25.476105297)
Block(id=3, nonce=43108, data=#3 Block, previousHash=0000c54e528ce21c03ecc982b6acc296484159227452d372b3b7e2c84cf1aa71, hash=00007f90d92ffbed93e6a56978b7ecd89f5ee3f6581c1b32654a48dc1f654413, createdAt=2022-12-05T10:16:26.125222101)
Updating block id 2...
Exception in thread "main" com.weesftw.InvalidChainException: [Block(id=1, nonce=87667, data=Genesis Block, previousHash=null, hash=00007ac475a054c74adce2600d6b17b15b9b6e2f25cc238400d63d98fc350c59, createdAt=2022-12-05T10:16:24.066021717), Block(id=2, nonce=90461, data=Updated data, previousHash=00007ac475a054c74adce2600d6b17b15b9b6e2f25cc238400d63d98fc350c59, hash=7310cd0342d87a0522febafbe3155f9d8c15c3e363858cb2a603123e389ff1c2, createdAt=2022-12-05T10:16:25.476105297), Block(id=3, nonce=43108, data=#3 Block, previousHash=0000c54e528ce21c03ecc982b6acc296484159227452d372b3b7e2c84cf1aa71, hash=00007f90d92ffbed93e6a56978b7ecd89f5ee3f6581c1b32654a48dc1f654413, createdAt=2022-12-05T10:16:26.125222101)]
	at com.weesftw.service.BlockService.getChains(BlockService.java:40)
	at com.weesftw.service.BlockService.getChains(BlockService.java:46)
	at com.weesftw.Application.main(Application.java:18)
```