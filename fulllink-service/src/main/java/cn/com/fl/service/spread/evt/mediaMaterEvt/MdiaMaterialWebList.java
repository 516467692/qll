package cn.com.fl.service.spread.evt.mediaMaterEvt;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @auther: Lxy
 * @description: 素材库web列表
 * @create: 2021-11-10 09:42
 */
@Data
public class MdiaMaterialWebList {
    @NotNull(message = "type为空")
    public String type;

    public String name;

    public String label;

    public String explain;


}
