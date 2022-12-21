package site.shkrr.whiskychuchu.app.rank.whisky.entity.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class AdminWhiskyDetailReq {
    @NotNull(message = "id (이)가 비어 있습니다.")
    private Long id;
    @NotBlank(message = "나라 (이)가 비어 있습니다.")
    private String countryType;
    @NotBlank(message = "재료 (이)가 비어 있습니다.")
    private String ingredientType;
    @NotBlank(message = "맛 종류 (이)가 비어 있습니다.")
    private String flavorType;
}
