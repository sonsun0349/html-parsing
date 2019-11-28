package homework.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ValueDto {

    private String quotient;
    private String remainder;

    public void setQuotient(String quotient){
        this.quotient = quotient;
    }
    public void setRemainder(String remainder){
        this.remainder = remainder;
    }
    public String getQuotient(){
        return this.quotient;
    }
    public String getRemainder(){
        return this.remainder;
    }
}
