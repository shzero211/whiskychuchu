package site.shkrr.whiskychuchu.rank.controller.dto;

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
