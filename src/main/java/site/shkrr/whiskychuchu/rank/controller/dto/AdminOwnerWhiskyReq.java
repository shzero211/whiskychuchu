package site.shkrr.whiskychuchu.rank.controller.dto;

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
