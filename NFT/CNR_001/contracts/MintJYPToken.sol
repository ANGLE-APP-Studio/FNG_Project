// SPDX-License-Identifier: MIT

pragma solidity ^0.8.0;

import "@openzeppelin/contracts/token/ERC721/extensions/ERC721Enumerable.sol";

contract MintJYPToken is ERC721Enumerable {
    constructor() ERC721("h662JYP", "HAS") {}

        function mintJYPToken() public {    //
            uint256 JYPTokenId = totalSupply() +1;
            _mint(msg.sender, JYPTokenId);
        }

}