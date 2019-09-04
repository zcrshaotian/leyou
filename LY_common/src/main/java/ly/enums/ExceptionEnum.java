package ly.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ExceptionEnum {
    Brand_Not_Null(404, "没有查询到商品"),
    Category_Not_Found(404, "没有查询到结果"),
    Save_Brand_Error(500, "存储商品失败"),
    Goods_Not_Found(500, "未查询到商品"),
    Good_Save_Error(500, "存储商品失败"),
    Upload_File_Error(500, "存储文件失败"),
    Invalid_File_Type(400, "无效的文件类型"),
    CategoryName_Not_Found(500, "没有查询到相关的商品分类名称"),
    Delete_Brand_Error(500, "删除商品失败"),
    SpecGroups_Not_Found(500, "未查询到该规格参数分组"),
    SpecParams_Not_Found(500, "未查询到该规格参数的参数名称"),
    ;
    private int Code;
    private String Message;
}
