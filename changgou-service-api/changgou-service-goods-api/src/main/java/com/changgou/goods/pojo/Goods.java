package com.changgou.goods.pojo;

import java.util.List;

public class Goods {
    private Spu spu;
    private List<Sku> skuList;

    public Goods(Spu spu, List<Sku> skuList) {
        this.spu = spu;
        this.skuList = skuList;
    }

    public Goods() {

    }


    public Spu getSpu() {
        return spu;
    }

    public List<Sku> getSkuList() {
        return skuList;
    }

    public void setSpu(Spu spu) {
        this.spu = spu;
    }

    public void setSkuList(List<Sku> skuList) {
        this.skuList = skuList;
    }
}
