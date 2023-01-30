package site.shkrr.whiskychuchu.app.rank.whisky.entity.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AdminOwnerWhisky {
    private Long id;
    private String savedPath;
    private String name;
    private String ownerComment;
}
