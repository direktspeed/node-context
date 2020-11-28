import org.graalvm.polyglot.*;

import java.io.IOException;
import java.io.File;


//TODO: https://www.graalvm.org/sdk/javadoc/org/graalvm/polyglot/Source.html
//TODO: https://www.graalvm.org/reference-manual/jvm/Options/
public class node {
    public static void main(String[] args) throws IOException {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        //Context to build the context 
        Context context = Context.newBuilder("js").
        //Java.addToClasspath()
        allowHostAccess(HostAccess.ALL).
        allowHostClassLookup(className -> true).  //allows access to all Java classes
        allowIO(true).
        allowAllAccess(true).
        allowCreateThread(false).
        allowPolyglotAccess(PolyglotAccess.ALL).
        allowExperimentalOptions(true).
        // -Dpolyglot.js.strict=true
        option("js.strict", "true").
        option("js.shared-array-buffer", "true").
        option("js.ecmascript-version", "2021").
        // Enable CommonJS experimental support.
        option("js.commonjs-require", "true").
        option("js.commonjs-require-cwd", System.getProperty("user.dir")/*+"/node_modules"*/).
        //option("js.commonjs-global-properties", "./builtin/global.js").
        
        //option("js.commonjs-core-modules-replacements", 
          //  "util:./builtin/util.js,"//+
        //    "async-error:./node_modules/async-error.js"
       // ).
        
        build();

        //Load the index.mjs as File to have a Directory
        File jsFile = new File("./index.mjs");
        Source src = Source.newBuilder("js", jsFile)
            .name("index.mjs")
            .mimeType("application/javascript+module")
            .buildLiteral();
                
        context.eval(src);
        
    }
    // Load main from cmd args later
    public static void loadMain(){
        /*
        Path filePath = Paths.get("C:/", "temp", "test.txt");
 
        try
        {
            String content = Files.readString(filePath);

            System.out.println(content);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        */
    }

}
