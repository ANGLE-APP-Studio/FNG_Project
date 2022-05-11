// SPDX-License-Identifier: MIT

pragma solidity ^0.8.0;

import "mintJYPToken.sol";

contract SaleJYPToken {
    MintJYPToken public mintJYPTokenAddress;

    constructor (address _mintJYPTokenAddress) {
        mintJYPTokenAddress = MintJYPToken(_mintJYPTokenAddress);
    }

    mapping(uint256 => uint256) public JYPTokenPrices;

    uint256[] public onSaleJYPTokenArray;

    function setForSaleJYPToken(uint256 _JYPTokenId, uint256 _price) public {
        address JYPTokenOwner = mintJYPTokenAddress.ownerOf(_JYPTokenId);

        require(JYPTokenOwner == msg.sender, "Caller is not JYP Token owner!");
        require(_price > 0,"Price is zero or lower!");
        require(JYPTokenPrices [_JYPTokenId]==0,"This JYP Token is already on sale!");
        require(mintJYPTokenAddress.isApprovedForAll(JYPTokenOwner, address(this)), "JYP Token owner did not approve Token!");

        JYPTokenPrices[_JYPTokenId] = _price;

        onSaleJYPTokenArray.push(_JYPTokenId);
    }

    function purchaseJYPToken(uint256 _JYPTokenId) public payable {
        uint256 price = JYPTokenPrices[_JYPTokenId];
        address JYPTokenOnwer = mintJYPTokenAddress.ownerOf(_JYPTokenId);

        require(price > 0, "JYP Token Not sale!");
        require(price <= msg.value, "Caller sent lower than price!");
        require(JYPTokenOnwer != msg.sender,"Caller is JYP Token owner!");

        payable(JYPTokenOnwer).transfer(msg.value);
        mintJYPTokenAddress.safeTransferFrom(JYPTokenOnwer, msg.sender, _JYPTokenId);

        JYPTokenPrices[_JYPTokenId] =0;

        for(uint256 i=0; i<onSaleJYPTokenArray.length; i++){
            if(JYPTokenPrices[onSaleJYPTokenArray[i]]==0){
                onSaleJYPTokenArray[i] = onSaleJYPTokenArray[onSaleJYPTokenArray.length -1];
                onSaleJYPTokenArray.pop();
            }
        }
    }

    function getOnsaleJYPTokenArrayLength() view public returns(uint256){
        return onSaleJYPTokenArray.length;
    }
}