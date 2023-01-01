package site.shkrr.whiskychuchu.app.rank.whisky.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.enums.CountryType;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.enums.FlavorType;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.enums.IngredientType;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.dto.AdminWhisky;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.dto.AdminWhiskyDetail;
import site.shkrr.whiskychuchu.app.rank.whiskycharacter.entity.WhiskyCharacter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Whisky {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int price;

    @Column
    private int perPrice;

    @Column
    private Long saleRank;

    @Column
    private Long ownerRank;

    @Column
    private String name;

    @Column
    private String oriImgName;
    @Column
    private String savedName;
    @Column
    private String savedPath;
    @Lob
    private String ownerComment;
    @Column(length = 10,columnDefinition = "varchar(10) default 'UNKNOWN'")
    @Enumerated(EnumType.STRING)
    private FlavorType flavorType;

    @Column(length = 10,columnDefinition = "varchar(10) default 'UNKNOWN'")
    @Enumerated(EnumType.STRING)
    private CountryType countryType;

    @Column(length = 10,columnDefinition = "varchar(10) default 'UNKNOWN'")
    @Enumerated(EnumType.STRING)
    private IngredientType ingredientType;

    @OneToMany(mappedBy = "whisky")
    private List<WhiskyCharacter> whiskyCharacterList;
    @PrePersist
    public void prePersist(){
        ownerComment=ownerComment==null?"empty":ownerComment;
        ownerRank= ownerRank==null?0L:ownerRank;
        saleRank=saleRank==null?0L:saleRank;
        savedPath= savedPath==null ? "empty" : savedPath;
        savedName= savedName==null ? "empty" : savedName;
        oriImgName= oriImgName==null ? "empty" :oriImgName;
        countryType=countryType==null?CountryType.UNKNOWN:countryType;
        ingredientType= ingredientType==null?IngredientType.UNKNOWN:ingredientType;
        flavorType= flavorType==null?FlavorType.UNKNOWN:flavorType;
    }
    public void update(int whiskyPrice, int whiskyPerPrice, Long saleRank) {
        this.price=whiskyPrice;
        this.perPrice=whiskyPerPrice;
        this.saleRank=saleRank;
    }
    public void update(String countryType,String ingredientType,String flavorType) {
        for(CountryType cType:CountryType.values()){
            if(cType.toString().equals(countryType)){
                this.countryType=cType;
            }
        }
        for(IngredientType iType:IngredientType.values()){
            if(iType.toString().equals(ingredientType)){
                this.ingredientType=iType;
            }
        }

        for(FlavorType fType:FlavorType.values()){
            if(fType.toString().equals(flavorType)){
                this.flavorType=fType;
            }
        }
    }

    public AdminWhisky toAdminWhisky() {
        return AdminWhisky.builder()
                .id(id)
                .name(name)
                .savedName(savedName)
                .countryType(countryType.toString())
                .ingredientType(ingredientType.toString())
                .flavorType(flavorType.toString())
                .savedPath(savedPath)
                .saleRank(saleRank)
                .build();
    }

    public AdminWhiskyDetail toAdminWhiskyDetail() {
        return AdminWhiskyDetail.builder()
                .id(id)
                .name(name)
                .price(price)
                .perPrice(perPrice)
                .savedPath(savedPath)
                .savedName(savedName)
                .saleRank(saleRank)
                .countryType(countryType.toString())
                .ingredientType(ingredientType.toString())
                .flavorType(flavorType.toString())
                .build();
    }

    public void updateImgData(String oriImgName, String savedName, String savedPath) {
        this.oriImgName=oriImgName;
        this.savedName=savedName;
        this.savedPath=savedPath;
    }
}
