package homework.service.impl;

import homework.common.Check;
import homework.dto.ValueDto;
import homework.service.ParsingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class ParsingServiceImpl implements ParsingService {

    private List<String> textList = new ArrayList<>();
    private List<Integer> numList = new ArrayList<>();
    public String text = "";
    public String number = "";
    public StringBuilder longString = new StringBuilder();
    public StringBuilder remainder = new StringBuilder();
    public StringBuilder quotientValue = new StringBuilder();


    public ValueDto parsing(String url, String type, int num){
        ValueDto valueDto = new ValueDto();
        if(url.isEmpty())
            return valueDto;
        String onlytxt = "";
        if(type.equals("HTML")){//txt만
            onlytxt = htmlParsing(url);
        }
        else{
            onlytxt = textParsing(url);
        }
        replace(onlytxt);
        textList = toParseTextList(text);
        setNumList(number);
        textList.sort(ALPHABET_ORDER);
        numList.sort(Comparator.naturalOrder());
        answer(num);
        valueDto.setQuotient(quotientValue.toString());
        valueDto.setRemainder(remainder.toString());

        return valueDto;
    }

    private void answer(int num){
        int textListSize = textList.size();
        int numListSize = numList.size();
        int maxNum = Math.max(textListSize,numListSize);
        int minNum = Math.min(textListSize,numListSize);
        for(int i=0;i<minNum;i++){
            longString.append(textList.get(i));
            longString.append(numList.get(i));
        }
        if(textListSize-minNum == 0){
            for(int i=minNum;i<numListSize;i++){
                longString.append(numList.get(i));
            }
        }
        else if(numListSize-minNum==0){
            for(int i=minNum;i<textListSize;i++){
                longString.append(textList.get(i));
            }
        }
        if(maxNum / num == 0){
            remainder = longString;
        }
        else{
            int start = 0;
            int end = num;
            while(maxNum / num != 0){
                quotientValue.append(longString.substring(start,end));
                start = end;
                end += num;
                maxNum -= num;
            }
            if(maxNum > 0){
                remainder.append(longString.substring(start));
            }
        }
    }
    private static Comparator<String> ALPHABET_ORDER = (s1, s2) -> {
        int res = String.CASE_INSENSITIVE_ORDER.compare(s1, s2);
        if (res == 0) {
            return s1.compareTo(s2);
        }
        return res;
    };

    private void replace(String txt){
        text = txt.replaceAll(Check.ENG.getRegex(),"");
        number = txt.replaceAll(Check.NUM.getRegex(),"");
    }
    private void setNumList(String txt){
        if(txt.isEmpty())
            return;
        String[] s = txt.split("");
        for(String k : s){
            try{
                numList.add(Integer.parseInt(k));
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
    }

    private List<String> toParseTextList(String text){
        String[] s = text.split("");
        return Arrays.asList(s);
    }
    private String htmlParsing(String url){ return url.replaceAll(Check.HTML.getRegex(),"");}
    private String textParsing(String url){ return url.replaceAll(Check.TXT.getRegex(),"");}

}