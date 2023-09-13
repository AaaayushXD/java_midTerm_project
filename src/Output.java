public class Output {
    public void outputText(String output) {
        System.out.println("-------------Result---------------");
        System.out.println("----------------------------------");
        System.out.println(output);
        System.out.println("----------------------------------");
        System.out.println("----------------------------------");
    }

    public void featureInfo(String title,String info) {
        System.out.println("----------------------------------");
        System.out.println("This feature is called " + title);
        System.out.println(info);
        System.out.println("----------------------------------");
    }
}
