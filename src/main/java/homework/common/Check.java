package homework.common;

public enum Check {
    HTML("<[\\/\\!]*?[^<>]*?>"),TXT("[^0-9a-zA-Z]+"),
    NUM("[^0-9]"),ENG("[^a-z|A-Z]");
    private final String regex;

    public String getRegex(){
        return regex;
    }
    Check(String regex){
        this.regex = regex;
    }
}

