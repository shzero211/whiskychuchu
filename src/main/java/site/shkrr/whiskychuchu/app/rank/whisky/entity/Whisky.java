package site.shkrr.whiskychuchu.app.rank.whisky.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public void update(int whiskyPrice, int whiskyPerPrice, Long saleRank) {
        this.price=whiskyPrice;
        this.perPrice=whiskyPerPrice;
        this.saleRank=saleRank;
    }
}
