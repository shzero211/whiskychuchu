package site.shkrr.whiskychuchu.app.rank.whisky.entity.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminOwnerWhiskyReq {
    private String id;
    private String name;
    private String ownerComment;
}
