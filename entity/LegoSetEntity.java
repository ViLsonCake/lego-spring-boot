package com.project.SpringBootLegoScrap.entity;

import com.project.SpringBootLegoScrap.entity.entityConsts.EntityConst;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = EntityConst.LEGO_TABLE)
public class LegoSetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lego_set_id;

    @Range(min = 10000, max = 99999)
    private Integer item;

    @NotEmpty
    @Size(min = 2, max = 50)
    private String title;

    @NotEmpty
    private String availability;

    @NotEmpty
    @Pattern(regexp = EntityConst.REGEX_AGES_PATTERN)
    private String ages;

    @NotEmpty
    private String pieces;

    @NotEmpty
    @Pattern(regexp = EntityConst.REGEX_PRICE_PATTERN)
    private String price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public LegoSetEntity() {}

    public Long getLego_set_id() {
        return lego_set_id;
    }

    public void setLego_set_id(Long lego_set_id) {
        this.lego_set_id = lego_set_id;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getAges() {
        return ages;
    }

    public void setAges(String ages) {
        this.ages = ages;
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "LegoSetEntity{" +
                "lego_set_id=" + lego_set_id +
                ", item=" + item +
                ", title='" + title + '\'' +
                ", ages='" + ages + '\'' +
                ", pieces='" + pieces + '\'' +
                ", price='" + price + '\'' +
                ", user=" + user +
                '}';
    }
}
