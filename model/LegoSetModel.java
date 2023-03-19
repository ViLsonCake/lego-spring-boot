package com.project.SpringBootLegoScrap.model;


import com.project.SpringBootLegoScrap.entity.LegoSetEntity;

public class LegoSetModel {
    private Long legoSetId;
    private Integer item;
    private String ages, pieces, price, availability;

    public LegoSetModel() {}

    public static LegoSetModel toModel(LegoSetEntity legoSetEntity) {
        LegoSetModel legoSetModel = new LegoSetModel();

        legoSetModel.setLegoSetId(legoSetEntity.getLego_set_id());
        legoSetModel.setItem(legoSetEntity.getItem());
        legoSetModel.setAges(legoSetEntity.getAges());
        legoSetModel.setPieces(legoSetEntity.getPieces());
        legoSetModel.setPrice(legoSetEntity.getPrice());
        legoSetModel.setAvailability(legoSetEntity.getAvailability());

        return legoSetModel;
    }

    public Long getLegoSetId() {
        return legoSetId;
    }

    public void setLegoSetId(Long legoSetId) {
        this.legoSetId = legoSetId;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public String getAges() {
        return ages;
    }

    public void setAges(String ages) {
        this.ages = ages;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getPieces() {
        return pieces;
    }

    public void setPieces(String pieces) {
        this.pieces = pieces;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
