package homework.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UrlDto {

    private String url;
    private String parsingType;
    private Integer num;

    public void setUrl(String url){
        this.url = url;
    }
    public void setParsingType(String parsingType){
        this.parsingType = parsingType;
    }
    public void setNum(Integer num){
        this.num = num;
    }
    public String getUrl(){return this.url;}
    public String getParsingType(){return this.parsingType;}
    public Integer getNum(){return this.num;}
}
