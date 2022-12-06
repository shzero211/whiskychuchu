package site.shkrr.whiskychuchu.app.rank.whisky.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.enums.CountryType;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.enums.IngredientType;
import site.shkrr.whiskychuchu.app.rank.whisky.service.dto.AdminWhisky;

import javax.persistence.*;

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
    private String name;

    @Column
    private String imgUrl;

    @Column(length = 10,columnDefinition = "varchar(10) default 'UNKNOWN'")
    @Enumerated(EnumType.STRING)
    private CountryType countryType;

    @Column(length = 10,columnDefinition = "varchar(10) default 'UNKNOWN'")
    @Enumerated(EnumType.STRING)
    private IngredientType ingredientType;

    @PrePersist
    public void prePersist(){
        imgUrl= imgUrl==null ? "empty" : imgUrl;
        countryType=countryType==null?CountryType.UNKNOWN:countryType;
        ingredientType= ingredientType==null?IngredientType.UNKNOWN:ingredientType;
    }
    public void update(int whiskyPrice, int whiskyPerPrice, Long saleRank) {
        this.price=whiskyPrice;
        this.perPrice=whiskyPerPrice;
        this.saleRank=saleRank;
    }

    public AdminWhisky toAdminWhisky() {
        return AdminWhisky.builder()
                .id(id)
                .name(name)
                .countryType(countryType.toString())
                .ingredientType(ingredientType.toString())
                .build();
    }
}
