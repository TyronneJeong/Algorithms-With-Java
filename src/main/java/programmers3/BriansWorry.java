package programmers3;

public class BriansWorry {

    public void exec(){
        solution("HaEaLaLaObWORLDb");
    }



    private String solution(String sentence) {
        StringBuffer sb = new StringBuffer();
        int priorNumber = 0;

        String[] r = sentence.split("(?=[A-Z])");

        for (String i : r){
            if(i.length() == 2){
                sb.append(i.charAt(0));
            } else {
                sb.append(i.charAt(0));
            }
            // System.out.println(stringSplitter(sentence, i));
        }

        System.out.println(sb.toString());

        return sb.toString();
    }

    private String stringSplitter(String arg, String del){
        System.out.println(arg.indexOf(del) + " ~ " + arg.lastIndexOf(del));
        return arg.substring(arg.indexOf(del)-1, arg.lastIndexOf(del)+2).replaceAll(del, "");
    }
}
