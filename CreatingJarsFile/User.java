package CreatingJarsFile;

public class User {
    public static void main(String[] args) {
        System.out.println("Hello, I'm user!!");
    }
}


/*
 Create a JAR file using:

 jar cvfe app.jar User *.class

 jar  --> JAR command
 c    --> Create
 v    --> Verbose
 f    --> File
 e    --> Entry point

 app.jar --> JAR file name
 User    --> Main class name
 *.class --> Include all .class files

 * means all
 .class means compiled Java class files



 Generating Doc Using -->  Javadoc <class name>
*/